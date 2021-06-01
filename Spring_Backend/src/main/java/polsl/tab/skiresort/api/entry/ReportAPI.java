package polsl.tab.skiresort.api.entry;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import polsl.tab.skiresort.api.entry.service.ReportService;

@RestController
@RequestMapping("/api/report")
public class ReportAPI {

    private final ReportService reportService;

    public ReportAPI(ReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping
    public ResponseEntity<Resource> downloadUserReport(){

        final InputStreamResource resource = new InputStreamResource(reportService.getUsersAsCSV());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"userReport.csv\"")
                .contentType(MediaType.parseMediaType("text/csv;charset=utf-8"))
                .body(resource);
    }

}
