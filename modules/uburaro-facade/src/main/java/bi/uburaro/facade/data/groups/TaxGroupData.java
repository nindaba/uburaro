package bi.uburaro.facade.data.groups;

public class TaxGroupData extends GroupData{
    private Double taxValue;
    private Integer taxPriority;

    public Double getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(Double taxValue) {
        this.taxValue = taxValue;
    }

    public Integer getTaxPriority() {
        return taxPriority;
    }

    public void setTaxPriority(Integer taxPriority) {
        this.taxPriority = taxPriority;
    }
}
