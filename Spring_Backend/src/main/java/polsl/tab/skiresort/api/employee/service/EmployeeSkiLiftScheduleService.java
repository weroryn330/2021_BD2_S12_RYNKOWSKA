package polsl.tab.skiresort.api.employee.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.api.employee.request.SkiLiftScheduleRequest;
import polsl.tab.skiresort.api.employee.response.SkiLiftScheduleResponse;
import polsl.tab.skiresort.model.SkiLiftSchedule;
import polsl.tab.skiresort.repository.SkiLiftRepository;
import polsl.tab.skiresort.repository.SkiLiftScheduleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeSkiLiftScheduleService {

    private final SkiLiftScheduleRepository skiLiftScheduleRepository;
    private final SkiLiftRepository skiLiftRepository;


    private SkiLiftScheduleResponse mapSkiLiftSchedule(SkiLiftSchedule skiLiftSchedule){
        return new SkiLiftScheduleResponse(
                skiLiftSchedule.getStartDate(),
                skiLiftSchedule.getEndDate(),
                skiLiftSchedule.getOpensTime(),
                skiLiftSchedule.getClosesTime(),
                skiLiftSchedule.getSkiLiftIdSkiLift().getIdSkiLift(),
                skiLiftSchedule.getIdSkiLiftSchedule());
    }

    public EmployeeSkiLiftScheduleService(SkiLiftScheduleRepository skiLiftScheduleRepository, SkiLiftRepository skiLiftRepository) {
        this.skiLiftScheduleRepository = skiLiftScheduleRepository;
        this.skiLiftRepository = skiLiftRepository;
    }

    public SkiLiftScheduleResponse addNewSkiLiftSchedule(SkiLiftScheduleRequest skiLiftScheduleRequest) {
        SkiLiftSchedule skiLiftSchedule = new SkiLiftSchedule(
                skiLiftScheduleRequest.getStartDate(),
                skiLiftScheduleRequest.getEndDate(),
                skiLiftScheduleRequest.getOpensTime(),
                skiLiftScheduleRequest.getClosesTime(),
                skiLiftRepository.findById(skiLiftScheduleRequest.getSkiLiftId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ski lift not found")));

        skiLiftScheduleRepository.save(skiLiftSchedule);

        return mapSkiLiftSchedule(skiLiftSchedule);

    }

    public SkiLiftScheduleResponse editSkiLiftSchedule(SkiLiftScheduleRequest skiLiftScheduleRequest) {

        SkiLiftSchedule skiLiftSchedule = skiLiftScheduleRepository.findCurrentBySkiLiftId(skiLiftScheduleRequest.getSkiLiftId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Current schedule not found"));

        skiLiftSchedule.setEndDate(skiLiftScheduleRequest.getEndDate());
        skiLiftSchedule.setStartDate(skiLiftScheduleRequest.getStartDate());
        skiLiftSchedule.setOpensTime(skiLiftScheduleRequest.getOpensTime());
        skiLiftSchedule.setClosesTime(skiLiftScheduleRequest.getClosesTime());

        skiLiftScheduleRepository.save(skiLiftSchedule);

        return mapSkiLiftSchedule(skiLiftSchedule);
    }
}
