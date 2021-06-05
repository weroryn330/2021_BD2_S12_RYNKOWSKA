package polsl.tab.skiresort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import polsl.tab.skiresort.model.PriceList;
import polsl.tab.skiresort.model.TimePass;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TimePassRepository extends JpaRepository<TimePass, Integer> {

    @Modifying
    @Transactional
    void deleteByPriceListIdPriceList(PriceList priceList);

    List<TimePass> findByPriceListIdPriceList(PriceList priceList);
}
