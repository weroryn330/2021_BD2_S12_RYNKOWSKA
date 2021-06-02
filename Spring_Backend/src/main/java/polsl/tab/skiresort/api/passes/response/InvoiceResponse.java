package polsl.tab.skiresort.api.passes.response;

import polsl.tab.skiresort.model.Invoice;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceResponse {

    private final Integer id;

    private final Date invoiceDate;

    private final String billingAddress;

    private final String billingCity;

    private final String billingState;

    private final String billingCountry;

    private final String billingPostalCode;

    private final Float total;

    private final List<PassResponse> passesResponseList;

    public InvoiceResponse(Invoice invoice) {
        this.id = invoice.getIdInvoice();
        this.invoiceDate = invoice.getInvoiceDate();
        this.billingAddress = invoice.getBillingAddress();
        this.billingState = invoice.getBillingState();
        this.billingCountry = invoice.getBillingCountry();
        this.billingPostalCode = invoice.getBillingPostalCode();
        this.billingCity = invoice.getBillingCity();
        this.total = invoice.getTotal();
        this.passesResponseList = invoice.getPassList().stream().map(PassResponse::new).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
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

    public List<PassResponse> getPassesResponseList() {
        return passesResponseList;
    }
}
