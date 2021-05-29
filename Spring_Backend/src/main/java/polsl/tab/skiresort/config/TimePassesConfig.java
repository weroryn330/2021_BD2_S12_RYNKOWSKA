package polsl.tab.skiresort.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import polsl.tab.skiresort.model.TimePass;
import polsl.tab.skiresort.repository.TimePassRepository;


@Configuration
public class TimePassesConfig {

    public enum TIME_PASS_ENUM
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

        TIME_PASS_ENUM(Integer hours,Float price) {
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

//    private static final Integer[] hours = {3,6,24,48,72,168,288};
//
//    private static final Float[] price = {10.0f,18.0f,60.0f,114.0f,162.0f,357.0f,576.0f};

    private static final Logger logger = LoggerFactory.getLogger(TimePassesConfig.class);

    private final TimePassRepository timePassRepository;

    private void deleteTimePasses(){timePassRepository.deleteAll();}

    TimePassesConfig(TimePassRepository timePassRepository,
                     @Value("false") Boolean recreate
    ) {
        this.timePassRepository = timePassRepository;

        if(recreate){
            deleteTimePasses();
        }

        if(timePassRepository.findAll().isEmpty()){

            for(TIME_PASS_ENUM t : TIME_PASS_ENUM.values())
            {
                TimePass timePass = new TimePass(t.getHours(),t.getPrice());
                timePassRepository.save(timePass);
            }
            logger.info("Time passes added to database");
        }
        else {
            logger.info("Time passes exist in database");
        }
    }
}