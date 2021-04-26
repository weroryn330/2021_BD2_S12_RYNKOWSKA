package polsl.tab.skiresort.model.invoiceitem;

import polsl.tab.skiresort.model.customer.Customer;
import polsl.tab.skiresort.model.invoice.Invoice;
import polsl.tab.skiresort.model.pass.Pass;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "invoice_items")
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private InvoiceItem() {}

    public static InvoiceItemBuilder builder() {
        return new InvoiceItemBuilder();
    }

    public static final class InvoiceItemBuilder {
        private Float unitPrice;
        private Integer quantity;
        private Invoice idInvoice;
        private Pass idPass;

        public InvoiceItemBuilder setUnitPrice(final Float unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public InvoiceItemBuilder setQuantity(final Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public InvoiceItemBuilder setIdInvoice(final Invoice idInvoice) {
            this.idInvoice = idInvoice;
            return this;
        }

        public InvoiceItemBuilder setIdPass(final Pass idPass) {
            this.idPass = idPass;
            return this;
        }

        public InvoiceItem build() {
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.quantity = this.quantity;
            invoiceItem.unitPrice = this.unitPrice;
            invoiceItem.idInvoice = this.idInvoice;
            invoiceItem.idPass = this.idPass;
            return invoiceItem;
        }
    }

    public Integer getIdInvoiceItem() {
        return idInvoiceItem;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Invoice getIdInvoice() {
        return idInvoice;
    }

    public Pass getIdPass() {
        return idPass;
    }
}
