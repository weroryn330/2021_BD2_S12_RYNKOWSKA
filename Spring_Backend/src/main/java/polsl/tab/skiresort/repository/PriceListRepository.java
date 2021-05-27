package polsl.tab.skiresort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import polsl.tab.skiresort.model.PriceList;

import java.util.Optional;

@Repository
public interface PriceListRepository extends JpaRepository<PriceList, Integer> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM price_lists WHERE start_date < NOW() AND end_date > NOW()"
    )
    Optional<PriceList> findCurrentPriceList();
}
