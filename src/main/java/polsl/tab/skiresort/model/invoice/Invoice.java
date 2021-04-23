package polsl.tab.skiresort.model.invoice;

import polsl.tab.skiresort.model.customer.Customer;
import polsl.tab.skiresort.model.invoiceitem.InvoiceItem;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idInvoice;

    private Date invoiceDate;

    private String billingAddress;

    private String billingCity;

    private String billingState;

    private String billingCountry;

    private String billingPostalCode;

    private Float total;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer idCustomer;

    @OneToMany(mappedBy = "idInvoice")
    private List<InvoiceItem> invoiceItemsList;

    private Invoice() {}

    public static InvoiceBuilder builder() {
        return new InvoiceBuilder();
    }

    public static final class InvoiceBuilder {
        private Date invoiceDate;
        private String billingAddress;
        private String billingCity;
        private String billingState;
        private String billingCountry;
        private String billingPostalCode;
        private Float total;
        private Customer idCustomer;
        private List<InvoiceItem> invoiceItemsList;

        public InvoiceBuilder setInvoiceDate(final Date invoiceDate) {
            this.invoiceDate = invoiceDate;
            return this;
        }

        public InvoiceBuilder setBillingAddress(final String billingAddress) {
            this.billingAddress = billingAddress;
            return this;
        }

        public InvoiceBuilder setBillingCity(final String billingCity) {
            this.billingCity = billingCity;
            return this;
        }

        public InvoiceBuilder setBillingState(final String billingState) {
            this.billingState = billingState;
            return this;
        }

        public InvoiceBuilder setBillingCountry(final String billingCountry) {
            this.billingCountry = billingCountry;
            return this;
        }

        public InvoiceBuilder setBillingPostalCode(final String billingPostalCode) {
            this.billingPostalCode = billingPostalCode;
            return this;
        }

        public InvoiceBuilder setTotal(final Float total) {
            this.total = total;
            return this;
        }

        public InvoiceBuilder setIdCustomer(final Customer idCustomer) {
            this.idCustomer = idCustomer;
            return this;
        }

        public InvoiceBuilder setInvoiceItemsList(final List<InvoiceItem> invoiceItemsList) {
            this.invoiceItemsList = invoiceItemsList;
            return this;
        }

        public Invoice build() {
            Invoice invoice = new Invoice();
            invoice.billingAddress = this.billingAddress;
            invoice.invoiceDate = this.invoiceDate;
            invoice.invoiceItemsList = this.invoiceItemsList;
            invoice.billingCity = this.billingCity;
            invoice.billingState = this.billingState;
            invoice.billingCountry = this.billingCountry;
            invoice.billingPostalCode = this.billingPostalCode;
            invoice.total = this.total;
            invoice.idCustomer = this.idCustomer;
            return invoice;
        }
    }

    public Integer getIdInvoice() {
        return idInvoice;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public String getBillingState() {
        return billingState;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public String getBillingPostalCode() {
        return billingPostalCode;
    }

    public Float getTotal() {
        return total;
    }

    public Customer getIdCustomer() {
        return idCustomer;
    }

    public List<InvoiceItem> getInvoiceItemsList() {
        return invoiceItemsList;
    }
}
