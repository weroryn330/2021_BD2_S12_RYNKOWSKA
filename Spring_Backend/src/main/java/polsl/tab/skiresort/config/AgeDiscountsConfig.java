package polsl.tab.skiresort.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import polsl.tab.skiresort.model.AgeDiscount;
import polsl.tab.skiresort.repository.AgeDiscountRepository;
import polsl.tab.skiresort.repository.PriceListRepository;

import java.sql.Date;

@Configuration
@Order
public class AgeDiscountsConfig {

    public enum DISCOUNTS
    {
        CHILD(0,3,100),
        SCHOOL_STUDENT(4,18,30),
        COLLEGE_STUDENT(19,26,51);

        private final Integer ageMin;

        private final Integer ageMax;

        private final Integer percentage;

        DISCOUNTS(Integer ageMin,Integer ageMax,Integer percentage) {
            this.ageMin = ageMin;
            this.ageMax = ageMax;
            this.percentage = percentage;
        }

        public Integer getAgeMin() {
            return ageMin;
        }

        public Integer getAgeMax() {
            return ageMax;
        }

        public Integer getPercentage() {
            return percentage;
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(AgeDiscountsConfig.class);

    private final AgeDiscountRepository ageDiscountRepository;

    private void deleteAgeDiscounts(){ageDiscountRepository.deleteAll();}

    AgeDiscountsConfig(final AgeDiscountRepository repository,
                       final PriceListRepository priceListRepository,
                       @Value("${resort.agediscount.recreate}") Boolean recreate
    ) {
        this.ageDiscountRepository = repository;

        if(recreate)
        {
            deleteAgeDiscounts();
        }

        if(ageDiscountRepository.findAll().isEmpty()) {
            for(DISCOUNTS d : DISCOUNTS.values())
            {
                //priceListRepository.findByStartDate(Date.valueOf("2021-05-26")).get()
                var ageDiscount = new AgeDiscount(d.getAgeMin(),d.getAgeMax(),d.getPercentage(),priceListRepository.findByStartDate(Date.valueOf("2021-05-26")).get());
                ageDiscountRepository.save(ageDiscount);
            }

            logger.info("Age discounts added to database");
        } else {
            logger.info("Age discounts exist in database");
        }
    }
}