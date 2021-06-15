package polsl.tab.skiresort.model;

import polsl.tab.skiresort.api.passes.request.PassRequest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;
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

    private Timestamp startDate;

    private Timestamp endDate;

    @NotBlank(message = "Your pass first name should not be empty!")
    private String firstName;

    @NotBlank(message = "Your pass last name should not be empty!")
    private String lastName;

    @NotNull(message = "Your pass birth date should not be empty!")
    private Date birthDate;

    private Integer usesTotal;

    private Integer usesLeft;

    private Boolean blocked;

    @ManyToOne
    @JoinColumn(name = "price_list_id_price_list")
    private PriceList priceList;

    @OneToMany(mappedBy = "passesIdInvoiceItem")
    private List<Usage> usageList;

    public Pass(Float unitPrice,
                Timestamp startDate,
                Timestamp endDate,
                String firstName,
                String lastName,
                Date birthDate,
                PriceList priceList,
                Invoice invoice
    ) {
        this.unitPrice = unitPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.priceList = priceList;
        this.invoicesIdInvoice = invoice;
    }

    public Pass(Float unitPrice,
                String firstName,
                String lastName,
                Date birthDate,
                Integer usesTotal,
                Integer usesLeft,
                PriceList priceList,
                Invoice invoice
    ) {
        this.unitPrice = unitPrice;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.usesTotal = usesTotal;
        this.usesLeft = usesLeft;
        this.priceList = priceList;
        this.invoicesIdInvoice = invoice;
    }

    public Pass() {

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

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
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

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public static Pass from(PassRequest request, Invoice invoice, PriceList priceList) {
        var pass = new Pass();
        pass.setUnitPrice(request.getUnitPrice());
        pass.setInvoicesIdInvoice(invoice);
        pass.setStartDate(request.getStartDate());
        pass.setEndDate(request.getEndDate());
        pass.setFirstName(request.getFirstName());
        pass.setLastName(request.getLastName());
        pass.setBirthDate(request.getBirthDate());
        pass.setUsesTotal(request.getUsesTotal());
        pass.setUsesLeft(request.getUsesTotal());
        pass.setPriceList(priceList);
        return pass;
    }
}
