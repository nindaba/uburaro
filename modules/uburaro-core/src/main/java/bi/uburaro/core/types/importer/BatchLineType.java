package bi.uburaro.core.types.importer;

import bi.uburaro.core.types.ItemType;

import javax.persistence.*;

@Entity(name = "batchLine")
public class BatchLineType extends ItemType {
    public static final String ITEM_TYPE = "batchLine";
    private Integer lineNumber;
    private String value;
    private boolean failed;

    @OneToOne(cascade = CascadeType.ALL)
    private BatchType batchField;
    @ManyToOne(cascade = CascadeType.ALL)
    private BatchType batch;

    public BatchType getBatchField() {
        return batchField;
    }

    public void setBatchField(BatchType batchField) {
        this.batchField = batchField;
    }

    public BatchType getBatch() {
        return batch;
    }

    public void setBatch(BatchType batch) {
        this.batch = batch;
    }

    public BatchLineType(Integer lineNumber, String value) {
        this.lineNumber = lineNumber;
        this.value = value;
    }

    public BatchLineType() {

    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void setValue(String value) {
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
