package bi.manager.facade.data;

import bi.manager.core.types.MBCapitalEntryType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBOrderType;
import bi.manager.core.types.enums.MBPaymentModeEnum;
import bi.uburaro.facade.data.ItemData;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MBInvoiceData extends ItemData {
    private  NamedItemData client;
    private Set<MBOrderData> orders = new HashSet<>();

    private long amount;
    private String invoiceNumber;
    private MBPaymentModeEnum paymentMode;
    private String description;

    public NamedItemData getClient() {
        return client;
    }

    public void setClient(NamedItemData client) {
        this.client = client;
    }

    public Set<MBOrderData> getOrders() {
        return orders;
    }

    public void setOrders(Set<MBOrderData> orders) {
        this.orders = orders;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public MBPaymentModeEnum getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(MBPaymentModeEnum paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MBInvoiceData)) return false;

        MBInvoiceData that = (MBInvoiceData) o;

        if (amount != that.amount) return false;
        if (!Objects.equals(invoiceNumber, that.invoiceNumber))
            return false;
        if (paymentMode != that.paymentMode) return false;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        int result = (int) (amount ^ (amount >>> 32));
        result = 31 * result + (invoiceNumber != null ? invoiceNumber.hashCode() : 0);
        result = 31 * result + (paymentMode != null ? paymentMode.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
