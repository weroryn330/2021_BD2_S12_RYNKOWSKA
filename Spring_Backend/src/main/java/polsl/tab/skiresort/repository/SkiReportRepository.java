package polsl.tab.skiresort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import polsl.tab.skiresort.model.SkiReport;
import polsl.tab.skiresort.model.SkiReportCount;
import polsl.tab.skiresort.model.SkiReportId;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface SkiReportRepository extends JpaRepository<SkiReport, SkiReportId> {

    @Query(nativeQuery = true,
            value = "SELECT s.name AS name, s.height AS height, COUNT(name) AS count " +
            "FROM ski_report_view AS s WHERE s.id_pass=:idPass AND s.use_timestamp BETWEEN :startDate AND :endDate " +
            "GROUP BY s.name, s.id_pass, s.height")
    List<SkiReportCount> findCountByIdPass(@Param("idPass") Integer idPass,
                                           @Param("startDate")Timestamp startDate,
                                           @Param("endDate")Timestamp endDate);
}
