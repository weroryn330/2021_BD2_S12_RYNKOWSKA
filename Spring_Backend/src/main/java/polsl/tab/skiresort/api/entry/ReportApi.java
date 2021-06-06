package polsl.tab.skiresort.api.entry;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import polsl.tab.skiresort.api.entry.service.ReportService;

@RestController
@RequestMapping("/api/report")
public class ReportApi {

    private final ReportService reportService;

    public ReportApi(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/{passId}")
    public ResponseEntity<Resource> getPassReport(@PathVariable String passId){

        final InputStreamResource resource = new InputStreamResource(reportService.getPassReport(Integer.parseInt(passId)));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + reportService.getFileName(Integer.parseInt(passId)) + ".csv\"")
                .contentType(MediaType.parseMediaType("text/csv;charset=utf-8"))
                .body(resource);
    }
}
