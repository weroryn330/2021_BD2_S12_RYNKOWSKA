package polsl.tab.skiresort.api.passes.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.api.entry.jwt.JwtTokenUtility;
import polsl.tab.skiresort.api.passes.request.InvoiceRequest;
import polsl.tab.skiresort.api.passes.response.InvoiceResponse;
import polsl.tab.skiresort.api.passes.response.PassResponse;
import polsl.tab.skiresort.model.Invoice;
import polsl.tab.skiresort.model.Pass;
import polsl.tab.skiresort.repository.InvoiceRepository;
import polsl.tab.skiresort.repository.PassRepository;
import polsl.tab.skiresort.repository.PriceListRepository;
import polsl.tab.skiresort.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private final JwtTokenUtility jwtTokenUtility;

    private final UserRepository userRepository;

    private final InvoiceRepository invoiceRepository;

    private final PriceListRepository priceListRepository;

    private final PassRepository passRepository;

    public InvoiceService(JwtTokenUtility jwtTokenUtility,
                          UserRepository userRepository,
                          InvoiceRepository invoiceRepository,
                          PriceListRepository priceListRepository,
                          PassRepository passRepository
    ) {
        this.jwtTokenUtility = jwtTokenUtility;
        this.userRepository = userRepository;
        this.invoiceRepository = invoiceRepository;
        this.priceListRepository = priceListRepository;
        this.passRepository = passRepository;
    }

    public List<InvoiceResponse> getAllUserInvoices(String token) {
        var user = userRepository.findByEmail(jwtTokenUtility.getUsernameFromToken(token));
        if (user.isPresent()) {
            return invoiceRepository.findByUserIdUser(user.get())
                    .stream()
                    .map(InvoiceResponse::new)
                    .collect(Collectors.toList());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    public InvoiceResponse addInvoiceToUser(String token, InvoiceRequest request) {
        var user = userRepository.findByEmail(jwtTokenUtility.getUsernameFromToken(token));
        var currentPriceList = priceListRepository.findCurrentPriceList();
        if (user.isPresent() && currentPriceList.isPresent()) {
            var invoice = new Invoice(
                    request.getInvoiceDate(),
                    request.getBillingAddress(),
                    request.getBillingCity(),
                    request.getBillingState(),
                    request.getBillingCountry(),
                    request.getBillingPostalCode(),
                    request.getTotal(),
                    user.get()
            );
            invoice.setPassList(
                    request.getPassList().stream().map(passRequest -> {
                        if (passRequest.getEndDate() == null &&
                                passRequest.getStartDate() == null) {
                            // Quantity Pass
                            return new Pass(
                                    passRequest.getUnitPrice(),
                                    passRequest.getFirstName(),
                                    passRequest.getLastName(),
                                    passRequest.getBirthDate(),
                                    passRequest.getUsesTotal(),
                                    passRequest.getUsesTotal(),
                                    currentPriceList.get(),
                                    invoice
                            );
                        } else {
                            // Time Pass
                            return new Pass(
                                    passRequest.getUnitPrice(),
                                    passRequest.getStartDate(),
                                    passRequest.getEndDate(),
                                    passRequest.getFirstName(),
                                    passRequest.getLastName(),
                                    passRequest.getBirthDate(),
                                    currentPriceList.get(),
                                    invoice
                            );
                        }
                    }).collect(Collectors.toList())
            );
            return new InvoiceResponse(invoiceRepository.save(invoice));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Check existence of user and price list");
    }

    public List<PassResponse> getAllInvoicePasses(String token, Integer invoiceId) {
        var user = userRepository.findByEmail(jwtTokenUtility.getUsernameFromToken(token));
        if (user.isPresent()) {
            return user.get().getInvoiceList()
                    .stream()
                    .filter(
                            invoice -> invoice.getIdInvoice().equals(invoiceId))
                    .findFirst()
                    .map(
                            invoice -> invoice.getPassList()
                                    .stream()
                                    .map(PassResponse::new)
                                    .collect(Collectors.toList())
                    )
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No invoices found"));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
}
