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

@SpringBootTest
@Transactional
abstract class PriceListIntegrationTestConfig {

    @Autowired
    public PriceListRepository priceListRepository;

    @Autowired
    public AgeDiscountRepository ageDiscountRepository;

    @Autowired
    public TimePassRepository timePassRepository;

    @Autowired
    public QuantityPassRepository quantityPassRepository;

    public String jsonRequest;

    @BeforeEach
    void setup() {

        this.jsonRequest = "{" +
                "    \"startDate\": \"2026-04-22\",\n" +
                "    \"endDate\": \"2026-04-22\",\n" +
                "    \"ageDiscountsResponse\": \n" +
                "[\n" +
                "{" +
                "    \"ageMin\": \"0\",\n" +
                "    \"ageMax\": \"50\",\n" +
                "    \"percentage\": \"5\"\n" +
                "}\n" +
                "],\n" +
                "    \"quantityPassResponse\": \n" +
                "[\n" +
                "{" +
                "    \"quantity\": \"10\",\n" +
                "    \"price\": \"10.0\"\n" +
                "}\n" +
                "],\n" +
                "    \"timePassResponse\": \n" +
                "[\n" +
                "{" +
                "    \"hours\": \"3\",\n" +
                "    \"price\": \"20.0\"\n" +
                "}\n" +
                "]\n" +
                "}";

        var priceList = new PriceList(
                Date.valueOf(LocalDate.of(2021, 2, 23)),
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
