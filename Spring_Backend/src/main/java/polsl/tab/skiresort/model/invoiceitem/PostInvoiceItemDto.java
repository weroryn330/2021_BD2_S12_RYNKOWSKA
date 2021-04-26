package polsl.tab.skiresort.model.invoiceitem;

import polsl.tab.skiresort.model.invoice.Invoice;
import polsl.tab.skiresort.model.pass.Pass;

public class PostInvoiceItemDto {

    private Float unitPrice;
    private Integer quantity;
    private Invoice idInvoice;
    private Pass idPass;

    public PostInvoiceItemDto() {}

    public PostInvoiceItemDto(InvoiceItem invoiceItem) {
        this.unitPrice = invoiceItem.getUnitPrice();
        this.quantity = invoiceItem.getQuantity();
        this.idInvoice = invoiceItem.getIdInvoice();
        this.idPass = invoiceItem.getIdPass();
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(final Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    public Invoice getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(final Invoice idInvoice) {
        this.idInvoice = idInvoice;
    }

    public Pass getIdPass() {
        return idPass;
    }

    public void setIdPass(final Pass idPass) {
        this.idPass = idPass;
    }

    public InvoiceItem toEntity() {
        return InvoiceItem
                .builder()
                .setIdInvoice(this.idInvoice)
                .setIdPass(this.idPass)
                .setQuantity(this.quantity)
                .setUnitPrice(this.unitPrice)
                .build();
    }
}
