package polsl.tab.skiresort.api.entry.service;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.api.entry.csv.SkiReportBuilder;
import polsl.tab.skiresort.model.Pass;
import polsl.tab.skiresort.model.SkiReportCount;
import polsl.tab.skiresort.repository.PassRepository;
import polsl.tab.skiresort.repository.SkiReportRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class SkiReportService {

    private final PassRepository passRepository;
    
    private final SkiReportRepository skiReportRepository;

    private final SkiReportBuilder skiReportBuilder;

    public SkiReportService(PassRepository passRepository, SkiReportRepository skiReportRepository, SkiReportBuilder skiReportBuilder) {
        this.passRepository = passRepository;
        this.skiReportRepository = skiReportRepository;
        this.skiReportBuilder = skiReportBuilder;
    }

    public ByteArrayInputStream getSkiReport(Integer passId, String startDate, String endDate) {

        Optional<Pass> passOpt = passRepository.findById(passId);
        Pass pass;

        if(passOpt.isPresent())
            pass = passOpt.get();
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pass not found");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startDateLocalDate = LocalDate.parse(startDate, dtf);
        LocalDate endDateLocalDate = LocalDate.parse(endDate, dtf);

        List<SkiReportCount> skiReports = skiReportRepository.findCountByIdPass(passId,
                Timestamp.valueOf(startDateLocalDate.atStartOfDay()),
                Timestamp.valueOf(endDateLocalDate.atStartOfDay()));

        try {
            return skiReportBuilder.generateReportCSV(pass, skiReports, startDateLocalDate, endDateLocalDate);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not create CSV file");
        }
    }

}
