package polsl.tab.skiresort.model.spa;

import polsl.tab.skiresort.model.pass.Pass;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "spas")
public class Spa {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Integer idSpa;

    private String name;

    @OneToMany(targetEntity = Pass.class)
    private List<Pass> passList;

    public Integer getIdSpa() {
        return idSpa;
    }

    public void setIdSpa(Integer idSpa) {
        this.idSpa = idSpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pass> getPassList() {
        return passList;
    }

    public void setPassList(List<Pass> passList) {
        this.passList = passList;
    }
}
