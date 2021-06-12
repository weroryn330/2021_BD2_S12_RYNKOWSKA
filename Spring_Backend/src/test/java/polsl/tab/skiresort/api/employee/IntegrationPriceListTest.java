package polsl.tab.skiresort.api.employee;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import polsl.tab.skiresort.model.PriceList;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("integration")
class IntegrationPriceListTest extends IntegrationEmployeeTestConfig {

    @Autowired
    private MockMvc mockMvc;

    private String jsonDateRequest;

    @BeforeEach
    void setup() {
        this.jsonDateRequest =
                "{ " +
                "\"startDate\": \"2019-06-20\", " +
                "\"endDate\": \"2022-09-20\" " +
                "}";
    }

    @Test
    void get_Current_Price_List_Should_return_200_successful() {
        try {
            mockMvc
                    .perform(
                            MockMvcRequestBuilders.get("/api/priceList/current")
                    )
                    .andExpect(status().is2xxSuccessful());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void get_all_Price_lists_should_assert_equals() {
        try {
            JSONArray jsonArray = new JSONArray(mockMvc
                .perform(
                        MockMvcRequestBuilders.get("api/priceList/all")
                )
                    .andExpect(status().is2xxSuccessful())
                    .andReturn()
                    .getResponse()
                    .getContentAsString());
            Assertions.assertEquals(
                    priceListRepository.findAll().size(),
                    jsonArray.length()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = "EMPLOYEE")
    void modify_current_price_lists_date_should_assert_equals() {
        try {
            var jsonDate = new JSONObject(jsonDateRequest);
            var currentPriceList = priceListRepository.findCurrentPriceList();
            var jsonPriceList = new PriceList(
                    Date.valueOf(jsonDate.getString("startDate")),
                    Date.valueOf(jsonDate.getString("endDate"))
            );
            Assertions.assertNotEquals(
                    jsonPriceList.getStartDate().toString(),
                    currentPriceList.get().getStartDate().toString()
            );
            Assertions.assertNotEquals(
                    jsonPriceList.getEndDate().toString(),
                    currentPriceList.get().getEndDate().toString()
            );
            mockMvc
                    .perform(
                            MockMvcRequestBuilders.put("/api/priceList/edit/current")
                            .param("startDate", jsonDate.getString("startDate"))
                            .param("endDate", jsonDate.getString("endDate"))
                    )
                    .andExpect(status().is2xxSuccessful());
            currentPriceList = priceListRepository.findCurrentPriceList();
            Assertions.assertEquals(
                    jsonPriceList.getStartDate().toString(),
                    currentPriceList.get().getStartDate().toString()
            );
            Assertions.assertEquals(
                    jsonPriceList.getEndDate().toString(),
                    currentPriceList.get().getEndDate().toString()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Disabled
    @WithMockUser(username = "test", password = "test", roles = "EMPLOYEE")
    void modify_current_price_lists_date_should_throw_bad_request_price_list_incorrect_date() {
        try {
            var jsonDate = new JSONObject(jsonDateRequest);
            var currentPriceList = priceListRepository.findCurrentPriceList();
            var jsonPriceList = new PriceList(
                    Date.valueOf(jsonDate.getString("startDate")),
                    Date.valueOf(jsonDate.getString("endDate"))
            );
            Assertions.assertNotEquals(
                    jsonPriceList.getStartDate().toString(),
                    currentPriceList.get().getStartDate().toString()
            );
            Assertions.assertNotEquals(
                    jsonPriceList.getEndDate().toString(),
                    currentPriceList.get().getEndDate().toString()
            );
            jsonPriceList.setStartDate(Date.valueOf(LocalDate.of(2021, 7, 20)));
            jsonPriceList.setEndDate(Date.valueOf(LocalDate.of(2023, 10, 21)));
            mockMvc
                    .perform(
                            MockMvcRequestBuilders.put("/api/priceList/edit/current")
                                    .param("startDate", jsonPriceList.getStartDate().toString())
                                    .param("endDate", jsonPriceList.getEndDate().toString())
                    )
                    .andExpect(status().is4xxClientError());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
