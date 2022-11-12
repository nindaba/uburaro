package bi.uburaro.initialdata.factory.impl;

import bi.uburaro.initialdata.data.BatchLineData;
import bi.uburaro.initialdata.factory.BatchLineDataFactory;

import static bi.uburaro.initialdata.InitialdataConstants.FIRST_LINE_NUMBER;

public class DefaultFileLineFactory implements BatchLineDataFactory {
    protected Integer currentLine = FIRST_LINE_NUMBER;

    @Override
    public BatchLineData create(String value) {
        return new BatchLineData(currentLine++, value);
    }
}
