package polsl.tab.skiresort.api.entry.service;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;
import polsl.tab.skiresort.model.Pass;
import polsl.tab.skiresort.model.SkiReport;
import polsl.tab.skiresort.model.User;
import polsl.tab.skiresort.repository.PassRepository;
import polsl.tab.skiresort.repository.SkiReportRepository;
import polsl.tab.skiresort.repository.UserRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class ReportService {

    private static final String[] HEADERS = {"Nazwa wyciagu", "Ilosc zjazdow", "Pokonana wysokosc"};
    private static final CSVFormat FORMAT = CSVFormat.DEFAULT;

    private final PassRepository passRepository;
    private final SkiReportRepository skiReportRepository;

    public ReportService(PassRepository passRepository, SkiReportRepository skiReportRepository) {
        this.passRepository = passRepository;
        this.skiReportRepository = skiReportRepository;
    }

    //write data to csv
    public ByteArrayInputStream getPassReport(Integer passId) {
        try (final ByteArrayOutputStream stream = new ByteArrayOutputStream();
            final CSVPrinter printer = new CSVPrinter(new PrintWriter(stream), FORMAT)) {

            Pass pass = passRepository.getOne(passId);

            String mainHeader = "Raport narciarski: " + pass.getFirstName() + " " + pass.getLastName();
            stream.write(mainHeader.getBytes());
            printer.println();
            printer.flush();

            mainHeader = "Nr: " + pass.getIdPass();
            stream.write(mainHeader.getBytes());
            printer.println();
            printer.flush();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            mainHeader = "Z dnia: " + now;
            stream.write(mainHeader.getBytes());
            printer.println();
            printer.flush();

            List<String> data = Arrays.asList(HEADERS);
            printer.printRecord(data);

            List<SkiReport> skiReports = skiReportRepository.findBySkiReportId_IdPass(passId);

            for (final SkiReport skiReport : skiReports) {
                data = Arrays.asList(
                        skiReport.skiReportId.getName(),
                        String.valueOf(skiReport.getCount()),
                        String.valueOf(skiReport.getHeight() * skiReport.getCount())
                );

                printer.printRecord(data);
            }

            printer.flush();
            return new ByteArrayInputStream(stream.toByteArray());

        } catch (final IOException e) {
            throw new RuntimeException("Csv writing error: " + e.getMessage());
        }
    }

}
