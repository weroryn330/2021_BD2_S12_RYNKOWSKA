package polsl.tab.skiresort.skiliftschedules;

import polsl.tab.skiresort.skilift.SkiLift;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "ski_lift_schedules")
public class SkiLiftSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idSkiLiftSchedule;

    @NotBlank(message = "Your ski lift schedule start date should not be empty!")
    private Date startDate;

    @NotBlank(message = "Your ski lift schedule end date should not be empty!")
    private Date endDate;

    @NotBlank(message = "Your ski lift schedule opens time should not be empty!")
    private Date opensTime;

    @NotBlank(message = "Your ski lift schedule closes time should not be empty!")
    private Date closesTime;

    @ManyToOne
    @JoinColumn(name = "ski_lift_id_ski_lift")
    @NotNull
    private SkiLift skiLiftIdSkiLift;

    public Integer getIdSkiLiftSchedule() {
        return idSkiLiftSchedule;
    }

    public void setIdSkiLiftSchedule(Integer idSkiLiftSchedule) {
        this.idSkiLiftSchedule = idSkiLiftSchedule;
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

    public Date getOpensTime() {
        return opensTime;
    }

    public void setOpensTime(Date opensTime) {
        this.opensTime = opensTime;
    }

    public Date getClosesTime() {
        return closesTime;
    }

    public void setClosesTime(Date closesTime) {
        this.closesTime = closesTime;
    }

    public SkiLift getSkiLiftIdSkiLift() {
        return skiLiftIdSkiLift;
    }

    public void setSkiLiftIdSkiLift(SkiLift skiLiftIdSkiLift) {
        this.skiLiftIdSkiLift = skiLiftIdSkiLift;
    }
}
