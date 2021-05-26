package polsl.tab.skiresort.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import polsl.tab.skiresort.model.QuantityPass;
import polsl.tab.skiresort.repository.QuantityPassRepository;

@Configuration
public class QuantityPassConfig {

    private static final Integer[] quantity = {10,20,30,60,90};

    private static final Float[] price = {20.0f,38.0f,54.0f,102.0f,144.0f};

    private static final Logger logger = LoggerFactory.getLogger(QuantityPassConfig.class);

    private final QuantityPassRepository quantityPassRepository;

    private void deleteQuantityPasses(){quantityPassRepository.deleteAll();}

    QuantityPassConfig(QuantityPassRepository quantityPassRepository,
                       @Value("false") Boolean recreate
    ) {
        this.quantityPassRepository = quantityPassRepository;

        if(recreate){
            deleteQuantityPasses();
        }

        if(quantityPassRepository.findAll().isEmpty()){
            for(int i=0;i<quantity.length;i++)
            {
                QuantityPass quantityPass = new QuantityPass(quantity[i],price[i]);
                quantityPassRepository.save(quantityPass);
            }
            logger.info("Quantity passes added to database");
        }
        else {
            logger.info("Quantity passes exist in database");
        }
    }
}
