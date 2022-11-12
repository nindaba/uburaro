package bi.uburaro.initialdata.data;

public class BatchLineData {
    private final Integer lineNumber;
    private final String value;
    private boolean failed;

    public BatchLineData(Integer lineNumber, String value) {
        this.lineNumber = lineNumber;
        this.value = value;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }
    public String getValue() {
        return value;
    }

    public boolean isFailed() {
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }
}
