package polsl.tab.skiresort.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import polsl.tab.skiresort.model.QuantityPass;
import polsl.tab.skiresort.repository.PriceListRepository;
import polsl.tab.skiresort.repository.QuantityPassRepository;

import java.sql.Date;

@Configuration
public class QuantityPassConfig {

    public enum QUANTITY_PASS_ENUM
    {
        TEN(10,20.0f),
        TWENTY(20,38.0f),
        THIRTY(30,54.0f),
        SIXTY(60,102.0f),
        NINETY(90,144.0f);

        private final Integer quantity;

        private final Float price;

        QUANTITY_PASS_ENUM(Integer quantity,Float price) {
            this.quantity = quantity;
            this.price = price;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public Float getPrice() {
            return price;
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(QuantityPassConfig.class);

    private final QuantityPassRepository quantityPassRepository;

    private void deleteQuantityPasses(){quantityPassRepository.deleteAll();}

    QuantityPassConfig(QuantityPassRepository quantityPassRepository,
                       final PriceListRepository priceListRepository,
                       @Value("false") Boolean recreate
    ) {
        this.quantityPassRepository = quantityPassRepository;

        if(recreate){
            deleteQuantityPasses();
        }

        if(quantityPassRepository.findAll().isEmpty()){
            for(QUANTITY_PASS_ENUM q : QUANTITY_PASS_ENUM.values())
            {
                var quantityPass = new QuantityPass(q.getQuantity(),q.getPrice(),priceListRepository.findByStartDate(Date.valueOf("2021-05-26")).get());
                quantityPassRepository.save(quantityPass);
            }
            logger.info("Quantity passes added to database");
        }
        else {
            logger.info("Quantity passes exist in database");
        }
    }
}