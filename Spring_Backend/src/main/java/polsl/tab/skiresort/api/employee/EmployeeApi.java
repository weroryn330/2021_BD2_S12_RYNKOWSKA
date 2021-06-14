package polsl.tab.skiresort.api.employee;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<List<PassesResponse>> getAllActivePasses() {
        return ResponseEntity.ok(employeePassesService.getActivePasses());
    }

    @GetMapping("/allPasses")
    public ResponseEntity<List<PassesResponse>> getAllPasses() {
        return ResponseEntity.ok(employeePassesService.getAllPasses());
    }

    @PostMapping("/blockPass/{passId}")
    public ResponseEntity<PassesResponse> blockPass(@PathVariable Integer passId){
        return ResponseEntity.ok(employeePassesService.setPassBlock(passId, '1'));
    }

    @PostMapping("/unblockPass/{passId}")
    public ResponseEntity<PassesResponse> unblockPass(@PathVariable Integer passId){
        return ResponseEntity.ok(employeePassesService.setPassBlock(passId, '0'));
    }
}
