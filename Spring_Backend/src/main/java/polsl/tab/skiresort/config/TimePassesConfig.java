package polsl.tab.skiresort.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import polsl.tab.skiresort.model.TimePass;
import polsl.tab.skiresort.repository.TimePassRepository;


@Configuration
public class TimePassesConfig {

    private static final Integer[] hours = {1,3,6,9,12,24,36,48};

    private static final Float[] price = {20.0f,55.0f,105.0f,165.0f,200.0f,445.0f,680.0f,900.0f};

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
          for(int i=0;i<hours.length;i++)
          {
              TimePass timePass = new TimePass(hours[i],price[i]);
              timePassRepository.save(timePass);
          }
          logger.info("Time passes added to database");
      }
      else {
          logger.info("Time passes exist in database");
      }
    }
}
