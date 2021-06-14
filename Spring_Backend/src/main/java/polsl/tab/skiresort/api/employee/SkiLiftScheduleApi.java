package polsl.tab.skiresort.api.employee;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polsl.tab.skiresort.api.employee.request.SkiLiftScheduleRequest;
import polsl.tab.skiresort.api.employee.response.SkiLiftScheduleResponse;
import polsl.tab.skiresort.api.employee.service.EmployeeSkiLiftScheduleService;
import polsl.tab.skiresort.model.SkiLiftSchedule;

import java.util.List;

@RestController
@RequestMapping("/api/skiLiftSchedule")
public class SkiLiftScheduleApi {

    private final EmployeeSkiLiftScheduleService employeeSkiLiftScheduleService;

    public SkiLiftScheduleApi(EmployeeSkiLiftScheduleService employeeSkiLiftScheduleService) {
        this.employeeSkiLiftScheduleService = employeeSkiLiftScheduleService;
    }

    @GetMapping("/allCurrent")
    public ResponseEntity<List<SkiLiftScheduleResponse>> getAllSkiLiftSchedules(){

        return ResponseEntity.ok(employeeSkiLiftScheduleService.getAllCurrentSchedules());
    }

    @GetMapping("/current/{skiLiftId}")
    public ResponseEntity<SkiLiftScheduleResponse> getSkiLiftSchedule(@PathVariable Integer skiLiftId){

        return ResponseEntity.ok(employeeSkiLiftScheduleService.getSkiLiftCurrentSchedule(skiLiftId));
    }

    @PostMapping
    public ResponseEntity<SkiLiftScheduleResponse> addNewSkiLiftSchedule(@RequestBody SkiLiftScheduleRequest skiLiftScheduleRequest){

        return ResponseEntity.ok(employeeSkiLiftScheduleService.addNewSkiLiftSchedule(skiLiftScheduleRequest));
    }

    @PutMapping
    public ResponseEntity<SkiLiftScheduleResponse> editSkiLiftSchedule(@RequestBody SkiLiftScheduleRequest skiLiftScheduleRequest){

        return ResponseEntity.ok(employeeSkiLiftScheduleService.editSkiLiftSchedule(skiLiftScheduleRequest));
    }

}
