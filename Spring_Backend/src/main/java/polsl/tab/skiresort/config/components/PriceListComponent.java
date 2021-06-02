package polsl.tab.skiresort.config.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import polsl.tab.skiresort.model.PriceList;
import polsl.tab.skiresort.repository.PriceListRepository;

import java.sql.Date;

enum PRICE_LIST_DATES_ENUM {
    START_DATE(Date.valueOf("2021-05-26")),
    END_DATE(Date.valueOf("2021-09-30"));

    private final Date date;

    PRICE_LIST_DATES_ENUM(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}

@Component
public class PriceListComponent {

    private final PriceListRepository priceListRepository;

    private static final Logger logger = LoggerFactory.getLogger(PriceListComponent.class);

    private final Boolean recreate;

    PriceListComponent(final PriceListRepository priceListRepository,
                       @Value("${resort.price-list.recreate}") Boolean recreate
    ) {
        this.priceListRepository = priceListRepository;
        this.recreate = recreate;
    }

    public Boolean getRecreate() {
        return recreate;
    }

    public void deletePriceList() {
        priceListRepository.deleteByStartDateAndEndDate(
                PRICE_LIST_DATES_ENUM.START_DATE.getDate(),
                PRICE_LIST_DATES_ENUM.END_DATE.getDate()
        );
        logger.info("Price list deleted");
    }

    public PriceList findConfigPriceListByEnumDates() {
        return priceListRepository.findByStartDateAndEndDate(
                PRICE_LIST_DATES_ENUM.START_DATE.getDate(),
                PRICE_LIST_DATES_ENUM.END_DATE.getDate()
        ).orElseThrow(() -> new NullPointerException(
                "Price list with enum start date and end date has not been created"
        ));
    }

    public PriceList createPriceList() {
        logger.info("Creating price list");
        return priceListRepository.save(
                new PriceList(
                        PRICE_LIST_DATES_ENUM.START_DATE.getDate(),
                        PRICE_LIST_DATES_ENUM.END_DATE.getDate()
                )
        );
    }
}