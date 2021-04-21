package polsl.tab.skiresort.model.invoiceitem;

import polsl.tab.skiresort.model.customer.Customer;
import polsl.tab.skiresort.model.invoice.Invoice;
import polsl.tab.skiresort.model.pass.Pass;

import javax.persistence.*;

@Entity
@Table(name = "invoice_items")
public class InvoiceItem {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Integer idInvoiceItem;

    private Float unitPrice;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id_invoice")
    private Invoice idInvoice;

    @ManyToOne
    @JoinColumn(name = "id_pass")
    private Pass idPass;

    public Integer getIdInvoiceItem() {
        return idInvoiceItem;
    }

    public void setIdInvoiceItem(Integer idInvoiceItem) {
        this.idInvoiceItem = idInvoiceItem;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Invoice getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Invoice idInvoice) {
        this.idInvoice = idInvoice;
    }

    public Pass getIdPass() {
        return idPass;
    }

    public void setIdPass(Pass idPass) {
        this.idPass = idPass;
    }
}
