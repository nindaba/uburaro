package bi.manager.facade.data;

import bi.uburaro.facade.data.ItemData;

import java.time.LocalDate;
import java.util.Objects;

public class MBOrderData extends ItemData {

    private String orderNumber;
    private int quantity;
    private int unit;
    private LocalDate orderDate;
    private long cost;
    private String itemName;
    private String itemCode;
    private String clientName;
    private String clientCode;
    private boolean paid;

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MBOrderData)) return false;

        MBOrderData that = (MBOrderData) o;

        if (quantity != that.quantity) return false;
        if (unit != that.unit) return false;
        if (cost != that.cost) return false;
        if (paid != that.paid) return false;
        if (!Objects.equals(orderNumber, that.orderNumber)) return false;
        if (!Objects.equals(orderDate, that.orderDate)) return false;
        if (!Objects.equals(itemName, that.itemName)) return false;
        if (!Objects.equals(itemCode, that.itemCode)) return false;
        if (!Objects.equals(clientName, that.clientName)) return false;
        return Objects.equals(clientCode, that.clientCode);
    }

    @Override
    public int hashCode() {
        int result = orderNumber != null ? orderNumber.hashCode() : 0;
        result = 31 * result + quantity;
        result = 31 * result + unit;
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (int) (cost ^ (cost >>> 32));
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (itemCode != null ? itemCode.hashCode() : 0);
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        result = 31 * result + (clientCode != null ? clientCode.hashCode() : 0);
        result = 31 * result + (paid ? 1 : 0);
        return result;
    }
}
