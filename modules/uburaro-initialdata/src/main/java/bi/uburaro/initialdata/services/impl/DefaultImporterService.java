package bi.uburaro.initialdata.services.impl;

import bi.uburaro.core.services.TypeService;
import bi.uburaro.initialdata.data.BatchData;
import bi.uburaro.initialdata.data.BatchLineData;
import bi.uburaro.initialdata.factory.BatchLineDataFactory;
import bi.uburaro.initialdata.factory.impl.DefaultFileLineFactory;
import bi.uburaro.initialdata.services.DataImporterService;
import bi.uburaro.initialdata.strategies.DataBatchProcessorStrategy;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static bi.uburaro.initialdata.InitialdataConstants.*;

public class DefaultImporterService extends SimpleFileVisitor<Path> implements DataImporterService {
    protected final TypeService typeService;
    protected final DataBatchProcessorStrategy dataBatchProcessorStrategy;

    protected final Environment environment;
    private static final Logger log = LogManager.getLogger();

    public DefaultImporterService(TypeService typeService, DataBatchProcessorStrategy dataBatchProcessorStrategies, Environment environment) {
        this.typeService = typeService;
        this.dataBatchProcessorStrategy = dataBatchProcessorStrategies;
        this.environment = environment;
    }


    @Override
    public void importCurrent() {
        String currentPath = environment.getProperty(INITIAL_PATCHES_CURRENT);
        try {
            Files.walkFileTree(Path.of(currentPath), this);
        } catch (IOException e) {
            log.error("Cant read some files in {}", currentPath);
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
        return StringUtils.endsWith(path.toString(), DATA_FILE_EXTENSION) ?
                processFile(path) : FileVisitResult.CONTINUE;
    }

    protected FileVisitResult processFile(Path filePath) {
        String dataPath = environment.getProperty(INITIAL_DATA_PATH);
        Integer repeat = environment.getProperty(DATA_REPEAT_NUMBER, Integer.class, 1);
        String fileName = StringUtils.remove(filePath.toString(), dataPath);
        try {
            final List<BatchData> batches = new ArrayList<>();
            final BatchLineDataFactory lineFactory = new DefaultFileLineFactory();
            Files.newBufferedReader(filePath).lines()
                    .map(lineFactory::create)
                    .filter(line -> StringUtils.isNoneEmpty(StringUtils.remove(line.getValue(),DELIMITER)))
                    .forEach(line -> addLine(line, batches));

            IntStream.range(0, repeat).forEach(pass -> {
                log.info("Importing {} for the {} time",fileName,pass);
                batches.stream()
                        .filter(batchData -> CollectionUtils.isNotEmpty(batchData.getValues()))
                        .peek(batchData -> batchData.setDataFile(fileName))
                        .peek(batchData -> batchData.getValues().forEach(batchLineData -> batchLineData.setFailed(false)))
                        .forEach(dataBatchProcessorStrategy::processBatch);
            });


        } catch (IOException e) {
            log.error("Error happened while importing file {}", fileName);
        }
        return FileVisitResult.CONTINUE;
    }

    private void addLine(BatchLineData line, List<BatchData> batches) {
        if (StringUtils.startsWith(line.getValue(), TYPE_PREFIX)) {
            BatchData batchData = new BatchData();

            batchData.setTarget(StringUtils.strip(line.getValue(), TYPE_PREFIX));
            batchData.setTarget(StringUtils.split(batchData.getTarget(), DELIMITER)[0]);
            batches.add(batchData);
        } else {
            batches.stream()
                    .skip(batches.size() - 1)
                    .forEach(batchData -> Optional.ofNullable(batchData.getFields())
                            .ifPresentOrElse(fields -> batchData.getValues().add(line), () -> batchData.setFields(line)));
        }

    }

    @Override
    public void importFromDir(String dir) {
        throw new UnsupportedOperationException();
    }
}
