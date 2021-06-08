package polsl.tab.skiresort.api.passes.service;

import com.google.zxing.WriterException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.api.entry.jwt.JwtTokenUtility;
import polsl.tab.skiresort.api.passes.qr.ZxingQRGenerator;
import polsl.tab.skiresort.api.passes.request.PassRequest;
import polsl.tab.skiresort.api.passes.response.PassResponse;
import polsl.tab.skiresort.repository.PassRepository;
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

    private static final String USER_EXISTENCE_ERROR = "User does not exist";

    private static final String PASS_EXISTENCE_ERROR = "Pass does not exist";

    public PassService(PassRepository passRepository,
                       UserRepository userRepository,
                       JwtTokenUtility jwtTokenUtility
    ) {
        this.passRepository = passRepository;
        this.userRepository = userRepository;
        this.jwtTokenUtility = jwtTokenUtility;
    }

    public List<PassResponse> getAllUserPasses(String token) {
        var user = userRepository.findByEmail(jwtTokenUtility.getUsernameFromToken(token));
        if (user.isPresent()) {
            return passRepository.getAllPassesForUser(user.get().getIdUser())
                    .stream()
                    .map(PassResponse::new)
                    .collect(Collectors.toList());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, USER_EXISTENCE_ERROR);
    }

    public List<PassResponse> getAllUserActivePasses(String token) {
        var user = userRepository.findByEmail(jwtTokenUtility.getUsernameFromToken(token));
        if (user.isPresent()) {
            return passRepository.getAllActivePassesForUser(user.get().getIdUser())
                    .stream()
                    .map(PassResponse::new)
                    .collect(Collectors.toList());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, USER_EXISTENCE_ERROR);
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
        var user = userRepository.findByEmail(jwtTokenUtility.getUsernameFromToken(token));
        if (user.isPresent()) {
            return new PassResponse(passRepository.getUserPass(
                    user.get().getIdUser(), passId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PASS_EXISTENCE_ERROR)));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, USER_EXISTENCE_ERROR);
    }

    public List<PassResponse> addPassToUserInvoice(String token, Integer invoiceId, PassRequest request) {
        var user = userRepository.findByEmail(jwtTokenUtility.getUsernameFromToken(token));
        if (user.isPresent()) {
            // TODO
            return null;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, USER_EXISTENCE_ERROR);
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
