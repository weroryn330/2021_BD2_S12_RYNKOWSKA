package polsl.tab.skiresort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polsl.tab.skiresort.model.SkiReport;
import polsl.tab.skiresort.model.SkiReportId;

import java.util.List;

@Repository
public interface SkiReportRepository extends JpaRepository<SkiReport, SkiReportId> {

    List<SkiReport> findBySkiReportId_IdPass(Integer idPass);
}
