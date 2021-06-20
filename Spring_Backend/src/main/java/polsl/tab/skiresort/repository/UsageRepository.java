package polsl.tab.skiresort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import polsl.tab.skiresort.model.SkiLift;
import polsl.tab.skiresort.model.Usage;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface UsageRepository extends JpaRepository<Usage, Integer> {

    Integer countAllBySkiLiftIdSkiLiftAndUseTimestampBetween(SkiLift skiLift, Timestamp start, Timestamp end);

    @Query(nativeQuery = true,
            value = "SELECT * FROM Usages u WHERE u.use_timestamp BETWEEN :startDate AND :endDate AND u.success_flag='1'")
    List<Usage> findAllWithUseTimestampBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
