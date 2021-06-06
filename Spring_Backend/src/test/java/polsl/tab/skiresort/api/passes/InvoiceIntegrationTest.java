package polsl.tab.skiresort.api.passes;

import org.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import polsl.tab.skiresort.api.passes.response.PassResponse;
import polsl.tab.skiresort.model.Pass;

import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class InvoiceIntegrationTest extends InvoiceIntegrationTestConfig {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllInvoicePassesContainingUserEmailShouldReturnTrue() {
        try {
            var invoiceId = invoice.getIdInvoice();
            JSONArray jsonArray = new JSONArray(mockMvc
                    .perform(
                            MockMvcRequestBuilders.get("/api/invoices/" + invoiceId)
                                    .header("Authorization", token)
                    ).andExpect(status().is2xxSuccessful())
                    .andReturn()
                    .getResponse()
                    .getContentAsString()
            );
            Assertions.assertEquals(userRepository.findByEmail(jwtTokenUtility.getUsernameFromToken(token)).get()
                    .getInvoiceList()
                    .stream()
                    .filter(
                            invoice -> invoice.getIdInvoice().equals(invoiceId))
                    .findFirst()
                    .get()
                    .getPassList()
                    .size(),
                    jsonArray.length()
            );
            Assertions.assertTrue(
                    jsonArray.toString().contains("test@test.pl")
            );
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
