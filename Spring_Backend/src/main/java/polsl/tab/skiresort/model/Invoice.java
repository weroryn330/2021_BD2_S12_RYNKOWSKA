package polsl.tab.skiresort.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idInvoice;

    @NotNull(message = "Your invoice date should not be empty!")
    private Date invoiceDate;

    @NotBlank(message = "Your invoice billing address should not be empty!")
    private String billingAddress;

    @NotBlank(message = "Your invoice billing city should not be empty!")
    private String billingCity;

    @NotBlank(message = "Your invoice billing state should not be empty!")
    private String billingState;

    @NotBlank(message = "Your invoice billing country should not be empty!")
    private String billingCountry;

    @NotBlank(message = "Your invoice billing postal code should not be empty!")
    private String billingPostalCode;

    @NotNull(message = "Your invoice total should not be empty!")
    private Float total;

    @ManyToOne
    @JoinColumn(name = "users_id_user")
    @NotNull
    private User userIdUser;

    @OneToMany(mappedBy = "invoicesIdInvoice")
    private List<Pass> passList;

    public Invoice() {}

    public Invoice(Date invoiceDate,
                   String billingAddress,
                   String billingCity,
                   String billingState,
                   String billingCountry,
                   String billingPostalCode,
                   Float total) {
        this.invoiceDate = invoiceDate;
        this.billingAddress = billingAddress;
        this.billingCity = billingCity;
        this.billingState = billingState;
        this.billingCountry = billingCountry;
        this.billingPostalCode = billingPostalCode;
        this.total = total;
    }

    public Integer getIdInvoice() {
        return idInvoice;
    }

    void setIdInvoice(Integer idInvoice) {
        this.idInvoice = idInvoice;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getBillingPostalCode() {
        return billingPostalCode;
    }

    public void setBillingPostalCode(String billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public User getUserIdUser() {
        return userIdUser;
    }

    public void setUserIdUser(User userIdUser) {
        this.userIdUser = userIdUser;
    }

    public List<Pass> getPassList() {
        if (this.passList == null) {
            this.passList = new ArrayList<>();
        }
        return passList;
    }

    public void setPassList(List<Pass> passList) {
        this.passList = passList;
    }
}
