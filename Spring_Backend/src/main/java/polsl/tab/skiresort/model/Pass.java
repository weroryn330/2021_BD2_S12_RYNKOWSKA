package polsl.tab.skiresort.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "passes")
public class Pass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idPass;

    @NotBlank(message = "Your pass unit price should not be empty!")
    private Float unitPrice;

    @ManyToOne()
    @JoinColumn(name = "invoices_id_invoice")
    @NotNull
    private Invoice invoicesIdInvoice;

    @NotBlank(message = "Your pass start date should not be empty!")
    private Date startDate;

    @NotBlank(message = "Your pass end date should not be empty!")
    private Date endDate;

    @NotBlank(message = "Your pass first name should not be empty!")
    private String firstName;

    @NotBlank(message = "Your pass last name should not be empty!")
    private String lastName;

    @NotBlank(message = "Your pass birth date should not be empty!")
    private Date birthDate;

    @NotBlank(message = "Your pass uses total should not be empty!")
    private Integer usesTotal;

    @NotBlank(message = "Your pass uses left should not be empty!")
    private Integer usesLeft;

    @ManyToOne
    @JoinColumn(name = "price_list_id_price_list")
    @NotNull
    private PriceList priceList;

    @OneToMany(mappedBy = "passesIdInvoiceItem")
    private List<Usage> usageList;

    public Integer getIdPass() {
        return idPass;
    }

    void setIdPass(Integer idPass) {
        this.idPass = idPass;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Invoice getInvoicesIdInvoice() {
        return invoicesIdInvoice;
    }

    public void setInvoicesIdInvoice(Invoice invoicesIdInvoice) {
        this.invoicesIdInvoice = invoicesIdInvoice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getUsesTotal() {
        return usesTotal;
    }

    public void setUsesTotal(Integer usesTotal) {
        this.usesTotal = usesTotal;
    }

    public Integer getUsesLeft() {
        return usesLeft;
    }

    public void setUsesLeft(Integer usesLeft) {
        this.usesLeft = usesLeft;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }

    public List<Usage> getUsageList() {
        return usageList;
    }

    public void setUsageList(List<Usage> usageList) {
        this.usageList = usageList;
    }
}
