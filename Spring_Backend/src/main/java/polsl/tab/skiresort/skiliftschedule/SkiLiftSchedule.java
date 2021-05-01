package polsl.tab.skiresort.skiliftschedule;

import polsl.tab.skiresort.skilift.SkiLift;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Entity
@Table(name = "ski_lift_schedule")
public class SkiLiftSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idSkiLiftSchedule;

    @NotBlank(message = "Your ski lift schedule start date should not be empty!")
    private Date startDate;

    @NotBlank(message = "Your ski lift schedule end date should not be empty!")
    private Date endDate;

    @NotBlank(message = "Your ski lift schedule open time should not be empty!")
    private Date opensTime;

    @NotBlank(message = "Your ski lift schedule close time should not be empty!")
    private Date closesTime;

    @ManyToOne()
    @JoinColumn(name = "ski_lift_id_ski_lift")
    @Column(nullable = false)
    private SkiLift skiLiftIdSkiLift;
}
