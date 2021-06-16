package polsl.tab.skiresort.api.mockup_usage;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import polsl.tab.skiresort.api.mockup_usage.response.SkiLiftMockupUsageResponse;
import polsl.tab.skiresort.api.mockup_usage.service.MockupUsageService;

import java.util.List;

@RestController
@RequestMapping("/api/mockup_usage")
public class MockupUsageApi {

    private final MockupUsageService mockupUsageService;

    public MockupUsageApi(MockupUsageService mockupUsageService) {
        this.mockupUsageService = mockupUsageService;
    }

    @GetMapping
    public ResponseEntity<List<SkiLiftMockupUsageResponse>> getCurrentSkiLiftsUsage() {
        return ResponseEntity.ok(mockupUsageService.getCurrentSkiLiftsUsage());
    }
}
