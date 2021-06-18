package polsl.tab.skiresort.api.skiLiftSchedule;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import polsl.tab.skiresort.api.employee.response.SkiLiftScheduleResponse;
import polsl.tab.skiresort.api.skiLiftSchedule.service.SkiLiftScheduleService;

import java.util.List;

@RestController
@RequestMapping("/api/skiLiftSchedule")
public class SkiLiftScheduleApi {

    private final SkiLiftScheduleService skiLiftScheduleService;

    public SkiLiftScheduleApi(SkiLiftScheduleService skiLiftScheduleService) {
        this.skiLiftScheduleService = skiLiftScheduleService;
    }

    @GetMapping("/allCurrent")
    public ResponseEntity<List<SkiLiftScheduleResponse>> getAllSkiLiftSchedules(){

        return ResponseEntity.ok(skiLiftScheduleService.getAllCurrentSchedules());
    }

    @GetMapping("/current/{skiLiftId}")
    public ResponseEntity<SkiLiftScheduleResponse> getSkiLiftSchedule(@PathVariable Integer skiLiftId){

        return ResponseEntity.ok(skiLiftScheduleService.getSkiLiftCurrentSchedule(skiLiftId));
    }

}
