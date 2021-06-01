package polsl.tab.skiresort.api.entry.service;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;
import polsl.tab.skiresort.model.User;
import polsl.tab.skiresort.repository.UserRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@Service
public class ReportService {

    private static final String[] HEADERS = {"Id", "First_Name", "Last_Name", "Email"};
    private static final CSVFormat FORMAT = CSVFormat.DEFAULT.withHeader(HEADERS);

    private final UserRepository userRepository;

    public ReportService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //write data to csv
    public ByteArrayInputStream getUsersAsCSV() {
        try (final ByteArrayOutputStream stream = new ByteArrayOutputStream();
            final CSVPrinter printer = new CSVPrinter(new PrintWriter(stream), FORMAT)) {

            List<User> users = userRepository.findAll();

            for (final User user : users) {
                final List<String> data = Arrays.asList(
                        String.valueOf(user.getIdUser()),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail()
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
