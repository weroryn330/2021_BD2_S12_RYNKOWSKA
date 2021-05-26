package polsl.tab.skiresort.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import polsl.tab.skiresort.model.TimePass;
import polsl.tab.skiresort.repository.TimePassRepository;


@Configuration
public class TimePassesConfig {

    private static final Integer[] hours = {3,6,24,48,72,168,288};

    private static final Float[] price = {10.0f,18.0f,60.0f,114.0f,162.0f,357.0f,576.0f};

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
