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
    public JwtTokenUtility jwtTokenUtility;

    public String token;

    public Invoice invoice;

    public PriceList priceList;

    public String jsonRequest;

    @BeforeEach
    void setup() {

        this.jsonRequest = "{" +
                "    \"invoiceDate\": \"2026-04-22\",\n" +
                "    \"billingAddress\": \"Test Billing Address\",\n" +
                "    \"billingCity\": \"Test Billing City\",\n" +
                "    \"billingState\": \"Test Billing State\",\n" +
                "    \"billingCountry\": \"Test Billing Country\",\n" +
                "    \"billingPostalCode\": \"Test Billing Postal Code\",\n" +
                "    \"total\": 100.0,\n" +
                "    \"passList\": \n" +
                "[\n" +
                "{" +
                "    \"unitPrice\": 100.0,\n" +
                "    \"startDate\": \"2021-06-22\",\n" +
                "    \"endDate\": \"2021-06-26\",\n" +
                "    \"firstName\": \"Test First Name\",\n" +
                "    \"lastName\": \"Test Last Name\",\n" +
                "    \"birthDate\": \"1999-04-22\",\n" +
                "    \"usesTotal\": null\n" +
                "}\n" +
                "]\n" +
                "}";

        this.token = "Bearer " + jwtTokenUtility.generateToken(new UserLoginRequest("test@test.pl", "testPassword"));

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

        invoice = new Invoice(
                Date.valueOf(LocalDate.of(2021, 5, 26)),
                "Test Invoice Billing",
                "Test Invoice City",
                "Test Invoice State",
                "Test Invoice Country",
                "Test Invoice PostalCode",
                123321.00f,
                user
        );

        priceList = new PriceList(
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
                priceList,
                invoice
        );

        var pass1 = new Pass(
                100.00f,
                "Test Pass1 First Name",
                "Test Pass1 Last Name",
                Date.valueOf(LocalDate.of(2001, 3, 12)),
                10,
                10,
                priceList,
                invoice
        );

        userRepository.save(user);
        invoice.setUserIdUser(user);
        invoiceRepository.save(invoice);
        priceListRepository.save(priceList);
        pass.setInvoicesIdInvoice(invoice);
        pass.setPriceList(priceList);
        pass1.setInvoicesIdInvoice(invoice);
        pass1.setPriceList(priceList);
        passRepository.save(pass);
    }
}
