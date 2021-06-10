package polsl.tab.skiresort.api.entry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import polsl.tab.skiresort.api.entry.jwt.JwtTokenUtility;
import polsl.tab.skiresort.api.entry.request.UserLoginRequest;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("integration")
class UserAPIIntegrationTest extends IntegrationTestConfig {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtTokenUtility jwtTokenUtility;

    private String token;

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
                "    \"email\": \"testingUserAPIJson@test.text\",\n" +
                "    \"password\": \"test\"\n" +
                "}";

        this.token = "Bearer " + jwtTokenUtility.generateToken(new UserLoginRequest("test@test.test", "testPassword"));
    }

    @Test
    @Order(1)
    void httpGet_returnUserDetails() {
        try {
            mockMvc
                    .perform(
                            MockMvcRequestBuilders.get("/api/user")
                                    .header("Authorization", token)
                    )
                    .andExpect(status().is2xxSuccessful());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    void httpDelete_deleteUser() {
        try {
            mockMvc
                    .perform(
                            MockMvcRequestBuilders.delete("/api/user")
                                    .header("Authorization", token)
                    )
                    .andExpect(status().is2xxSuccessful());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    @Order(3)
    void httpPost_returnSavedUserDetails() {
        try {
            mockMvc
                    .perform(
                            MockMvcRequestBuilders.post("/api/user")
                                    .header("Authorization", token)
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

    @Test
    @Order(4)
    void httpPut_returnUpdatedUserDetails() {
        try {
            mockMvc
                    .perform(
                            MockMvcRequestBuilders.put("/api/user")
                                    .header("Authorization", token)
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
