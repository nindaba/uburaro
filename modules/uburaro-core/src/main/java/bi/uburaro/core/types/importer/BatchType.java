package bi.uburaro.core.types.importer;

import bi.uburaro.core.types.ItemType;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "batch")
public class BatchType extends ItemType {
    public static final String ITEM_TYPE = "batch";
    public static final String TARGET_ORDER = "targetOrder";
    private String dataFile;
    private String target;
    private Integer targetOrder;

    public Integer getTargetOrder() {
        return targetOrder;
    }

    public void setTargetOrder(Integer targetOrder) {
        this.targetOrder = targetOrder;
    }

    @OneToOne(mappedBy = "batchField", cascade = CascadeType.ALL)
    private BatchLineType fields;
    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
    private final List<BatchLineType> values = new ArrayList<>();

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setFields(BatchLineType fields) {
        this.fields = fields;
    }

    public BatchLineType getFields() {
        return fields;
    }

    public List<BatchLineType> getValues() {
        return values;
    }

    public String getDataFile() {
        return dataFile;
    }

    public void setDataFile(String dataFile) {
        this.dataFile = dataFile;
    }
}
