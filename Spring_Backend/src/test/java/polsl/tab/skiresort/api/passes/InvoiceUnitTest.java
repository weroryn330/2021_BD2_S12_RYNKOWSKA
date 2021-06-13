package polsl.tab.skiresort.api.passes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import polsl.tab.skiresort.api.passes.request.InvoiceRequest;
import polsl.tab.skiresort.api.passes.request.PassRequest;
import polsl.tab.skiresort.api.passes.service.InvoiceService;
import polsl.tab.skiresort.model.Pass;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class InvoiceUnitTest extends InvoiceUnitTestConfig {

    @Autowired
    private InvoiceService invoiceService;

    private InvoiceRequest invoiceToAdd;

    @BeforeEach
    void setUp() {
        super.setUp();
        invoiceToAdd = new InvoiceRequest();
        invoiceToAdd.setInvoiceDate(Date.valueOf(LocalDate.now().minusDays(2)));
        invoiceToAdd.setBillingAddress("TEST");
        invoiceToAdd.setBillingCity("TEST");
        invoiceToAdd.setBillingCountry("TEST");
        invoiceToAdd.setBillingState("TEST");
        invoiceToAdd.setBillingPostalCode("TEST");
        invoiceToAdd.setTotal(200F);
        List<PassRequest> passesToAddWithInvoice = new ArrayList<>();
        var timePass = new PassRequest();
        timePass.setBirthDate(Date.valueOf(LocalDate.now().minusYears(20)));
        timePass.setStartDate(Timestamp.valueOf(LocalDateTime.now().minusDays(2)));
        timePass.setEndDate(Timestamp.valueOf(LocalDateTime.now().plusDays(2)));
        timePass.setFirstName("Test");
        timePass.setLastName("Test");
        timePass.setUnitPrice(100F);
        passesToAddWithInvoice.add(timePass);
        var quantityPass = new PassRequest();
        quantityPass.setBirthDate(Date.valueOf(LocalDate.now().minusYears(20)));
        quantityPass.setFirstName("Test");
        quantityPass.setLastName("Test");
        quantityPass.setUsesTotal(20);
        quantityPass.setUnitPrice(100F);
        passesToAddWithInvoice.add(quantityPass);
        invoiceToAdd.setPassRequestList(passesToAddWithInvoice);
    }

    @Test
    void mustReturnActiveUserPasses() {
        Assertions.assertEquals(
                invoiceService.getAllUserInvoices(token).size(),
                invoiceRepository.findByUserIdUser(user).size()
        );
    }

    @Test
    void mustAddNewInvoice() {
        var beforeAdding = invoiceService.getAllUserInvoices(token).size();
        Assertions.assertEquals(
                beforeAdding,
                invoiceRepository.findByUserIdUser(user).size()
        );
        invoiceService.addInvoiceToUser(token, this.invoiceToAdd);
        // Increment counter
        beforeAdding++;
        Assertions.assertEquals(
                beforeAdding,
                invoiceRepository.findByUserIdUser(user).size()
        );
    }

    @Test
    void mustAddNewInvoiceThatContainsPasses() {
        var currentInvoicesForUser = invoiceService.getAllUserInvoices(token).size();
        var currentPassesForUser = passRepository.getAllActivePassesForUser(user.getIdUser()).size();
        Assertions.assertEquals(
                currentInvoicesForUser,
                invoiceRepository.findByUserIdUser(user).size()
        );
        var invoiceResponse = invoiceService.addInvoiceToUser(token, this.invoiceToAdd);
        // Should add two passes
        currentPassesForUser += 2;
        // Should add one invoice
        currentInvoicesForUser++;
        Assertions.assertEquals(
                currentPassesForUser,
                passRepository.getAllActivePassesForUser(user.getIdUser()).size()
        );
        Assertions.assertEquals(
                currentInvoicesForUser,
                invoiceRepository.findByUserIdUser(user).size()
        );
        // Check if invoice contains correct passes
        var invoice = invoiceRepository.findById(invoiceResponse.getId()).get();
        for (Pass pass: invoice.getPassList()) {
            Assertions.assertEquals(
                    pass.getBirthDate().toString(),
                    invoiceToAdd.getPassList().get(currentInvoicesForUser - 1).getBirthDate().toString()
            );
            currentInvoicesForUser--;
        }
    }
}
