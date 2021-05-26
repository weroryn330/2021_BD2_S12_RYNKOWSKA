package polsl.tab.skiresort.api.employee;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import polsl.tab.skiresort.api.employee.response.PassesResponse;
import polsl.tab.skiresort.api.employee.service.EmployeePassesService;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeApi {

    private final EmployeePassesService employeePassesService;

    public EmployeeApi(EmployeePassesService employeePassesService) {
        this.employeePassesService = employeePassesService;
    }

    @GetMapping("/activePasses")
    ResponseEntity<List<PassesResponse>> getAllActivePasses() {
        return ResponseEntity.ok(employeePassesService.getActivePasses());
    }

    @GetMapping("/allPasses")
    ResponseEntity<List<PassesResponse>> getAllPasses() {
        return ResponseEntity.ok(employeePassesService.getAllPasses());
    }


}
