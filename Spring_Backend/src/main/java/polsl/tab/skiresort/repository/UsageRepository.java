package polsl.tab.skiresort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polsl.tab.skiresort.model.SkiLift;
import polsl.tab.skiresort.model.Usage;

import java.sql.Timestamp;

@Repository
public interface UsageRepository extends JpaRepository<Usage, Integer> {

    Integer countAllBySkiLiftIdSkiLiftAndUseTimestampBetween(SkiLift skiLift, Timestamp start, Timestamp end);
}
