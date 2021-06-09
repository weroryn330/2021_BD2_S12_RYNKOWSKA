package polsl.tab.skiresort.api.passes.service;

import com.google.zxing.WriterException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.api.entry.jwt.JwtTokenUtility;
import polsl.tab.skiresort.api.passes.qr.ZxingQRGenerator;
import polsl.tab.skiresort.api.passes.request.PassRequest;
import polsl.tab.skiresort.api.passes.response.PassResponse;
import polsl.tab.skiresort.model.Pass;
import polsl.tab.skiresort.repository.InvoiceRepository;
import polsl.tab.skiresort.repository.PassRepository;
import polsl.tab.skiresort.repository.PriceListRepository;
import polsl.tab.skiresort.repository.UserRepository;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PassService {

    private final PassRepository passRepository;

    private final UserRepository userRepository;

    private final JwtTokenUtility jwtTokenUtility;

    private final InvoiceRepository invoiceRepository;

    private final PriceListRepository priceListRepository;

    private static final String USER_EXISTENCE_ERROR = "User does not exist";

    private static final String PASS_EXISTENCE_ERROR = "Pass does not exist";

    public PassService(PassRepository passRepository,
                       UserRepository userRepository,
                       JwtTokenUtility jwtTokenUtility,
                       InvoiceRepository invoiceRepository, PriceListRepository priceListRepository) {
        this.passRepository = passRepository;
        this.userRepository = userRepository;
        this.jwtTokenUtility = jwtTokenUtility;
        this.invoiceRepository = invoiceRepository;
        this.priceListRepository = priceListRepository;
    }

    public List<PassResponse> getAllUserPasses(String token) {
        return passRepository.getAllPassesForUser(
                userRepository.findByEmail(jwtTokenUtility.getUsernameFromToken(token))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, USER_EXISTENCE_ERROR)).getIdUser())
                .stream()
                .map(PassResponse::new)
                .collect(Collectors.toList());
    }

    public List<PassResponse> getAllUserActivePasses(String token) {
        return passRepository.getAllActivePassesForUser(
                userRepository.findByEmail(jwtTokenUtility.getUsernameFromToken(token))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, USER_EXISTENCE_ERROR)).getIdUser())
                .stream()
                .map(PassResponse::new)
                .collect(Collectors.toList());
    }

    public byte[] generateQr(String token, Integer passId) {
        var user = userRepository.findByEmail(jwtTokenUtility.getUsernameFromToken(token));
        if (user.isPresent()) {
            var pass = passRepository.getUserPass(user.get().getIdUser(), passId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PASS_EXISTENCE_ERROR));
            try {
                var qr = ZxingQRGenerator.builder()
                        .setData(pass.toString())
                        .setPath(UUID.randomUUID() + ".jpg")
                        .setHeight(400)
                        .setWidth(400)
                        .build();
                var stream = new ByteArrayOutputStream();
                ImageIO.write(qr.createQrImage(), "jpg", stream);
                return stream.toByteArray();
            } catch (WriterException | IOException exception) {
                exception.printStackTrace();
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, USER_EXISTENCE_ERROR);
    }

    public PassResponse getPassById(String token, Integer passId) {
        return new PassResponse(passRepository.getUserPass(
                userRepository.findByEmail(jwtTokenUtility.getUsernameFromToken(token))
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, USER_EXISTENCE_ERROR))
                        .getIdUser(), passId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PASS_EXISTENCE_ERROR)));
    }

    public List<PassResponse> addPassToUserInvoice(String token, Integer invoiceId, PassRequest request) {
        var invoice = invoiceRepository.findByUserIdUserAndIdInvoice(
            userRepository.findByEmail(jwtTokenUtility.getUsernameFromToken(token))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, USER_EXISTENCE_ERROR)),
            invoiceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found"));
        invoice.getPassList().add(Pass.from(
            request,
            invoice,
            priceListRepository.findCurrentPriceList()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Price list not found"))));
        return invoiceRepository.save(invoice).getPassList()
                .stream()
                .map(PassResponse::new)
                .collect(Collectors.toList());
    }

    public PassResponse editSinglePass(String token, Integer passId, PassRequest request) {
        // TODO
        return null;
    }

    public PassResponse deletePassFromInvoice(String token, Integer invoiceId, Integer passId) {
        // TODO
        return null;
    }
}
