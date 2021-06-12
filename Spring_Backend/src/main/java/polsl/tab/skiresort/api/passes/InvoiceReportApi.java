package polsl.tab.skiresort.api.passes;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import polsl.tab.skiresort.api.passes.service.InvoiceReportService;

@RestController
@RequestMapping("/api/invoiceReport")
public class InvoiceReportApi {

    private final InvoiceReportService invoiceReportService;

    public InvoiceReportApi(InvoiceReportService invoiceReportService) {
        this.invoiceReportService = invoiceReportService;
    }

    @GetMapping("/{idInvoice}")
    public ResponseEntity<Resource> downloadInvoiceReport(@PathVariable Integer idInvoice){

        final InputStreamResource resource = new InputStreamResource(invoiceReportService.getInvoiceReport(idInvoice));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"invoiceNum" + idInvoice + ".pdf\"")
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);
    }
}
