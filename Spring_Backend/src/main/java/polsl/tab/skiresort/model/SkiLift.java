package polsl.tab.skiresort.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "ski_lifts")
public class SkiLift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idSkiLift;

    @NotBlank(message = "Your ski lift name should not be empty!")
    private String name;

    @NotNull
    private Integer height;

    @NotNull
    private Boolean isOpened;

    @OneToMany(mappedBy = "skiLiftIdSkiLift")
    private List<Usage> usageList;

    @OneToMany(mappedBy = "skiLiftIdSkiLift")
    private List<SkiLiftSchedule> skiLiftScheduleList;

    public Integer getIdSkiLift() {
        return idSkiLift;
    }

    void setIdSkiLift(Integer idSkiLift) {
        this.idSkiLift = idSkiLift;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Boolean getIsOpened() {
        return isOpened;
    }

    public void setIsOpened(Boolean isOpened) {
        this.isOpened = isOpened;
    }

    public List<Usage> getUsageList() {
        return usageList;
    }

    public void setUsageList(List<Usage> usageList) {
        this.usageList = usageList;
    }

    public List<SkiLiftSchedule> getSkiLiftScheduleList() {
        return skiLiftScheduleList;
    }

    public void setSkiLiftScheduleList(List<SkiLiftSchedule> skiLiftScheduleList) {
        this.skiLiftScheduleList = skiLiftScheduleList;
    }
}
