package polsl.tab.skiresort.api.employee.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.api.employee.request.AgeDiscountsRequest;
import polsl.tab.skiresort.api.employee.request.QuantityPassRequest;
import polsl.tab.skiresort.api.employee.request.TimePassRequest;
import polsl.tab.skiresort.api.employee.response.AgeDiscountsResponse;
import polsl.tab.skiresort.api.employee.response.PriceListResponse;
import polsl.tab.skiresort.api.employee.response.QuantityPassResponse;
import polsl.tab.skiresort.api.employee.response.TimePassResponse;
import polsl.tab.skiresort.model.AgeDiscount;
import polsl.tab.skiresort.model.PriceList;
import polsl.tab.skiresort.model.QuantityPass;
import polsl.tab.skiresort.model.TimePass;
import polsl.tab.skiresort.repository.AgeDiscountRepository;
import polsl.tab.skiresort.repository.PriceListRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceListService {

    private final PriceListRepository priceListRepository;

    public PriceListService(PriceListRepository priceListRepository, AgeDiscountRepository ageDiscountRepository) {
        this.priceListRepository = priceListRepository;
    }

    public PriceListResponse getActivePriceList() {
        return this.mapPriceListResponse(this.getCurrentPriceList());
    }

    public PriceListResponse modifyAgeDiscountsForActivePriceList(AgeDiscountsRequest request) {
        var currentList = this.getCurrentPriceList();
        var listOfAgeDiscounts = currentList.getAgeDiscountList();
        for (AgeDiscount discount: listOfAgeDiscounts) {
            if (discount.getAgeMin().equals(request.getAgeMin()) &&
            discount.getAgeMax().equals(request.getAgeMax())) {
                discount.setPercentage(request.getPercentage());
            }
        }
        currentList.setAgeDiscountList(listOfAgeDiscounts);
        return mapPriceListResponse(priceListRepository.save(currentList));
    }

    public PriceListResponse modifyQuantityPassForActivePriceList(QuantityPassRequest request) {
        var currentList = this.getCurrentPriceList();
        var listOfQuantityPass = currentList.getQuantityPassList();
        for (QuantityPass quantityPass: listOfQuantityPass) {
            if (quantityPass.getQuantity().equals(request.getQuantity())) {
                quantityPass.setQuantity(request.getQuantity());
            }
        }
        currentList.setQuantityPassList(listOfQuantityPass);
        return mapPriceListResponse(priceListRepository.save(currentList));
    }

    public PriceListResponse modifyTimePassForActivePriceList(TimePassRequest request) {
        var currentList = this.getCurrentPriceList();
        var listOfTimePass = currentList.getTimePassList();
        for (TimePass timePass: listOfTimePass) {
           if (timePass.getHours().equals(request.getHours())) {
               timePass.setPrice(request.getPrice());
           }
        }
        currentList.setTimePassList(listOfTimePass);
        return mapPriceListResponse(currentList);
    }

    public PriceListResponse modifyAllAgeDiscountsForActivePriceList(List<AgeDiscountsRequest> requestList) {
        //todo
        return null;
    }

    public PriceListResponse modifyAllQuantityPassForActivePriceList(List<QuantityPassRequest> requestList) {
        //todo
        return null;
    }

    public PriceListResponse modifyAllTimePassForActivePriceList(List<TimePassRequest> requestList) {
        //todo
        return null;
    }

    private PriceListResponse mapPriceListResponse(PriceList priceList) {
        return new PriceListResponse(
                priceList.getIdPriceList(),
                priceList.getAgeDiscountList().stream().map(AgeDiscountsResponse::new).collect(Collectors.toList()),
                priceList.getQuantityPassList().stream().map(QuantityPassResponse::new).collect(Collectors.toList()),
                priceList.getTimePassList().stream().map(TimePassResponse::new).collect(Collectors.toList())
        );
    }

    private PriceList getCurrentPriceList() {
        var list = priceListRepository.findCurrentPriceList();
        if (list.isPresent()) {
            return list.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Price list not found!");
    }
}
