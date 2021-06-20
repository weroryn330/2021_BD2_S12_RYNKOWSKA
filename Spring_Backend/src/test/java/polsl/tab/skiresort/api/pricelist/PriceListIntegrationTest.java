package polsl.tab.skiresort.api.pricelist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceListIntegrationTest extends PriceListIntegrationTestConfig {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "test", password = "test", roles = "EMPLOYEE")
    void modifyCurrentPriceListAndDeactivateOldOneShouldReturnTrue()
    {
        try {
            mockMvc
                    .perform(
                            MockMvcRequestBuilders.post("/api/priceList/edit/priceList")
                            .contentType(MediaType.APPLICATION_JSON)
                            .characterEncoding(StandardCharsets.UTF_8.name())
                            .content(jsonRequest)
                    )
                    .andExpect(status().is2xxSuccessful());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
