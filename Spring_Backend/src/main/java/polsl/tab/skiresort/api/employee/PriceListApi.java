package polsl.tab.skiresort.api.employee;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polsl.tab.skiresort.api.employee.request.AgeDiscountsRequest;
import polsl.tab.skiresort.api.employee.request.QuantityPassRequest;
import polsl.tab.skiresort.api.employee.request.TimePassRequest;
import polsl.tab.skiresort.api.employee.response.PriceListResponse;
import polsl.tab.skiresort.api.employee.service.PriceListService;

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

    @PutMapping("/ageDiscounts")
    ResponseEntity<PriceListResponse> modifyActivePriceListAgeDiscounts(
            @RequestBody AgeDiscountsRequest request
    ) {
        return ResponseEntity.ok(priceListService.modifyAgeDiscountsForActivePriceList(request));
    }

    @PutMapping("/quantityPasses")
    ResponseEntity<PriceListResponse> modifyActivePriceListQuantityPasses(
            @RequestBody QuantityPassRequest request
    ) {
        return ResponseEntity.ok(priceListService.modifyQuantityPassForActivePriceList(request));
    }

    @PutMapping("/timePasses")
    ResponseEntity<PriceListResponse> modifyActivePriceListTimePasses(
            @RequestBody TimePassRequest request
    ) {
        return ResponseEntity.ok(priceListService.modifyTimePassForActivePriceList(request));
    }

}
