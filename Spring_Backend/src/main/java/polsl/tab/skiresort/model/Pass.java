package polsl.tab.skiresort.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "passes")
public class Pass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPass;

    @NotNull(message = "Your pass unit price should not be empty!")
    private Float unitPrice;

    @ManyToOne()
    @JoinColumn(name = "invoices_id_invoice")
    private Invoice invoicesIdInvoice;

    private Date startDate;

    private Date endDate;

    @NotBlank(message = "Your pass first name should not be empty!")
    private String firstName;

    @NotBlank(message = "Your pass last name should not be empty!")
    private String lastName;

    @NotNull(message = "Your pass birth date should not be empty!")
    private Date birthDate;

    private Integer usesTotal;

    private Integer usesLeft;

    @ManyToOne
    @JoinColumn(name = "price_list_id_price_list")
    private PriceList priceList;

    @OneToMany(mappedBy = "passesIdInvoiceItem")
    private List<Usage> usageList;

    public Pass() {
    }

    public Pass(Float unitPrice,
                Date startDate,
                Date endDate,
                String firstName,
                String lastName,
                Date birthDate,
                PriceList priceList
    ) {
        this.unitPrice = unitPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.priceList = priceList;
    }

    public Pass(Float unitPrice,
                String firstName,
                String lastName,
                Date birthDate,
                Integer usesTotal,
                Integer usesLeft,
                PriceList priceList
    ) {
        this.unitPrice = unitPrice;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.usesTotal = usesTotal;
        this.usesLeft = usesLeft;
        this.priceList = priceList;
    }

    public Pass(Pass pass)
    {
        this.idPass = pass.getIdPass();
        this.unitPrice = pass.getUnitPrice();
        this.startDate = pass.getStartDate();
        this.endDate = pass.getEndDate();
        this.firstName = pass.getFirstName();
        this.lastName = pass.getLastName();
        this.birthDate = pass.getBirthDate();
        this.usesTotal = pass.getUsesTotal();
        this.usesLeft = pass.getUsesLeft();
    }
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
        if (this.usageList == null){
            this.usageList = new ArrayList<>();
        }
        return usageList;
    }

    public void setUsageList(List<Usage> usageList) {
        this.usageList = usageList;
    }
}
