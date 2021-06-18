package polsl.tab.skiresort.api.skiLiftSchedule.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.api.employee.response.SkiLiftScheduleResponse;
import polsl.tab.skiresort.model.SkiLiftSchedule;
import polsl.tab.skiresort.repository.SkiLiftScheduleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkiLiftScheduleService {

    private final SkiLiftScheduleRepository skiLiftScheduleRepository;

    public SkiLiftScheduleService(SkiLiftScheduleRepository skiLiftScheduleRepository) {
        this.skiLiftScheduleRepository = skiLiftScheduleRepository;
    }

    public List<SkiLiftScheduleResponse> getAllCurrentSchedules() {

        return skiLiftScheduleRepository.findAllCurrent()
                .stream().map(SkiLiftScheduleResponse::new)
                .collect(Collectors.toList());
    }

    public SkiLiftScheduleResponse getSkiLiftCurrentSchedule(Integer skiLiftId) {

        return new SkiLiftScheduleResponse(skiLiftScheduleRepository.findSkiLiftScheduleBySkiLiftIdSkiLift(skiLiftId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found")));
    }

}
