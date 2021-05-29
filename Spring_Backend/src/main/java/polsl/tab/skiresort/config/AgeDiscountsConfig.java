package polsl.tab.skiresort.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import polsl.tab.skiresort.model.AgeDiscount;
import polsl.tab.skiresort.repository.AgeDiscountRepository;

@Configuration
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
    //Table include respectively child, school student and college student
//    private static final Integer[] ageMin = {0,4,19};
//
//    private static final Integer[] ageMax = {3,18,26};
//
//    private static final Integer[] percentage = {100,30,51};

    private static final Logger logger = LoggerFactory.getLogger(AgeDiscountsConfig.class);

    private final AgeDiscountRepository ageDiscountRepository;

    private void deleteAgeDiscounts(){ageDiscountRepository.deleteAll();}

    AgeDiscountsConfig(final AgeDiscountRepository ageDiscountRepository,
                       @Value("false") Boolean recreate
    ) {
        this.ageDiscountRepository = ageDiscountRepository;

        if(recreate)
        {
            deleteAgeDiscounts();
        }

        if(ageDiscountRepository.findAll().isEmpty()) {
            for(DISCOUNTS d : DISCOUNTS.values())
            {
                AgeDiscount ageDiscount = new AgeDiscount(d.getAgeMin(),d.getAgeMax(),d.getPercentage());
                ageDiscountRepository.save(ageDiscount);
            }

            logger.info("Age discounts added to database");
        } else {
            logger.info("Age discounts exist in database");
        }
    }
}