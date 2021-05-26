package polsl.tab.skiresort.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import polsl.tab.skiresort.model.AgeDiscount;
import polsl.tab.skiresort.repository.AgeDiscountRepository;

@Configuration
public class AgeDiscountsConfig {

    //Table include respectively child, school student and college student
    private static final Integer[] ageMin = {0,4,19};

    private static final Integer[] ageMax = {3,18,26};

    private static final Integer[] percentage = {100,30,51};

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
            AgeDiscount child = new AgeDiscount(ageMin[0],ageMax[0],percentage[0]);
            AgeDiscount schoolStudent = new AgeDiscount(ageMin[1],ageMax[1],percentage[1]);
            AgeDiscount collegeStudent = new AgeDiscount(ageMin[2],ageMax[2],percentage[2]);

            ageDiscountRepository.save(child);
            ageDiscountRepository.save(schoolStudent);
            ageDiscountRepository.save(collegeStudent);
            logger.info("Age discounts added to database");
        } else {
            logger.info("Age discounts exist in database");
        }
    }
}
