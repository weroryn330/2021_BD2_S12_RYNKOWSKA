package polsl.tab.skiresort.api.passes.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.api.passes.response.PassResponse;
import polsl.tab.skiresort.repository.InvoiceRepository;
import polsl.tab.skiresort.repository.PassRepository;
import polsl.tab.skiresort.repository.UserRepository;

import java.sql.Timestamp;

@Service
public class ReturnPassService {

    private final PassRepository passRepository;

    private final InvoiceRepository invoiceRepository;

    private final UserRepository userRepository;

    public ReturnPassService(PassRepository passRepository,
                             InvoiceRepository invoiceRepository,
                             UserRepository userRepository) {
        this.passRepository = passRepository;
        this.invoiceRepository = invoiceRepository;
        this.userRepository = userRepository;
    }

    public PassResponse returnPass(Integer passId) {
        var pass = passRepository.findById(passId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Pass does not exist"));
        if(pass.getUsesTotal() != null && !pass.getUsesTotal().equals(pass.getUsesLeft()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Pass already used, cannot return");
        if(pass.getStartDate() != null && !pass.getStartDate().after(new Timestamp(System.currentTimeMillis())))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Pass already active, cannot return");
        var invoice = invoiceRepository.findById(pass.getInvoicesIdInvoice().getIdInvoice())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invoice not found"));
        if(invoice.getPassList().size() != 1) {
            invoice.setTotal(invoice.getTotal() - pass.getUnitPrice());
            invoiceRepository.save(invoice);
            return new PassResponse(passRepository.deleteByIdPass(pass).get());
        }
        else {
            var returnPass = passRepository.deleteByIdPass(pass).get();
            invoiceRepository.deleteById(invoice.getIdInvoice());
            return new PassResponse(returnPass);
        }
    }
}
