package polsl.tab.skiresort.skilift;

import polsl.tab.skiresort.pass.Pass;
import polsl.tab.skiresort.skiliftschedule.SkiLiftSchedule;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "ski_lift")
public class SkiLift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idSkiLift;

    @NotBlank(message = "Your ski lift name should not be empty!")
    private String name;

    @NotBlank(message = "Your ski lift height should not be empty!")
    private Integer height;

    @NotBlank(message = "Your ski lift is open flag should not be empty!")
    private Character isOpened;


    @OneToMany(mappedBy = "skiLiftIdSkiLift")
    private List<SkiLiftSchedule> skiLiftScheduleList;

    @ManyToMany(mappedBy = "skiLiftList")
    private List<Pass> passList;

    public Integer getIdSkiLift() {
        return idSkiLift;
    }

    public void setIdSkiLift(Integer idSkiLift) {
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

    public Character getIsOpened() {
        return isOpened;
    }

    public void setIsOpened(Character isOpened) {
        this.isOpened = isOpened;
    }

    public List<SkiLiftSchedule> getSkiLiftScheduleList() {
        return skiLiftScheduleList;
    }

    public void setSkiLiftScheduleList(List<SkiLiftSchedule> skiLiftScheduleList) {
        this.skiLiftScheduleList = skiLiftScheduleList;
    }

    public List<Pass> getPassList() {
        return passList;
    }

    public void setPassList(List<Pass> passList) {
        this.passList = passList;
    }
}
