package polsl.tab.skiresort.api.entry.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;
import polsl.tab.skiresort.model.Pass;
import polsl.tab.skiresort.model.SkiReportCount;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class SkiReportBuilder {

    private static final String[] HEADERS = {"Nazwa wyciągu", "Ilość zjazdów", "Pokonana wysokość"};

    private static final CSVFormat FORMAT = CSVFormat.DEFAULT;

    public ByteArrayInputStream generateReportCSV(Pass pass, List<SkiReportCount> skiReports,
                                                  LocalDate startDate, LocalDate endDate) throws IOException {

        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        final CSVPrinter printer = new CSVPrinter(new PrintWriter(stream), FORMAT);

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
        long sum = skiReports.stream().mapToLong(SkiReportCount::getCount).sum();
        mainHeader = "Suma zjazdów: " + sum;
        stream.write(mainHeader.getBytes());
        printer.println();
        printer.println();
        printer.flush();

        // Print record for every ski lift
        if (sum != 0) {
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

        return new ByteArrayInputStream(stream.toByteArray());
    }

}
