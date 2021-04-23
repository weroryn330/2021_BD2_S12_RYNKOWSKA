package polsl.tab.skiresort.model.invoice;

import polsl.tab.skiresort.model.customer.Customer;
import polsl.tab.skiresort.model.invoiceitem.InvoiceItem;

import java.sql.Date;
import java.util.List;

public class PostInvoiceDto {

    private Date invoiceDate;
    private String billingAddress;
    private String billingCity;
    private String billingState;
    private String billingCountry;
    private String billingPostalCode;
    private Float total;
    private Customer idCustomer;
    private List<InvoiceItem> invoiceItemsList;

    public PostInvoiceDto() {}

    public PostInvoiceDto(Invoice invoice) {
        this.invoiceDate = invoice.getInvoiceDate();
        this.billingAddress = invoice.getBillingAddress();
        this.billingCity = invoice.getBillingCity();
        this.billingState = invoice.getBillingState();
        this.billingCountry = invoice.getBillingCountry();
        this.billingPostalCode = invoice.getBillingPostalCode();
        this.total = invoice.getTotal();
        this.idCustomer = invoice.getIdCustomer();
        this.invoiceItemsList = invoice.getInvoiceItemsList();
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(final Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(final String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(final String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(final String billingState) {
        this.billingState = billingState;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(final String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getBillingPostalCode() {
        return billingPostalCode;
    }

    public void setBillingPostalCode(final String billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(final Float total) {
        this.total = total;
    }

    public Customer getIdCustomer() {
        return idCustomer;
    }

    public List<InvoiceItem> getInvoiceItemsList() {
        return invoiceItemsList;
    }

    public void setInvoiceItemsList(final List<InvoiceItem> invoiceItemsList) {
        this.invoiceItemsList = invoiceItemsList;
    }
}
