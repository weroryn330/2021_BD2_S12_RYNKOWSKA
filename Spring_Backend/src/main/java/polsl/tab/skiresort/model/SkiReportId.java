package polsl.tab.skiresort.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SkiReportId implements Serializable{

    private Integer idPass;
    private String name;

    public SkiReportId() {
    }

    public Integer getIdPass() {
        return idPass;
    }

    public void setIdPass(Integer idPass) {
        this.idPass = idPass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
