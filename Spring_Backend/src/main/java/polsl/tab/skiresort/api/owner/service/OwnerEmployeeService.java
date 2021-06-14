package polsl.tab.skiresort.api.owner.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.api.entry.request.UserRequest;
import polsl.tab.skiresort.api.entry.response.UserResponse;
import polsl.tab.skiresort.api.passes.response.InvoiceResponse;
import polsl.tab.skiresort.model.User;
import polsl.tab.skiresort.repository.InvoiceRepository;
import polsl.tab.skiresort.repository.UserRepository;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerEmployeeService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final InvoiceRepository invoiceRepository;

    public OwnerEmployeeService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            InvoiceRepository invoiceRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.invoiceRepository = invoiceRepository;
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
}
