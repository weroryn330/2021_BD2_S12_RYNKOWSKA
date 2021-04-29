package polsl.tab.skiresort.pass;

import polsl.tab.skiresort.discount.Discount;
import polsl.tab.skiresort.invoice.Invoice;
import polsl.tab.skiresort.pricelist.PriceList;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Entity
@Table(name = "pass")
public class Pass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idInvoiceItem;

    @NotBlank(message = "Your unit price should not be empty!")
    private Float unitPrice;


    @ManyToOne()
    @JoinColumn(name = "invoices_id_invoice")
    @Column(nullable = false)
    private Invoice invoicesIdInvoice;

    @NotBlank(message = "Your start date should not be empty!")
    private Date startDate;

    @NotBlank(message = "Your end date should not be empty!")
    private Date endDate;

    @OneToOne()
    @JoinColumn(name = "discounts_id_discount")
    @Column(nullable = false)
    private Discount discountIdDiscount;

    @NotBlank(message = "Your first name should not be empty!")
    private String firstName;

    @NotBlank(message = "Your last name should not be empty!")
    private String lastName;

    @NotBlank(message = "Your birth date should not be empty!")
    private Date birthDate;

    @NotBlank(message = "Your uses should not be empty!")
    private Integer uses;

    @NotBlank(message = "Your uses left should not be empty!")
    private Integer usesLeft;

    @OneToOne(mappedBy = "passIdInvoiceItem")
    private PriceList priceList;

//    @OneToOne(mappedBy = )
}
