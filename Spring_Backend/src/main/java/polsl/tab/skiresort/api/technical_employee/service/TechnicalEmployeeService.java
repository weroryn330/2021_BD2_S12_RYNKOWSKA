package polsl.tab.skiresort.api.technical_employee.service;

import org.springframework.stereotype.Service;
import polsl.tab.skiresort.api.technical_employee.response.SkiLiftResponse;
import polsl.tab.skiresort.repository.SkiLiftRepository;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TechnicalEmployeeService {

    private final SkiLiftRepository skiLiftRepository;

    public TechnicalEmployeeService(SkiLiftRepository skiLiftRepository){this.skiLiftRepository = skiLiftRepository;}

    public List<SkiLiftResponse> getAllSkiLifts() {
        var skiLiftList = skiLiftRepository.findAll()
                .stream()
                .map(SkiLiftResponse::new)
                .collect(Collectors.toList());
        if(skiLiftList.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No ski lifts found");
        return skiLiftList;
    }

    public SkiLiftResponse editSkiLiftIsOpened(Integer skiLiftId)
    {
        var skiLift = skiLiftRepository.findById(skiLiftId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Ski lift does not exist"));
        skiLift.setIsOpened(!skiLift.getIsOpened());
        return new SkiLiftResponse(skiLiftRepository.save(skiLift));
    }
}
