package polsl.tab.skiresort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polsl.tab.skiresort.model.SkiLift;

@Repository
interface SkiLiftRepository extends JpaRepository<SkiLift, Integer> {
}
