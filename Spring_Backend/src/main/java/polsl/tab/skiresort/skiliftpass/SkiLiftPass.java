package polsl.tab.skiresort.skiliftpass;

import polsl.tab.skiresort.pass.Pass;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "ski_lift_pass")
public class SkiLiftPass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Pass passIdInvoiceItem;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer skiLiftIdSkiLift;

    @NotBlank(message = "Your uses should not be empty!")
    private Integer uses;
    }
}
