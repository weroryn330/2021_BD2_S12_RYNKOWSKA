package polsl.tab.skiresort.discount;

import polsl.tab.skiresort.pass.Pass;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDiscount;

    @NotBlank(message = "Your percentage should not be empty!")
    private Integer percentage;

    @NotBlank(message = "Your uses should not be empty!")
    private Integer uses;

    @NotBlank(message = "Your lenght should not be empty!")
    private Integer length;

    @OneToOne(mappedBy = "discountIdDiscount")
    private Pass pass;
}
