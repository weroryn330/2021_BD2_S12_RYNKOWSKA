package polsl.tab.skiresort.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import polsl.tab.skiresort.model.PriceList;
import polsl.tab.skiresort.repository.AgeDiscountRepository;
import polsl.tab.skiresort.repository.PriceListRepository;
import polsl.tab.skiresort.repository.QuantityPassRepository;
import polsl.tab.skiresort.repository.TimePassRepository;

import java.sql.Date;
import java.util.ArrayList;

@Configuration
public class PriceListConfig {

    private static final Date startDate = Date.valueOf("2021-05-26");

    private static final Date endDate = Date.valueOf("2021-09-30");

    private final PriceListRepository priceListRepository;

    private void deletePriceList(Date startDate) {
        priceListRepository.deleteByStartDate(startDate);
    }

    PriceListConfig(final PriceListRepository priceListRepository,
                    final AgeDiscountRepository ageDiscountRepository,
                    final TimePassRepository timePassRepository,
                    final QuantityPassRepository quantityPassRepository,
                    @Value("false") Boolean recreate
    ) {
        this.priceListRepository = priceListRepository;

        if (recreate) {
            deletePriceList(startDate);
        }
        Logger logger = LoggerFactory.getLogger(PriceListConfig.class);
        if (priceListRepository.findByStartDate(startDate).isPresent()) {
            logger.info("Price list exists in database");
        } else {
            priceListRepository.save(
                    new PriceList(
                            startDate,
                            endDate,
                            new ArrayList<>(ageDiscountRepository.findAll()),
                            new ArrayList<>(timePassRepository.findAll()),
                            new ArrayList<>(quantityPassRepository.findAll()))
            );
            logger.info("Price List added to database with credentials from properties.");
        }
    }
}