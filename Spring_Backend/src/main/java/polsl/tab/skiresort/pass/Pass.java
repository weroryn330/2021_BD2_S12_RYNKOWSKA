package polsl.tab.skiresort.pass;

import polsl.tab.skiresort.discount.Discount;
import polsl.tab.skiresort.invoice.Invoice;
import polsl.tab.skiresort.pricelist.PriceList;
import polsl.tab.skiresort.skiliftpass.SkiLiftPass;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "pass")
public class Pass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idInvoiceItem;

    @NotBlank(message = "Your pass unit price should not be empty!")
    private Float unitPrice;

    @ManyToOne()
    @JoinColumn(name = "invoices_id_invoice")
    @Column(nullable = false)
    private Invoice invoicesIdInvoice;

    @NotBlank(message = "Your pass start date should not be empty!")
    private Date startDate;

    @NotBlank(message = "Your pass end date should not be empty!")
    private Date endDate;

    @OneToOne()
    @JoinColumn(name = "discounts_id_discount")
    @Column(nullable = false)
    private Discount discountIdDiscount;

    @NotBlank(message = "Your pass first name should not be empty!")
    private String firstName;

    @NotBlank(message = "Your pass last name should not be empty!")
    private String lastName;

    @NotBlank(message = "Your pass birth date should not be empty!")
    private Date birthDate;

    @NotBlank(message = "Your pass uses should not be empty!")
    private Integer uses;

    @NotBlank(message = "Your pass uses left should not be empty!")
    private Integer usesLeft;

    @OneToOne(mappedBy = "passIdInvoiceItem")
    private PriceList priceList;

    @OneToMany(mappedBy = "pass")
    private List<SkiLiftPass> skiLiftPassList;

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

    public Discount getDiscountIdDiscount() {
        return discountIdDiscount;
    }

    public void setDiscountIdDiscount(Discount discountIdDiscount) {
        this.discountIdDiscount = discountIdDiscount;
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

    public Integer getUses() {
        return uses;
    }

    public void setUses(Integer uses) {
        this.uses = uses;
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

    public List<SkiLiftPass> getSkiLiftPassList() {
        return skiLiftPassList;
    }

    public void setSkiLiftPassList(List<SkiLiftPass> skiLiftPassList) {
        this.skiLiftPassList = skiLiftPassList;
    }
}
