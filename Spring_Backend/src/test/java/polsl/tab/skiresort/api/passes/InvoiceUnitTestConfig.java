package polsl.tab.skiresort.api.passes;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import polsl.tab.skiresort.api.entry.jwt.JwtTokenUtility;
import polsl.tab.skiresort.api.entry.request.UserLoginRequest;
import polsl.tab.skiresort.model.Invoice;
import polsl.tab.skiresort.model.Pass;
import polsl.tab.skiresort.model.User;
import polsl.tab.skiresort.repository.InvoiceRepository;
import polsl.tab.skiresort.repository.PassRepository;
import polsl.tab.skiresort.repository.PriceListRepository;
import polsl.tab.skiresort.repository.UserRepository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;

@Transactional
@SpringBootTest
abstract class InvoiceUnitTestConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtility jwtTokenUtility;

    @Autowired
    public InvoiceRepository invoiceRepository;

    @Autowired
    private PriceListRepository priceListRepository;

    @Autowired
    public PassRepository passRepository;

    public User user;

    public Pass pass;

    public String token;

    public Invoice invoice;

    @BeforeEach
    void setUp() {
        user = new User(
                "Test User First Name",
                "Test User Last Name",
                "Test User Address",
                "Test User City",
                "Test User Voivodeship",
                "Test User Country",
                "Test User PostalCode",
                "Test User Phone",
                "test@test.pl",
                passwordEncoder.encode("123")
        );
        token = "Bearer " + jwtTokenUtility.generateToken(new UserLoginRequest(
                "test@test.pl",
                passwordEncoder.encode("123"),
                "ROLE_USER"
        ));
        userRepository.save(user);
        invoice = new Invoice(
                Date.valueOf(LocalDate.now()),
                "Test",
                "Test",
                "Test",
                "Test",
                "Test",
                200F,
                user
        );
        invoiceRepository.save(invoice);
        pass = new Pass(
                100F,
                Date.valueOf(LocalDate.now().minusDays(5)),
                Date.valueOf(LocalDate.now().plusDays(5)),
                "Test",
                "Test",
                Date.valueOf(LocalDate.now().minusYears(20)),
                priceListRepository.findCurrentPriceList().get(),
                invoice
        );
        passRepository.save(pass);
    }
}
