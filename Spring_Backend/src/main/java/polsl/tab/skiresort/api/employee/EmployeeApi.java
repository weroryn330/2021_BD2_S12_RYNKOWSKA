package polsl.tab.skiresort.api.employee;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polsl.tab.skiresort.api.employee.request.SkiLiftScheduleRequest;
import polsl.tab.skiresort.api.employee.response.PassesResponse;
import polsl.tab.skiresort.api.employee.response.SkiLiftScheduleResponse;
import polsl.tab.skiresort.api.employee.service.EmployeePassesService;
import polsl.tab.skiresort.api.employee.service.EmployeeSkiLiftScheduleService;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeApi {

    private final EmployeePassesService employeePassesService;
    private final EmployeeSkiLiftScheduleService employeeSkiLiftScheduleService;

    public EmployeeApi(EmployeePassesService employeePassesService, EmployeeSkiLiftScheduleService employeeSkiLiftScheduleService) {
        this.employeePassesService = employeePassesService;
        this.employeeSkiLiftScheduleService = employeeSkiLiftScheduleService;
    }

    @GetMapping("/activePasses")
    public ResponseEntity<List<PassesResponse>> getAllActivePasses() {
        return ResponseEntity.ok(employeePassesService.getActivePasses());
    }

    @GetMapping("/allPasses")
    public ResponseEntity<List<PassesResponse>> getAllPasses() {
        return ResponseEntity.ok(employeePassesService.getAllPasses());
    }

    @PutMapping("/blockPass/{passId}")
    public ResponseEntity<PassesResponse> blockPass(@PathVariable Integer passId){
        return ResponseEntity.ok(employeePassesService.setPassBlock(passId));
    }

    @PostMapping("/skiLiftSchedule")
    public ResponseEntity<SkiLiftScheduleResponse> addNewSkiLiftSchedule(@RequestBody SkiLiftScheduleRequest skiLiftScheduleRequest){

        return ResponseEntity.ok(employeeSkiLiftScheduleService.addNewSkiLiftSchedule(skiLiftScheduleRequest));
    }

    @PutMapping("/skiLiftSchedule")
    public ResponseEntity<SkiLiftScheduleResponse> editSkiLiftSchedule(@RequestBody SkiLiftScheduleRequest skiLiftScheduleRequest){

        return ResponseEntity.ok(employeeSkiLiftScheduleService.editSkiLiftSchedule(skiLiftScheduleRequest));
    }
}
