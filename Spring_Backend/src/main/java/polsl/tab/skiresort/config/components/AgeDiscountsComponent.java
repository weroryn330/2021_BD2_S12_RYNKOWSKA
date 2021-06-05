package polsl.tab.skiresort.config.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import polsl.tab.skiresort.model.AgeDiscount;
import polsl.tab.skiresort.model.PriceList;
import polsl.tab.skiresort.repository.AgeDiscountRepository;

enum DISCOUNTS_ENUM
{
    CHILD(0,3,100),
    SCHOOL_STUDENT(4,18,30),
    COLLEGE_STUDENT(19,26,51);

    private final Integer ageMin;

    private final Integer ageMax;

    private final Integer percentage;

    DISCOUNTS_ENUM(Integer ageMin, Integer ageMax, Integer percentage) {
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

@Component
public class AgeDiscountsComponent {

    private static final Logger logger = LoggerFactory.getLogger(AgeDiscountsComponent.class);

    private final AgeDiscountRepository ageDiscountRepository;

    private final Boolean recreate;

    AgeDiscountsComponent(final AgeDiscountRepository repository,
                          @Value("${resort.age-discount-price.recreate}") Boolean recreate
    ) {
        this.ageDiscountRepository = repository;
        this.recreate = recreate;
    }

    public Boolean getRecreate() {
        return recreate;
    }

    public Boolean exists(PriceList priceList) {
        return ageDiscountRepository.findByPriceListIdPriceList(priceList).size() == DISCOUNTS_ENUM.values().length;
    }

    public void deleteAgeDiscounts(PriceList priceList) {
        ageDiscountRepository.deleteByPriceListIdPriceList(priceList);
        logger.info("Deleted age discounts");
    }

    public void createAgeDiscounts(PriceList priceList) {
        if (Boolean.TRUE.equals(recreate)) {
            logger.info("Recreating age discounts");
            this.deleteAgeDiscounts(priceList);
        }
        for (DISCOUNTS_ENUM discounts : DISCOUNTS_ENUM.values()) {
            ageDiscountRepository.save(
                    new AgeDiscount(
                            discounts.getAgeMin(),
                            discounts.getAgeMax(),
                            discounts.getPercentage(),
                            priceList
                    )
            );
        }
        logger.info("Age discounts created");
    }
}