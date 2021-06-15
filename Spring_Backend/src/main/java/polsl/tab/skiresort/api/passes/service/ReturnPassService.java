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
        if(!pass.getUsesTotal().equals(pass.getUsesLeft()) && pass.getUsesTotal() != null)
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Pass already used, cannot return");
        if(!pass.getStartDate().after(new Timestamp(System.currentTimeMillis())) && pass.getStartDate() != null)
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Pass already active, cannot return");


        return null;
    }
}
