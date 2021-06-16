package polsl.tab.skiresort.api.technical_employee;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polsl.tab.skiresort.api.technical_employee.response.SkiLiftResponse;
import polsl.tab.skiresort.api.technical_employee.service.TechnicalEmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/technical_employee")
public class TechnicalEmployeeApi {

    private final TechnicalEmployeeService technicalEmployeeService;

    public TechnicalEmployeeApi(TechnicalEmployeeService technicalEmployeeService){this.technicalEmployeeService = technicalEmployeeService;}

    @GetMapping("/all")
    public ResponseEntity<List<SkiLiftResponse>> getAllSkiLifts() {
        return ResponseEntity.ok(technicalEmployeeService.getAllSkiLifts());
    }

    @PutMapping("/{skiLiftId}")
    public ResponseEntity<SkiLiftResponse> editSkiLiftIsOpened(@PathVariable("skiLiftId") Integer skiLiftId
    ) {
        return ResponseEntity.ok(technicalEmployeeService.editSkiLiftIsOpened(skiLiftId));
    }

}
