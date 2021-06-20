package polsl.tab.skiresort.config;

import org.springframework.context.annotation.Configuration;
import polsl.tab.skiresort.config.components.AgeDiscountsComponent;
import polsl.tab.skiresort.config.components.PriceListComponent;
import polsl.tab.skiresort.config.components.QuantityPassComponent;
import polsl.tab.skiresort.config.components.TimePassesComponent;
import polsl.tab.skiresort.model.PriceList;
import polsl.tab.skiresort.repository.PriceListRepository;

@Configuration
public class PriceListOrderedConfig {

    private final AgeDiscountsComponent ageDiscountsComponent;

    private final TimePassesComponent timePassesComponent;

    private final QuantityPassComponent quantityPassComponent;

    private final PriceListComponent priceListComponent;

    private final PriceListRepository priceListRepository;

    private PriceList priceList;

    private void cascadeDeletePriceList() {
        this.priceList = priceListComponent.findConfigPriceListByEnumDates();
        ageDiscountsComponent.deleteAgeDiscounts(priceList);
        timePassesComponent.deleteTimePasses(priceList);
        quantityPassComponent.deleteQuantityPass(priceList);
        priceListComponent.deletePriceList();
    }

    private void createCurrentPriceList() {
        if (Boolean.TRUE.equals(priceListComponent.getRecreate())) {
            cascadeDeletePriceList();
        }
        try {
            this.priceList = priceListComponent.findConfigPriceListByEnumDates();
        } catch (NullPointerException e) {
            this.priceList = priceListComponent.createPriceList();
        }
    }

    private void createAgeDiscounts() {
        if (!ageDiscountsComponent.exists(this.priceList) || ageDiscountsComponent.getRecreate()) {
            ageDiscountsComponent.createAgeDiscounts(this.priceList);
        }
    }

    private void createQuantityPasses() {
        if (!quantityPassComponent.exists(this.priceList) || quantityPassComponent.getRecreate()) {
            quantityPassComponent.createQuantityPass(this.priceList);
        }
    }

    private void createTimePasses() {
        if (!timePassesComponent.exists(this.priceList) || timePassesComponent.getRecreate()) {
            timePassesComponent.createTimePasses(this.priceList);
        }
    }

    public PriceListOrderedConfig(final AgeDiscountsComponent ageDiscountsComponent,
                                  final TimePassesComponent timePassesComponent,
                                  final QuantityPassComponent quantityPassComponent,
                                  final PriceListComponent priceListComponent,
                                  final PriceListRepository priceListRepository
    ) {
        this.ageDiscountsComponent = ageDiscountsComponent;
        this.timePassesComponent = timePassesComponent;
        this.quantityPassComponent = quantityPassComponent;
        this.priceListComponent = priceListComponent;
        this.priceListRepository = priceListRepository;
        if (priceListRepository.findCurrentPriceList().isEmpty()) {
            this.createCurrentPriceList();
            this.createAgeDiscounts();
            this.createQuantityPasses();
            this.createTimePasses();
        }
    }
}
