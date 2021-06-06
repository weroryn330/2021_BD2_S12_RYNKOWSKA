package polsl.tab.skiresort.api.passes;

import org.json.JSONArray;
import org.junit.jupiter.api.Assertions;
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
@ActiveProfiles("invoice_integration")
public class InvoiceIntegrationTest extends InvoiceIntegrationTestConfig {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "test", password = "test",roles = "USER")
    void getAllInvoicePassesContainingUserEmailShouldReturnTrue() {
        try {
            JSONArray jsonArray = new JSONArray(mockMvc
                    .perform(
                            MockMvcRequestBuilders.get("/api/invoices/" + invoice.getIdInvoice()).header("Authorization",getToken())
                    ).andExpect(status().is2xxSuccessful())
                    .andReturn()
                    .getResponse()
                    .getContentAsString()
            );
            Assertions.assertEquals(userRepository.findByEmail(jwtTokenUtility.getUsernameFromToken(getToken())).get()
                    .getInvoiceList()
                    .stream()
                    .filter(
                            invoice -> invoice.getIdInvoice().equals(invoice.getIdInvoice()))
                    .findFirst()
                    .map(
                            invoice -> invoice.getPassList()
                                    .stream()
                                    .map(Pass::new)
                                    .collect(Collectors.toList())).get().size(),
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
