package polsl.tab.skiresort.model.invoice;

import polsl.tab.skiresort.model.customer.Customer;
import polsl.tab.skiresort.model.invoiceitem.InvoiceItem;

import java.sql.Date;
import java.util.List;

public class GetInvoiceDto {

    private final Integer idInvoice;
    private final Date invoiceDate;
    private final String billingAddress;
    private final String billingCity;
    private final String billingState;
    private final String billingCountry;
    private final String billingPostalCode;
    private final Float total;
    private final Customer idCustomer;
    private final List<InvoiceItem> invoiceItemsList;

    public GetInvoiceDto(Invoice invoice) {
        this.idInvoice = invoice.getIdInvoice();
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
