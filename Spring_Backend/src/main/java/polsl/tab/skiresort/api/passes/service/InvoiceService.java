package polsl.tab.skiresort.api.passes.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.api.entry.jwt.JwtTokenUtility;
import polsl.tab.skiresort.api.passes.request.InvoiceRequest;
import polsl.tab.skiresort.api.passes.response.InvoiceResponse;
import polsl.tab.skiresort.api.passes.response.PassResponse;
import polsl.tab.skiresort.model.Invoice;
import polsl.tab.skiresort.repository.InvoiceRepository;
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

    public InvoiceService(JwtTokenUtility jwtTokenUtility,
                          UserRepository userRepository,
                          InvoiceRepository invoiceRepository,
                          PriceListRepository priceListRepository
    ) {
        this.jwtTokenUtility = jwtTokenUtility;
        this.userRepository = userRepository;
        this.invoiceRepository = invoiceRepository;
        this.priceListRepository = priceListRepository;
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
            return new InvoiceResponse(invoiceRepository.save(new Invoice(
                    request.getInvoiceDate(),
                    request.getBillingAddress(),
                    request.getBillingCity(),
                    request.getBillingState(),
                    request.getBillingCountry(),
                    request.getBillingPostalCode(),
                    request.getTotal(),
                    user.get()
            )));
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
