package polsl.tab.skiresort.config.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import polsl.tab.skiresort.model.PriceList;
import polsl.tab.skiresort.model.QuantityPass;
import polsl.tab.skiresort.repository.QuantityPassRepository;

enum QUANTITY_PASS_ENUM
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

@Component
public class QuantityPassComponent {

    private static final Logger logger = LoggerFactory.getLogger(QuantityPassComponent.class);

    private final QuantityPassRepository quantityPassRepository;

    private final Boolean recreate;

    QuantityPassComponent(final QuantityPassRepository quantityPassRepository,
                          @Value("${resort.quantity-pass-price.recreate}") Boolean recreate
    ) {
        this.quantityPassRepository = quantityPassRepository;
        this.recreate = recreate;
    }

    public Boolean getRecreate() {
        return recreate;
    }

    public Boolean exists(PriceList priceList) {
        return quantityPassRepository.
                findByPriceListIdPriceList(priceList).size() == QUANTITY_PASS_ENUM.values().length;
    }

    public void deleteQuantityPass(PriceList priceList) {
        quantityPassRepository.deleteByPriceListIdPriceList(priceList);
    }

    public void createQuantityPass(PriceList priceList) {
        if (Boolean.TRUE.equals(recreate)) {
            deleteQuantityPass(priceList);
        }
        for (QUANTITY_PASS_ENUM pass : QUANTITY_PASS_ENUM.values()) {
            quantityPassRepository.save(
                    new QuantityPass(
                            pass.getQuantity(),
                            pass.getPrice(),
                            priceList
                    )
            );
        }
        logger.info("Quantity passes created");
    }




}