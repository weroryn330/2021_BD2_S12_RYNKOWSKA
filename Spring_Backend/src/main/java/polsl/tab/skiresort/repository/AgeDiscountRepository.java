package polsl.tab.skiresort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import polsl.tab.skiresort.model.AgeDiscount;
import polsl.tab.skiresort.model.PriceList;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgeDiscountRepository extends JpaRepository<AgeDiscount, Integer> {

    Optional<AgeDiscount> findByAgeMinAndAgeMax(Integer ageMin, Integer ageMax);

    Optional<AgeDiscount> findByAgeMax(Integer ageMax);

    @Modifying
    @Transactional
    void deleteByPriceListIdPriceList(PriceList priceList);

    @Query(
            nativeQuery = true,
            value = "DELETE FROM AGE_DISCOUNTS WHERE age_max = :age_max"
    )
    @Modifying
    @Transactional
    void deleteByAgeMax(@Param("age_max") Integer age_max);

    List<AgeDiscount> findByPriceListIdPriceList(PriceList priceList);
}
