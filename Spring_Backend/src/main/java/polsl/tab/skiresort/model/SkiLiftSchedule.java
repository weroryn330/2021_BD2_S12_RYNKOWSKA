package polsl.tab.skiresort.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "ski_lift_schedules")
public class SkiLiftSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idSkiLiftSchedule;

    @NotNull(message = "Your ski lift schedule start date should not be empty!")
    private Date startDate;

    @NotNull(message = "Your ski lift schedule end date should not be empty!")
    private Date endDate;

    @NotNull(message = "Your ski lift schedule opens time should not be empty!")
    private Time opensTime;

    @NotNull(message = "Your ski lift schedule closes time should not be empty!")
    private Time closesTime;

    @ManyToOne
    @JoinColumn(name = "ski_lift_id_ski_lift")
    @NotNull
    private SkiLift skiLiftIdSkiLift;

    public SkiLiftSchedule() {
    }

    public SkiLiftSchedule(Date startDate, Date endDate, Time opensTime, Time closesTime, SkiLift skiLiftIdSkiLift) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.opensTime = opensTime;
        this.closesTime = closesTime;
        this.skiLiftIdSkiLift = skiLiftIdSkiLift;
    }

    public Integer getIdSkiLiftSchedule() {
        return idSkiLiftSchedule;
    }

    void setIdSkiLiftSchedule(Integer idSkiLiftSchedule) {
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

    public Time getOpensTime() {
        return opensTime;
    }

    public void setOpensTime(Time opensTime) {
        this.opensTime = opensTime;
    }

    public Time getClosesTime() {
        return closesTime;
    }

    public void setClosesTime(Time closesTime) {
        this.closesTime = closesTime;
    }

    public SkiLift getSkiLiftIdSkiLift() {
        return skiLiftIdSkiLift;
    }

    public void setSkiLiftIdSkiLift(SkiLift skiLiftIdSkiLift) {
        this.skiLiftIdSkiLift = skiLiftIdSkiLift;
    }
}
