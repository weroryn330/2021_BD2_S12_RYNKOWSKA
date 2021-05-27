package polsl.tab.skiresort.api.employee;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polsl.tab.skiresort.api.employee.request.AgeDiscountsRequest;
import polsl.tab.skiresort.api.employee.request.PriceListRequest;
import polsl.tab.skiresort.api.employee.request.QuantityPassRequest;
import polsl.tab.skiresort.api.employee.request.TimePassRequest;
import polsl.tab.skiresort.api.employee.response.PriceListResponse;
import polsl.tab.skiresort.api.employee.service.PriceListService;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/priceList")
public class PriceListApi {

    private final PriceListService priceListService;

    public PriceListApi(PriceListService priceListService) {
        this.priceListService = priceListService;
    }

    @GetMapping("/current")
    ResponseEntity<PriceListResponse> getActivePriceList() {
        return ResponseEntity.ok(priceListService.getActivePriceList());
    }

    @GetMapping("/edit/all")
    ResponseEntity<List<PriceListResponse>> getAllPriceLists() {
        return ResponseEntity.ok(priceListService.getAll());
    }

    @PutMapping("/edit/current")
    ResponseEntity<PriceListResponse> editStartDateAndEndDateOfActivePriceList(
            @RequestBody Date startDate,
            @RequestBody Date endDate
    ) {
        return ResponseEntity.ok(priceListService.modifyActivePriceListStartDateEndDate(startDate, endDate));
    }

    @PostMapping
    ResponseEntity<PriceListResponse> addNewPriceList(@RequestBody PriceListRequest priceListRequest) {
        return ResponseEntity.ok(priceListService.addNewPriceList(priceListRequest));
    }

    @PutMapping("/edit/ageDiscounts")
    ResponseEntity<PriceListResponse> modifyActivePriceListAgeDiscounts(
            @RequestBody AgeDiscountsRequest request
    ) {
        return ResponseEntity.ok(priceListService.modifyAgeDiscountsForActivePriceList(request));
    }

    @PutMapping("/edit/quantityPasses")
    ResponseEntity<PriceListResponse> modifyActivePriceListQuantityPasses(
            @RequestBody QuantityPassRequest request
    ) {
        return ResponseEntity.ok(priceListService.modifyQuantityPassForActivePriceList(request));
    }

    @PutMapping("/edit/timePasses")
    ResponseEntity<PriceListResponse> modifyActivePriceListTimePasses(
            @RequestBody TimePassRequest request
    ) {
        return ResponseEntity.ok(priceListService.modifyTimePassForActivePriceList(request));
    }

}
