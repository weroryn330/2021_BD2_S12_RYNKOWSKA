package polsl.tab.skiresort.model;

import org.hibernate.annotations.Immutable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Immutable
@Table(name="ski_report_view")
public class SkiReport {

    @EmbeddedId
    public SkiReportId skiReportId;

    public Timestamp useTimestamp;

    public Integer height;

    public SkiReport() {
    }

    public Timestamp getUseTimestamp() {
        return useTimestamp;
    }

    public void setUseTimestamp(Timestamp useTimestamp) {
        this.useTimestamp = useTimestamp;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public SkiReportId getSkiReportId() {
        return skiReportId;
    }

    public void setSkiReportId(SkiReportId skiReportId) {
        this.skiReportId = skiReportId;
    }

}
