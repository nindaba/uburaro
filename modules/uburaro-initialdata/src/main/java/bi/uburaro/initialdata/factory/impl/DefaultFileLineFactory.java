package bi.uburaro.initialdata.factory.impl;

import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.importer.BatchLineType;
import bi.uburaro.initialdata.factory.BatchLineDataFactory;

import static bi.uburaro.initialdata.InitialdataConstants.FIRST_LINE_NUMBER;

public class DefaultFileLineFactory implements BatchLineDataFactory {
    protected Integer currentLine = FIRST_LINE_NUMBER;
    protected final TypeService typeService;

    public DefaultFileLineFactory(TypeService typeService) {
        this.typeService = typeService;
    }

    @Override
    public BatchLineType create(final String value) {
        final BatchLineType line = typeService.create(BatchLineType.class);
        line.setLineNumber(currentLine++);
        line.setValue(value);
        return line;
    }
}
