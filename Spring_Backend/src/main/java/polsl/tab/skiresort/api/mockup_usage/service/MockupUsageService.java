package polsl.tab.skiresort.api.mockup_usage.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.api.mockup_usage.response.SkiLiftMockupScheduleResponse;
import polsl.tab.skiresort.api.mockup_usage.response.SkiLiftMockupUsageResponse;
import polsl.tab.skiresort.repository.SkiLiftRepository;
import polsl.tab.skiresort.repository.SkiLiftScheduleRepository;
import polsl.tab.skiresort.repository.UsageRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MockupUsageService {

    private final SkiLiftRepository skiLiftRepository;

    private final UsageRepository usageRepository;

    private final SkiLiftScheduleRepository skiLiftScheduleRepository;

    private final Integer secondsTimespan;

    public MockupUsageService(
            SkiLiftRepository skiLiftRepository,
            UsageRepository usageRepository,
            SkiLiftScheduleRepository skiLiftScheduleRepository,
            @Value("${resort.mockup.timespan}") Integer secondsTimespan
    ) {
        this.skiLiftRepository = skiLiftRepository;
        this.usageRepository = usageRepository;
        this.skiLiftScheduleRepository = skiLiftScheduleRepository;
        this.secondsTimespan = secondsTimespan;
    }

    public List<SkiLiftMockupUsageResponse> getCurrentSkiLiftsUsage() {
        return skiLiftRepository
                .findAll()
                .stream()
                .map(skiLift -> new SkiLiftMockupUsageResponse(
                        skiLift,
                        usageRepository.countAllBySkiLiftIdSkiLiftAndUseTimestampBetween(
                                skiLift,
                                Timestamp.valueOf(LocalDateTime.now().minusSeconds(secondsTimespan)),
                                Timestamp.valueOf(LocalDateTime.now())
                        ),
                        new SkiLiftMockupScheduleResponse(skiLiftScheduleRepository
                                .findCurrentScheduleForSkiLiftId(
                                    skiLift.getIdSkiLift(),
                                    Date.valueOf(LocalDate.now())
                        ).orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                        "Ski lift schedule does not exist for " + skiLift.getName()
                                )
                            )
                        )
                    )
                ).collect(Collectors.toList())
        ;
    }
}
