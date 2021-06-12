package polsl.tab.skiresort.api.entry;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polsl.tab.skiresort.api.entry.service.SkiReportService;

@RestController
@RequestMapping("/api/skiReport")
public class SkiReportApi {

    private final SkiReportService skiReportService;

    public SkiReportApi(SkiReportService skiReportService) {
        this.skiReportService = skiReportService;
    }

    @GetMapping("/{passId}")
    public ResponseEntity<Resource> getPassReport(@PathVariable Integer passId,
                                                  @RequestParam("startDate") String startDate,
                                                  @RequestParam("endDate") String endDate){

        final InputStreamResource resource = new InputStreamResource(skiReportService.getPassReport(passId, startDate, endDate));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"skiReportId" + passId + startDate + "-" + endDate + ".csv\"")
                .contentType(MediaType.parseMediaType("text/csv;charset=utf-8"))
                .body(resource);
    }
}
