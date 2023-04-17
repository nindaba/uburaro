package bi.uburaro.initialdata.factory;

import bi.uburaro.core.types.importer.BatchLineType;

public interface BatchLineDataFactory {
    /**
     * Creates a file line with line number which is incremented each time a line is created
     *
     * @param value of the line
     * @return fileLine of FileLineData
     */
    BatchLineType create(String value);
}
