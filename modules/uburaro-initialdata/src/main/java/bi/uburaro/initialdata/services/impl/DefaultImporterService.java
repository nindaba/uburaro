package bi.uburaro.initialdata.services.impl;

import bi.uburaro.core.repositories.BatchRepository;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.importer.BatchLineType;
import bi.uburaro.core.types.importer.BatchType;
import bi.uburaro.initialdata.factory.BatchLineDataFactory;
import bi.uburaro.initialdata.factory.impl.DefaultFileLineFactory;
import bi.uburaro.initialdata.services.DataImporterService;
import bi.uburaro.initialdata.strategies.DataBatchProcessorStrategy;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static bi.uburaro.initialdata.InitialdataConstants.*;

public class DefaultImporterService extends SimpleFileVisitor<Path> implements DataImporterService {
    public static final String ORDER_KEY_PREFIX = "order.";
    protected final TypeService typeService;
    protected final BatchRepository batchRepository;
    protected final DataBatchProcessorStrategy dataBatchProcessorStrategy;

    protected final Environment environment;
    private static final Logger log = LogManager.getLogger();

    public DefaultImporterService(TypeService typeService, BatchRepository batchRepository, DataBatchProcessorStrategy dataBatchProcessorStrategies, Environment environment) {
        this.typeService = typeService;
        this.batchRepository = batchRepository;
        this.dataBatchProcessorStrategy = dataBatchProcessorStrategies;
        this.environment = environment;
    }


    @Override
    public void importCurrent() {
        final String currentPath = environment.getProperty(INITIAL_PATCHES_CURRENT);
        startWalkingFiles(currentPath);
    }

    protected void startWalkingFiles(String currentPath) {
        try {
            log.info("Running imports for  {}", currentPath);
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

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        final Integer repeat = environment.getProperty(DATA_REPEAT_NUMBER, Integer.class, 1);

        for (int i = 0; i < repeat; i++) {
            log.info("Processing imports in {} for {} time", dir, i);

            batchRepository.findAll(Sort.by(BatchType.TARGET_ORDER)).stream()
                    .peek(batchType -> batchType.getValues().stream().peek(value -> value.setFailed(false)))
                    .peek(dataBatchProcessorStrategy::processBatch)
                    .filter(batchData -> batchData.getValues().isEmpty())
                    .forEach(batchRepository::delete);
        }
        return super.postVisitDirectory(dir, exc);
    }

    protected FileVisitResult processFile(Path filePath) {
        String dataPath = environment.getProperty(INITIAL_DATA_PATH);
        String fileName = StringUtils.remove(filePath.toString(), dataPath);
        try {
            log.info("Importing Data from {}", fileName);
            final List<BatchType> batches = new ArrayList<>();
            final BatchLineDataFactory lineFactory = new DefaultFileLineFactory(typeService);
            Files.newBufferedReader(filePath).lines()
                    .map(lineFactory::create)
                    .filter(line -> StringUtils.isNoneEmpty(StringUtils.remove(line.getValue(), DELIMITER)))
                    .forEach(line -> addLine(line, batches));

            batches.stream()
                    .filter(batchData -> CollectionUtils.isNotEmpty(batchData.getValues()))
                    .peek(batchData -> batchData.setDataFile(fileName))
                    .forEach(typeService::save);
        } catch (IOException e) {
            log.error("Error happened while importing file {}", fileName);
        }
        return FileVisitResult.CONTINUE;
    }

    private void addLine(final BatchLineType line, final List<BatchType> batches) {
        if (StringUtils.contains(line.getValue(), TYPE_PREFIX)) {
            final BatchType batchData = typeService.create(BatchType.class);
            final String target = StringUtils.split(line.getValue().replaceFirst(TYPE_PREFIX_REGEXP, ""), DELIMITER)[0];
            final Integer order = environment.getProperty(ORDER_KEY_PREFIX + target, Integer.class, 0);
            batchData.setTarget(target);
            batchData.setTargetOrder(order);
            batches.add(batchData);
        } else {
            batches.stream()
                    .skip(batches.size() - 1)
                    .forEach(batchData -> Optional.ofNullable(batchData.getFields())
                            .ifPresentOrElse(fields -> {
                                batchData.getValues().add(line);
                                line.setBatch(batchData);
                            }, () -> {
                                batchData.setFields(line);
                                line.setBatchField(batchData);
                            }));
        }

    }

    @Override
    public void importFromDir(String dir) {
        throw new UnsupportedOperationException();
    }
}
