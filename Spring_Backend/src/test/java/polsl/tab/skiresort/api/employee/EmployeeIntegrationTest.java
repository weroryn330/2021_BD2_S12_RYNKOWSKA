package polsl.tab.skiresort.api.employee;

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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("employee_integration")
class EmployeeIntegrationTest extends IntegrationEmployeeTestConfig{

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "test", password = "test", roles = "EMPLOYEE")
    void getAllActivePassesContainingUserEmailShouldReturnTrue() {
        try {
            JSONArray jsonArray = new JSONArray(mockMvc
                    .perform(
                            MockMvcRequestBuilders.get("/api/employee/activePasses")
                    )
                    .andExpect(status().is2xxSuccessful())
                    .andReturn()
                    .getResponse()
                    .getContentAsString()
            );
            Assertions.assertEquals(
                    passRepository.getAllActivePasses().size(),
                    jsonArray.length()
            );
            Assertions.assertTrue(
                    jsonArray.toString().contains("test@test.pl")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = "EMPLOYEE")
    void getAllPassesContainingUserEmailShouldReturnTrue() {
        try {
            JSONArray jsonArray = new JSONArray(mockMvc
                    .perform(
                            MockMvcRequestBuilders.get("/api/employee/allPasses")
                    )
                    .andExpect(status().is2xxSuccessful())
                    .andReturn()
                    .getResponse()
                    .getContentAsString()
            );
            Assertions.assertEquals(
                    passRepository.findAll().size(),
                    jsonArray.length()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
