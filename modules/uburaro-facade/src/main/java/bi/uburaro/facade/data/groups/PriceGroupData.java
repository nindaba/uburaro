package bi.uburaro.facade.data.groups;

public class PriceGroupData extends GroupData{
    public Double getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(Double priceValue) {
        this.priceValue = priceValue;
    }

    private Double priceValue;
}
