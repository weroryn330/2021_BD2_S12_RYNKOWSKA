package polsl.tab.skiresort.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import polsl.tab.skiresort.model.PriceList;
import polsl.tab.skiresort.repository.AgeDiscountRepository;
import polsl.tab.skiresort.repository.PriceListRepository;
import polsl.tab.skiresort.repository.QuantityPassRepository;
import polsl.tab.skiresort.repository.TimePassRepository;

import java.sql.Date;
import java.util.ArrayList;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PriceListConfig {

    private static final Date startDate = Date.valueOf("2021-05-26");

    private static final Date endDate = Date.valueOf("2021-09-30");

    private final PriceListRepository priceListRepository;

    private void deletePriceList(Date startDate) {
        priceListRepository.deleteByStartDate(startDate);
    }

    PriceListConfig(final PriceListRepository priceListRepository,
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
                            endDate
                            )
            );
            logger.info("Price List added to database with credentials from properties.");
        }
    }
}