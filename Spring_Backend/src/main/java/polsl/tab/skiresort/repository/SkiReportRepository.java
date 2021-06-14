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

    @Query("SELECT s.skiReportId.name AS name, s.height AS height, COUNT(s.skiReportId.name) AS count " +
            "FROM SkiReport AS s WHERE s.skiReportId.idPass=:idPass AND s.useTimestamp BETWEEN :startDate AND :endDate " +
            "GROUP BY s.skiReportId.name, s.skiReportId.idPass, s.height, s.useTimestamp")
    List<SkiReportCount> findCountByIdPass(@Param("idPass") Integer idPass,
                                           @Param("startDate")Timestamp startDate,
                                           @Param("endDate")Timestamp endDate);
}
