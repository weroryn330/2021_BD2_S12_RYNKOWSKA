package polsl.tab.skiresort.api.employee.service;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.api.employee.request.SkiLiftScheduleRequest;
import polsl.tab.skiresort.api.employee.response.SkiLiftScheduleResponse;
import polsl.tab.skiresort.model.SkiLiftSchedule;
import polsl.tab.skiresort.repository.SkiLiftRepository;
import polsl.tab.skiresort.repository.SkiLiftScheduleRepository;

import java.sql.Date;
import java.sql.Time;

@Service
public class EmployeeSkiLiftScheduleService {

    private final SkiLiftScheduleRepository skiLiftScheduleRepository;

    private final SkiLiftRepository skiLiftRepository;

    public EmployeeSkiLiftScheduleService(SkiLiftScheduleRepository skiLiftScheduleRepository, SkiLiftRepository skiLiftRepository) {
        this.skiLiftScheduleRepository = skiLiftScheduleRepository;
        this.skiLiftRepository = skiLiftRepository;
    }

    public SkiLiftScheduleResponse editSkiLiftSchedule(SkiLiftScheduleRequest skiLiftScheduleRequest) {

        SkiLiftSchedule skiLiftSchedule;
        try {
            skiLiftSchedule = skiLiftScheduleRepository.findCurrentBySkiLiftId(skiLiftScheduleRequest.getSkiLiftId())
                    .orElse(new SkiLiftSchedule(
                            new Date(System.currentTimeMillis()),
                            new Date(System.currentTimeMillis() + (630720000000L)), // 
                            skiLiftScheduleRequest.getOpensTime(),
                            skiLiftScheduleRequest.getClosesTime(),
                            skiLiftRepository.findById(skiLiftScheduleRequest.getSkiLiftId()).orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "Ski lift with id not found"
                            ))
                    ));

            skiLiftSchedule.setOpensTime(skiLiftScheduleRequest.getOpensTime());
            skiLiftSchedule.setClosesTime(skiLiftScheduleRequest.getClosesTime());

            skiLiftScheduleRepository.save(skiLiftSchedule);

        }catch(IncorrectResultSizeDataAccessException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "More than one ski lift schedule present");
        }

        return new SkiLiftScheduleResponse(skiLiftSchedule);
    }
}
