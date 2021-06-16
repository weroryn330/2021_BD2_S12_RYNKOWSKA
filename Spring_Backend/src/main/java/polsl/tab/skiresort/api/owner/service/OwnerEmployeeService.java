package polsl.tab.skiresort.api.owner.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.api.entry.request.UserRequest;
import polsl.tab.skiresort.api.entry.response.UserResponse;
import polsl.tab.skiresort.api.owner.pdf.BusinessReportBuilder;
import polsl.tab.skiresort.api.passes.response.InvoiceResponse;
import polsl.tab.skiresort.model.User;
import polsl.tab.skiresort.repository.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerEmployeeService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final InvoiceRepository invoiceRepository;

    private final PassRepository passRepository;

    private final UsageRepository usageRepository;

    private final BusinessReportBuilder businessReportBuilder;

    private final SkiLiftRepository skiLiftRepository;

    public OwnerEmployeeService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            InvoiceRepository invoiceRepository, PassRepository passRepository, UsageRepository usageRepository, BusinessReportBuilder businessReportBuilder, SkiLiftRepository skiLiftRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.invoiceRepository = invoiceRepository;
        this.passRepository = passRepository;
        this.usageRepository = usageRepository;
        this.businessReportBuilder = businessReportBuilder;
        this.skiLiftRepository = skiLiftRepository;
    }

    public List<UserResponse> getAllSkiers() {
        return userRepository.findAllSkiers().stream().map(UserResponse::new).collect(Collectors.toList());
    }

    public List<UserResponse> getAllEmployees() {
        return userRepository.findAllEmployees().stream().map(UserResponse::new).collect(Collectors.toList());
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(UserResponse::new).collect(Collectors.toList());
    }

    public List<InvoiceResponse> getAllInvoices() {
        return invoiceRepository.findAll().stream().map(InvoiceResponse::new).collect(Collectors.toList());
    }

    public List<InvoiceResponse> getInvoicesBetweenDates(Date startDate, Date endDate) {
        return invoiceRepository.findByInvoiceDateBetween(startDate, endDate)
                .stream()
                .map(InvoiceResponse::new)
                .collect(Collectors.toList());
    }

    public InvoiceResponse getInvoiceById(Integer invoiceId) {
        return new InvoiceResponse(invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found")));
    }

    public UserResponse editEmployeeAccount(UserRequest request) {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        return new UserResponse(userRepository.save(User.editMapping(user, request, true)));
    }

    public ByteArrayInputStream getBusinessReport(java.util.Date startDate, java.util.Date endDate) {

        try {
            return businessReportBuilder.generateReportPDF(
                    invoiceRepository.findAllWithInvoiceDateBetween(startDate, endDate),
                    skiLiftRepository.findAll(),
                    usageRepository.findAllWithUseTimestampBetween(new Date(startDate.getTime()), new Date(endDate.getTime())),
                    startDate,
                    endDate);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not create PDF file");
        }
    }
}
