package polsl.tab.skiresort.api.technical_employee.service;

import org.springframework.stereotype.Service;
import polsl.tab.skiresort.api.technical_employee.response.SkiLiftResponse;
import polsl.tab.skiresort.repository.SkiLiftRepository;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class TechnicalEmployeeService {

    private final SkiLiftRepository skiLiftRepository;

    public TechnicalEmployeeService(SkiLiftRepository skiLiftRepository){this.skiLiftRepository = skiLiftRepository;}

    public SkiLiftResponse editSkiLiftIsOpened(Integer skiLiftId)
    {
        var skiLift = skiLiftRepository.findById(skiLiftId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Ski lift does not exist"));
        skiLift.setIsOpened(!skiLift.getIsOpened());
        return new SkiLiftResponse(skiLiftRepository.save(skiLift));
    }
}
