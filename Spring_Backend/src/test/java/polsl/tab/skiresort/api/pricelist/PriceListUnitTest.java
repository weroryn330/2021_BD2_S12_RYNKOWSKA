package polsl.tab.skiresort.api.pricelist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import polsl.tab.skiresort.api.employee.request.AgeDiscountsRequest;
import polsl.tab.skiresort.api.employee.request.PriceListRequest;
import polsl.tab.skiresort.api.employee.request.QuantityPassRequest;
import polsl.tab.skiresort.api.employee.request.TimePassRequest;
import polsl.tab.skiresort.api.employee.service.PriceListService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class PriceListUnitTest extends PriceListUnitTestConfig {

    @Autowired
    private PriceListService priceListService;

    private PriceListRequest priceListRequest;

    private AgeDiscountsRequest ageDiscountsRequest;

    private TimePassRequest timePassRequest;

    private QuantityPassRequest quantityPassRequest;

    @BeforeEach
    void setup() {
        super.setup();
        priceListRequest = new PriceListRequest();
        priceListRequest.setStartDate(Date.valueOf(LocalDate.now()));
        priceListRequest.setEndDate(Date.valueOf(LocalDate.of(2042, 11, 23)));
        List<AgeDiscountsRequest> ageDiscountsRequestList = new ArrayList<>();
        ageDiscountsRequest = new AgeDiscountsRequest();
        ageDiscountsRequest.setAgeMin(0);
        ageDiscountsRequest.setAgeMax(50);
        ageDiscountsRequest.setPercentage(5);
        ageDiscountsRequestList.add(ageDiscountsRequest);
        List<TimePassRequest> timePassRequestList = new ArrayList<>();
        timePassRequest = new TimePassRequest();
        timePassRequest.setPrice(10.0f);
        timePassRequest.setHours(3);
        timePassRequestList.add(timePassRequest);
        List<QuantityPassRequest> quantityPassRequestList = new ArrayList<>();
        quantityPassRequest = new QuantityPassRequest();
        quantityPassRequest.setQuantity(10);
        quantityPassRequest.setPrice(20.0f);
        quantityPassRequestList.add(quantityPassRequest);
        priceListRequest.setAgeDiscountsResponse(ageDiscountsRequestList);
        priceListRequest.setTimePassResponse(timePassRequestList);
        priceListRequest.setQuantityPassResponse(quantityPassRequestList);
    }

    @Test
    void checkNewPriceListStartDate() {
        var todayDate = new Date(new java.util.Date().getTime());
        priceListService.modifyCurrentPriceListAndDeactivateOldOne(priceListRequest);
        var after = priceListService.getCurrentPriceList().getStartDate();
        Assertions.assertEquals(after,todayDate);
    }

    @Test
    void checkOldPriceListEndDate() {
        var todayDate = new Date(new java.util.Date().getTime());
        var c = Calendar.getInstance();
        c.setTime(todayDate);
        c.add(Calendar.DATE, -1);
        var oldEndDate = new Date(c.getTimeInMillis());
        var oldPriceListId = priceListService.getCurrentPriceList().getIdPriceList();
        priceListService.modifyCurrentPriceListAndDeactivateOldOne(priceListRequest);
        var oldPriceListEndDate= priceListRepository.findById(oldPriceListId).get().getEndDate();
        Assertions.assertEquals(oldEndDate,oldPriceListEndDate);
    }
}
