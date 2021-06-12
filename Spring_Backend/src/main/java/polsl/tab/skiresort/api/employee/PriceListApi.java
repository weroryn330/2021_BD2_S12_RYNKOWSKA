package polsl.tab.skiresort.api.employee;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polsl.tab.skiresort.api.employee.request.AgeDiscountsRequest;
import polsl.tab.skiresort.api.employee.request.PriceListRequest;
import polsl.tab.skiresort.api.employee.request.QuantityPassRequest;
import polsl.tab.skiresort.api.employee.request.TimePassRequest;
import polsl.tab.skiresort.api.employee.response.PriceListResponse;
import polsl.tab.skiresort.api.employee.service.PriceListService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/priceList")
public class PriceListApi {

    private final PriceListService priceListService;

    public PriceListApi(PriceListService priceListService) {
        this.priceListService = priceListService;
    }

    @GetMapping("/current")
    public ResponseEntity<PriceListResponse> getActivePriceList() {
        return ResponseEntity.ok(priceListService.getActivePriceList());
    }

    @GetMapping("/edit/all")
    public ResponseEntity<List<PriceListResponse>> getAllPriceLists() {
        return ResponseEntity.ok(priceListService.getAll());
    }

    @PutMapping("/edit/current")
    public ResponseEntity<PriceListResponse> editStartDateAndEndDateOfActivePriceList(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ) {
        return ResponseEntity.ok(priceListService.modifyActivePriceListStartDateEndDate(
                new java.sql.Date(startDate.getTime()),
                new java.sql.Date(endDate.getTime())
        ));
    }

    @PostMapping("/edit/new")
    public ResponseEntity<PriceListResponse> addNewPriceList(@RequestBody PriceListRequest priceListRequest) {
        return ResponseEntity.ok(priceListService.addNewCurrentPriceList(priceListRequest));
    }

    @PutMapping("/edit/ageDiscounts")
    public ResponseEntity<PriceListResponse> modifyActivePriceListAgeDiscounts(
            @RequestBody AgeDiscountsRequest request
    ) {
        return ResponseEntity.ok(priceListService.modifyAgeDiscountsForActivePriceList(request));
    }

    @PutMapping("/edit/quantityPasses")
    public ResponseEntity<PriceListResponse> modifyActivePriceListQuantityPasses(
            @RequestBody QuantityPassRequest request
    ) {
        return ResponseEntity.ok(priceListService.modifyQuantityPassForActivePriceList(request));
    }

    @PutMapping("/edit/timePasses")
    public ResponseEntity<PriceListResponse> modifyActivePriceListTimePasses(
            @RequestBody TimePassRequest request
    ) {
        return ResponseEntity.ok(priceListService.modifyTimePassForActivePriceList(request));
    }

}
