package polsl.tab.skiresort.api.passes;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import polsl.tab.skiresort.api.entry.jwt.JwtTokenUtility;
import polsl.tab.skiresort.api.entry.request.UserLoginRequest;
import polsl.tab.skiresort.model.Invoice;
import polsl.tab.skiresort.model.Pass;
import polsl.tab.skiresort.model.PriceList;
import polsl.tab.skiresort.model.User;
import polsl.tab.skiresort.repository.InvoiceRepository;
import polsl.tab.skiresort.repository.PassRepository;
import polsl.tab.skiresort.repository.PriceListRepository;
import polsl.tab.skiresort.repository.UserRepository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;

@SpringBootTest
@Transactional
abstract class InvoiceIntegrationTestConfig {

    @Autowired
    public PassRepository passRepository;

    @Autowired
    public InvoiceRepository invoiceRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PriceListRepository priceListRepository;

    @Autowired
    private JwtTokenUtility jwtTokenUtility;

    private String token;

    public String getToken()
    {
        return this.token;
    }
    @BeforeEach
    void setup() {
        this.token = "Bearer " + jwtTokenUtility.generateToken(new UserLoginRequest("test@test.pl", "testPassword"));

        var priceList = new PriceList(
                Date.valueOf(LocalDate.of(2021, 5, 23)),
                Date.valueOf(LocalDate.of(2022, 5, 23))
        );
        var pass = new Pass(
                100.00f,
                Date.valueOf(LocalDate.of(2021, 5, 26)),
                Date.valueOf(LocalDate.of(2021, 12, 12)),
                "Test Pass First Name",
                "Test Pass Last Name",
                Date.valueOf(LocalDate.of(2002, 3, 12)),
                priceList
        );
        var user = new User(
                "Test User First Name",
                "Test User Last Name",
                "Test User Address",
                "Test User City",
                "Test User Voivodeship",
                "Test User Country",
                "Test User PostalCode",
                "Test User Phone",
                "test@test.pl",
                passwordEncoder.encode("testPassword")
        );
        var invoice = new Invoice(
                25,
                Date.valueOf(LocalDate.of(2021, 5, 26)),
                "Test Invoice Billing",
                "Test Invoice City",
                "Test Invoice State",
                "Test Invoice Country",
                "Test Invoice PostalCode",
                123321.00f
        );
        userRepository.save(user);
        invoice.setUserIdUser(user);
        invoiceRepository.save(invoice);
        pass.setInvoicesIdInvoice(invoice);
        pass.setPriceList(priceList);
        passRepository.save(pass);

    }
}
