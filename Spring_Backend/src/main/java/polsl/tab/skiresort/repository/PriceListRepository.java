package polsl.tab.skiresort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import polsl.tab.skiresort.model.PriceList;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.Optional;

@Repository
public interface PriceListRepository extends JpaRepository<PriceList, Integer> {

    Optional<PriceList> findByStartDate(Date startDate);

    @Query(
            nativeQuery = true,
            value = "DELETE FROM PRICE_LISTS WHERE start_date = :start_date"
    )
    @Modifying
    @Transactional
    void deleteByStartDate(@Param("2021-05-26") Date start_date);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM price_lists WHERE start_date < NOW() AND end_date > NOW()"
    )
    Optional<PriceList> findCurrentPriceList();
}
