package polsl.tab.skiresort.api.pricelist;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import polsl.tab.skiresort.model.AgeDiscount;
import polsl.tab.skiresort.model.PriceList;
import polsl.tab.skiresort.model.QuantityPass;
import polsl.tab.skiresort.model.TimePass;
import polsl.tab.skiresort.repository.AgeDiscountRepository;
import polsl.tab.skiresort.repository.PriceListRepository;
import polsl.tab.skiresort.repository.QuantityPassRepository;
import polsl.tab.skiresort.repository.TimePassRepository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;

@Transactional
@SpringBootTest
abstract class PriceListUnitTestConfig {

    @Autowired
    public PriceListRepository priceListRepository;

    @Autowired
    private AgeDiscountRepository ageDiscountRepository;

    @Autowired
    private TimePassRepository timePassRepository;

    @Autowired
    private QuantityPassRepository quantityPassRepository;

    @BeforeEach
    void setup() {
        var priceList = new PriceList(
                Date.valueOf(LocalDate.of(2022, 2, 23)),
                Date.valueOf(LocalDate.of(2022, 11, 23))
        );
        var ageDiscount = new AgeDiscount(
                10,
                20,
                20
        );

        var timePass = new TimePass(
                5,
                5.0f
        );

        var quantityPass = new QuantityPass(
                6,
                6.0f
        );
        priceListRepository.save(priceList);
        ageDiscount.setPriceListIdPriceList(priceList);
        ageDiscountRepository.save(ageDiscount);
        timePass.setPriceListIdPriceList(priceList);
        timePassRepository.save(timePass);
        quantityPass.setPriceListIdPriceList(priceList);
        quantityPassRepository.save(quantityPass);
    }
}
