package polsl.tab.skiresort.api.owner;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polsl.tab.skiresort.api.entry.request.UserRequest;
import polsl.tab.skiresort.api.entry.response.UserResponse;
import polsl.tab.skiresort.api.entry.service.RegisterService;
import polsl.tab.skiresort.api.owner.service.OwnerEmployeeService;
import polsl.tab.skiresort.api.passes.response.InvoiceResponse;

import java.util.List;

@RestController
@RequestMapping("/api/owner")
public class OwnerApi {

    private final OwnerEmployeeService ownerEmployeeService;

    private final RegisterService registerService;

    public OwnerApi(
            OwnerEmployeeService ownerEmployeeService,
            RegisterService registerService
    ) {
        this.ownerEmployeeService = ownerEmployeeService;
        this.registerService = registerService;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<UserResponse>> getAllEmployees() {
        return ResponseEntity.ok(ownerEmployeeService.getAllEmployees());
    }

    @GetMapping("/skiers")
    public ResponseEntity<List<UserResponse>> getAllSkiers() {
        return ResponseEntity.ok(ownerEmployeeService.getAllSkiers());
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(ownerEmployeeService.getAllUsers());
    }

    @GetMapping("/invoices/all")
    public ResponseEntity<List<InvoiceResponse>> getAllInvoices() {
        return ResponseEntity.ok(ownerEmployeeService.getAllInvoices());
    }

    @GetMapping("/invoice/{invoiceId}")
    public ResponseEntity<InvoiceResponse> getInvoiceById(@PathVariable("invoiceId") Integer id) {
        return ResponseEntity.ok(ownerEmployeeService.getInvoiceById(id));
    }

    @PutMapping("/editAccount")
    public ResponseEntity<UserResponse> editEmployeeAccount(@RequestBody UserRequest request) {
        return ResponseEntity.ok(ownerEmployeeService.editEmployeeAccount(request));
    }

    @PostMapping("/employees/add")
    public ResponseEntity<UserResponse> addNewEmployee(
            @RequestParam("roleName") String roleName,
            @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(registerService.registerUser(request, roleName));
    }
}
