package polsl.tab.skiresort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polsl.tab.skiresort.model.AgeDiscount;

import java.util.Optional;

@Repository
public interface AgeDiscountRepository extends JpaRepository<AgeDiscount, Integer> {

    Optional<AgeDiscount> findByAgeMinAndAgeMax(Integer ageMin, Integer ageMax);
}
