package polsl.tab.skiresort.api.employee.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.api.employee.request.PriceListRequest;
import polsl.tab.skiresort.repository.PriceListRepository;

import java.util.Date;

@Aspect
public class PriceListValidation {

    private final PriceListRepository priceListRepository;

    public PriceListValidation(PriceListRepository priceListRepository) {
        this.priceListRepository = priceListRepository;
    }

    @Pointcut("execution(* polsl.tab.skiresort.api.employee.PriceListApi.editStartDateAndEndDateOfActivePriceList(..))")
    static void validateStartDateEndDateOfPriceLists() { /* Used for pointcut header */ }

    @Around("validateStartDateEndDateOfPriceLists()")
    Object validateStartDateAndEndDateThrowExceptionIfError(ProceedingJoinPoint joinPoint) throws Throwable {
        var startDate = (Date) joinPoint.getArgs()[0];
        var endDate = (Date) joinPoint.getArgs()[1];
        var dbPriceLists = priceListRepository.findAll();
        if (dbPriceLists.stream().noneMatch(
                priceList -> priceList.getStartDate().compareTo(startDate) < 0 &&
                priceList.getEndDate().compareTo(endDate) < 0)) {
            joinPoint.proceed();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Invalid start date and end date. " +
                        "There is already defined price list within start and end date range.");
    }

    @Around("execution(* polsl.tab.skiresort.api.employee.PriceListApi.addNewPriceList(..))")
    Object validateActivePriceListStartDateEndDate(ProceedingJoinPoint joinPoint) throws Throwable {
        var priceList = (PriceListRequest) joinPoint.getArgs()[0];
        var dbActivePriceList = priceListRepository.findCurrentPriceList();
        if (dbActivePriceList.isPresent()) {
            if (priceList.getStartDate().compareTo(dbActivePriceList.get().getStartDate()) > 0 &&
                priceList.getEndDate().compareTo(dbActivePriceList.get().getEndDate()) > 0 &&
                priceList.getEndDate().compareTo(priceList.getStartDate()) > 0){
                joinPoint.proceed();
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Invalid start date and end date. " +
                            "There is already defined price list within start and end date range.");
        }
        return joinPoint.proceed();
    }
}
