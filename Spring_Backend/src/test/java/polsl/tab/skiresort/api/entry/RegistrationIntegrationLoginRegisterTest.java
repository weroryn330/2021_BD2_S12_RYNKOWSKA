package polsl.tab.skiresort.api.entry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

@AutoConfigureMockMvc
@ActiveProfiles("integration")
class RegistrationIntegrationLoginRegisterTest extends IntegrationLoginRegisterTestConfig {

    @Autowired
    private MockMvc mockMvc;

    private String jsonRequest;

    @BeforeEach
    void setUpJsonRequest() {
        this.jsonRequest = "{" +
                "    \"firstName\": \"Test\",\n" +
                "    \"lastName\": \"Subject\",\n" +
                "    \"address\": \"address\",\n" +
                "    \"voivodeship\": \"void\",\n" +
                "    \"country\": \"count\",\n" +
                "    \"postalCode\": \"code\",\n" +
                "    \"city\": \"Test\",\n" +
                "    \"phone\": \"Testing\",\n" +
                "    \"email\": \"testingJson@test.text\",\n" +
                "    \"password\": \"test\"\n" +
                "}";
    }

    @Test
    void httpPost_returnGivenUser() {
        try {
            mockMvc
                    .perform(
                            MockMvcRequestBuilders.post("/api/register")
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
