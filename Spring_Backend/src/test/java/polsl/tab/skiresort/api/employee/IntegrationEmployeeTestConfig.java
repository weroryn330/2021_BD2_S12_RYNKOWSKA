package polsl.tab.skiresort.api.employee;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public abstract class IntegrationEmployeeTestConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PassRepository passRepository;

    @Autowired
    private PriceListRepository priceListRepository;

    @BeforeEach
    void setup() {
        var priceList = new PriceList(
                Date.valueOf(LocalDate.of(2021, 5, 23)),
                Date.valueOf(LocalDate.of(2022, 5, 23))
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
                Date.valueOf(LocalDate.of(2021, 5, 26)),
                "Test Invoice Billing",
                "Test Invoice City",
                "Test Invoice State",
                "Test Invoice Country",
                "Test Invoice PostalCode",
                123321.00f
        );
        var pass = new Pass(
                100.00f,
                Date.valueOf(LocalDate.of(2021, 5, 26)),
                Date.valueOf(LocalDate.of(2021, 12, 12)),
                "Test Pass First Name",
                "Test Pass Last Name",
                Date.valueOf(LocalDate.of(2002, 3, 12))
        );
        userRepository.save(user);
        invoice.setUserIdUser(user);
        invoiceRepository.save(invoice);
        pass.setInvoicesIdInvoice(invoice);
        priceListRepository.save(priceList);
        pass.setPriceList(priceList);
        passRepository.save(pass);
    }

}
