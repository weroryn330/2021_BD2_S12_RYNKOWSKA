package polsl.tab.skiresort.api.entry.service;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.model.Pass;
import polsl.tab.skiresort.model.SkiReportCount;
import polsl.tab.skiresort.repository.PassRepository;
import polsl.tab.skiresort.repository.SkiReportRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SkiReportService {

    private static final String[] HEADERS = {"Nazwa wyciagu", "Ilosc zjazdow", "Pokonana wysokosc"};
    private static final CSVFormat FORMAT = CSVFormat.DEFAULT;

    private final PassRepository passRepository;
    private final SkiReportRepository skiReportRepository;

    public SkiReportService(PassRepository passRepository, SkiReportRepository skiReportRepository) {
        this.passRepository = passRepository;
        this.skiReportRepository = skiReportRepository;
    }

    public String getFileName(Integer passId){
        Optional<Pass> passOpt = passRepository.findById(passId);
        Pass pass;
        if(passOpt.isPresent())
            pass = passOpt.get();
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pass not found");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm");
        LocalDateTime now = LocalDateTime.now();
        return "SkiReport"+ pass.getFirstName() + pass.getLastName() + now.format(dtf);
    }


    public ByteArrayInputStream getPassReport(Integer passId, String startDate, String endDate) {

        return new ByteArrayInputStream(getSkiReportCSV(passId, startDate, endDate).toByteArray());
    }
    

    private ByteArrayOutputStream getSkiReportCSV(Integer passId, String startDate, String endDate){

        try (final ByteArrayOutputStream stream = new ByteArrayOutputStream();
             final CSVPrinter printer = new CSVPrinter(new PrintWriter(stream), FORMAT)) {

            // Get pass
            Optional<Pass> passOpt = passRepository.findById(passId);
            Pass pass;
            if(passOpt.isPresent())
                pass = passOpt.get();
            else
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pass not found");

            // Parse dates
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            List<SkiReportCount> skiReports = skiReportRepository.findCountByIdPass(passId,
                    Timestamp.valueOf(LocalDate.parse(startDate, dtf).atStartOfDay()),
                    Timestamp.valueOf(LocalDate.parse(endDate, dtf).atStartOfDay()));

            // Add header
            String mainHeader = "Raport narciarski: " + pass.getFirstName() + " " + pass.getLastName();
            stream.write(mainHeader.getBytes());
            printer.println();
            printer.flush();

            // Add pass id number
            mainHeader = "Karnet Nr: " + pass.getIdPass();
            stream.write(mainHeader.getBytes());
            printer.println();
            printer.flush();

            // Add date range
            mainHeader = "Za okres od " + startDate + " do " + endDate;
            stream.write(mainHeader.getBytes());
            printer.println();
            printer.flush();

            // Add sum of uses
            Long sum = skiReports.stream().mapToLong(SkiReportCount::getCount).sum();
            mainHeader = "Suma zjazdow: " + sum;
            stream.write(mainHeader.getBytes());
            printer.println();
            printer.println();
            printer.flush();

            // Print record for every ski lift
            if(sum != 0) {
                List<String> data = Arrays.asList(HEADERS);
                printer.printRecord(data);

                for (SkiReportCount skiReport : skiReports) {
                    data = Arrays.asList(
                            skiReport.getName(),
                            String.valueOf(skiReport.getCount()),
                            String.valueOf(skiReport.getHeight() * skiReport.getCount())
                    );
                    printer.printRecord(data);
                }
                printer.flush();
            }

        } catch (final IOException e) {
            throw new RuntimeException("Csv writing error: " + e.getMessage());
        }

        return null;
    }

}
