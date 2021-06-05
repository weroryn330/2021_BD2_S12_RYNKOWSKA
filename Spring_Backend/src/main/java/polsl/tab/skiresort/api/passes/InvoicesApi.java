package polsl.tab.skiresort.api.passes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polsl.tab.skiresort.api.passes.request.InvoiceRequest;
import polsl.tab.skiresort.api.passes.response.InvoiceResponse;
import polsl.tab.skiresort.api.passes.response.PassResponse;
import polsl.tab.skiresort.api.passes.service.InvoiceService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/invoices")
public class InvoicesApi {

    private final InvoiceService invoiceService;

    public InvoicesApi(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceResponse>> getUserInvoices(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(invoiceService.getAllUserInvoices(token));
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<List<PassResponse>> getInvoicePasses(
            @RequestHeader("Authorization") String token,
            @PathVariable("invoiceId") Integer invoiceId
    ) {
        return ResponseEntity.ok(invoiceService.getAllInvoicePasses(token, invoiceId));
    }

    @PostMapping
    public ResponseEntity<InvoiceResponse> addInvoiceToUser(
            @RequestHeader("Authorization") String token,
            @RequestBody InvoiceRequest invoiceRequest
    ) {
        return ResponseEntity.ok(invoiceService.addInvoiceToUser(token, invoiceRequest));
    }
}
