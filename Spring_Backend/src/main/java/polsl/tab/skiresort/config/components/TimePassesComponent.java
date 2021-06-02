package polsl.tab.skiresort.config.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import polsl.tab.skiresort.model.PriceList;
import polsl.tab.skiresort.model.TimePass;
import polsl.tab.skiresort.repository.TimePassRepository;

enum TIME_PASS_ENUM
{
    THREE(3,10.0f),
    SIX(6,18.0f),
    TWENTY_FOUR(24,60.0f),
    FORTY_EIGHT(48,114.0f),
    SEVENTY_TWO(72,162.0f),
    ONE_HUNDRED_SIXTY_EIGHT(168,357.0f),
    TWO_HUNDRED_EIGHTY_EIGHT(288,576.0f);

    private final Integer hours;

    private final Float price;

    TIME_PASS_ENUM(Integer hours, Float price) {
        this.hours = hours;
        this.price = price;
    }

    public Integer getHours() {
        return hours;
    }

    public Float getPrice() {
        return price;
    }
}

@Component
public class TimePassesComponent {

    private static final Logger logger = LoggerFactory.getLogger(TimePassesComponent.class);

    private final TimePassRepository timePassRepository;

    private final Boolean recreate;

    TimePassesComponent(final TimePassRepository timePassRepository,
                        @Value("${resort.time-pass-price.recreate}") Boolean recreate
    ) {
        this.timePassRepository = timePassRepository;
        this.recreate = recreate;
    }

    public Boolean getRecreate() {
        return recreate;
    }

    public Boolean exists(PriceList priceList) {
        return timePassRepository.findByPriceListIdPriceList(priceList).size() == TIME_PASS_ENUM.values().length;
    }

    public void deleteTimePasses(PriceList priceList) {
        timePassRepository.deleteByPriceListIdPriceList(priceList);
    }

    public void createTimePasses(PriceList priceList) {
        if (Boolean.TRUE.equals(recreate)) {
            deleteTimePasses(priceList);
        }
        for (TIME_PASS_ENUM pass : TIME_PASS_ENUM.values()) {
            timePassRepository.save(
                    new TimePass(
                            pass.getHours(),
                            pass.getPrice(),
                            priceList
                    )
            );
        }
        logger.info("Time passes created");
    }
}