package polsl.tab.skiresort.api.entry;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
class LoginIntegrationLoginRegisterTest extends IntegrationLoginRegisterTestConfig {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtTokenUtility jwtTokenUtility;

    private String jsonRequest;

    @BeforeEach
    void setUpJsonRequest() {
        this.jsonRequest = "{" +
                "\n" +
                "\"email\": \"test@test.test\",\n" +
                "\"password\": \"testPassword\"\n" +
                "}";
    }

    @Test
    void loginUserShouldReturnStatusOk() {
        try {
            mockMvc
                    .perform(
                            MockMvcRequestBuilders.post("/api/login")
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
    void loginUserShouldReturnJSONAndValidateTokenShouldReturnTrue() {
        try {
            var jsonObject = new JSONObject(
                    mockMvc
                        .perform(
                                MockMvcRequestBuilders.post("/api/login")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .characterEncoding(StandardCharsets.UTF_8.name())
                                        .content(jsonRequest)
                        )
                        .andExpect(status().is2xxSuccessful())
                        .andReturn()
                        .getResponse()
                        .getContentAsString()
            );
            Assertions.assertTrue(jwtTokenUtility.validateToken(
                    jsonObject.get("token").toString(),
                    new UserLoginRequest(
                            jsonObject.getString("email"),
                            jsonObject.getString("password"),
                            jsonObject.getString("role")
                    )
            ));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
