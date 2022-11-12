package bi.uburaro.initialdata.data;

import java.util.ArrayList;
import java.util.List;

public class BatchData {
    private String dataFile;
    private String target;
    private BatchLineData fields;
    private final List<BatchLineData> values = new ArrayList<>();

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setFields(BatchLineData fields) {
        this.fields = fields;
    }

    public BatchLineData getFields() {
        return fields;
    }

    public List<BatchLineData> getValues() {
        return values;
    }

    public String getDataFile() {
        return dataFile;
    }

    public void setDataFile(String dataFile) {
        this.dataFile = dataFile;
    }
}
