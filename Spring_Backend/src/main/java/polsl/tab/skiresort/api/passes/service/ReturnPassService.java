package polsl.tab.skiresort.api.passes.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.api.entry.jwt.JwtTokenUtility;
import polsl.tab.skiresort.api.passes.response.PassResponse;
import polsl.tab.skiresort.repository.InvoiceRepository;
import polsl.tab.skiresort.repository.PassRepository;
import polsl.tab.skiresort.repository.UserRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class ReturnPassService {

    private final PassRepository passRepository;

    private final InvoiceRepository invoiceRepository;

    private final JwtTokenUtility jwtTokenUtility;

    private final UserRepository userRepository;

    public ReturnPassService(PassRepository passRepository,
                             InvoiceRepository invoiceRepository,
                             JwtTokenUtility jwtTokenUtility, UserRepository userRepository) {
        this.passRepository = passRepository;
        this.invoiceRepository = invoiceRepository;
        this.jwtTokenUtility = jwtTokenUtility;
        this.userRepository = userRepository;
    }

    public PassResponse returnPass(String token, Integer passId) {
        var pass = passRepository.getAllPassesForUser(
                userRepository.findByEmail(jwtTokenUtility.getUsernameFromToken(token))
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"))
                        .getIdUser()
        )
            .stream()
            .filter(passList -> passList.getIdPass().equals(passId))
            .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pass not found"));
        if (
                (pass.getBlocked() != null && Boolean.TRUE.equals(pass.getBlocked()))
             || (pass.getUsesTotal() != null && !pass.getUsesLeft().equals(pass.getUsesTotal()))
             || (pass.getStartDate() != null && !pass.getStartDate().after(Timestamp.valueOf(LocalDateTime.now())))
        ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pass has been used");
        }
        var invoice = invoiceRepository
                .findInvoiceByPassListContaining(pass)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Invoice for pass does not exist"));
        passRepository.deletePass(pass.getIdPass());
        invoice = invoiceRepository.findById(invoice.getIdInvoice())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found"));
        if (invoice.getPassList().isEmpty()) {
            invoiceRepository.deleteInvoice(invoice.getIdInvoice());
            return new PassResponse(pass);
        }
        invoice.setTotal(invoice.getTotal() - pass.getUnitPrice());
        invoiceRepository.save(invoice);
        return new PassResponse(pass);
    }
}
