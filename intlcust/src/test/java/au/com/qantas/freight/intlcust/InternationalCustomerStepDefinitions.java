package au.com.qantas.freight.intlcust;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@EnableSpringDataWebSupport
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase
public class InternationalCustomerStepDefinitions {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerSummaryController controller;

    @Mock
    private CustomerSummaryService service;

    private String HAL_MEDIA_TYPE = "application/hal+json";

    private ResultActions resultActions;

    private CustomerSummary mockCustomerSummary;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(controller, "service", service);
        mockCustomerSummary = createMockCustomerSummary();
    }

    @Given("^An international agent with countryCode: (\\d*), agentCode: (\\d*) and cassCode: (.*|\\d+) (does|doesn't) exist$")
    public void aDomesticCustomerExistsForGivenCustomerCodeAndAccountNumber(final String countryCode,
                                                final String agentCode, final String cassCode, final String exists) {

        if ("does".equals(exists)) {
            when(service.getCustomerSummary(anyString(), anyString(), anyString())).thenReturn(mockCustomerSummary);
        } else {
            when(service.getCustomerSummary(eq(countryCode), eq(agentCode), eq(cassCode))).thenReturn(null);
        }
    }

    @When("^validating international customer request with (.*)$")
    public void validatingInternationalCustomerRequestWithRequestData(String requestData) throws Throwable {
        resultActions = mockMvc.perform(post("/internationalcustomer/validate/agent")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestData)
                .accept(HAL_MEDIA_TYPE));
    }

    @Then("^a http status code (\\d+) should be returned for customer summary$")
    public void aHttpStatusCodeStatusShouldBeReturnedForCustomerSummary(int httpStatus) throws Throwable {
        resultActions.andExpect(status().is(httpStatus));
    }

    @And("^a response matching summary with result: (Success|Failure|ValidationFailure|\\s*) and errorCount: (\\d) and message: '(.*)' should be returned$")
    public void aResponseMatchingSummaryWithResultResultAndErrorCountErrorCountAndMessageMessageShouldBeReturned
            (String result, int errorCount, String message) throws Throwable {
        if ("Success".equals(result)) {
            resultActions
                    .andExpect(jsonPath("$.customerCode").value(mockCustomerSummary.getCustomerCode()))
                    .andExpect(jsonPath("$.customerName").value(mockCustomerSummary.getCustomerName()))
                    .andExpect(jsonPath("$.shopNumber").value(mockCustomerSummary.getShopNumber()))
                    .andExpect(jsonPath("$.streetNumber").value(mockCustomerSummary.getStreetNumber()))
                    .andExpect(jsonPath("$.streetName").value(mockCustomerSummary.getStreetName()))
                    .andExpect(jsonPath("$.streetType").value(mockCustomerSummary.getStreetType()))
                    .andExpect(jsonPath("$.city").value(mockCustomerSummary.getCity()))
                    .andExpect(jsonPath("$.state").value(mockCustomerSummary.getState()))
                    .andExpect(jsonPath("$.postCode").value(mockCustomerSummary.getPostCode()))
                    .andExpect(jsonPath("$.countryCode").value(mockCustomerSummary.getCountryCode()));
        } else if ("ValidationFailure".equals(result)) {
            resultActions
                    .andExpect(jsonPath("$.message").value("Validation failed. Error count: " + errorCount));
            switch (errorCount) {
                case 1:
                    resultActions.andExpect(jsonPath("$.fieldErrors[0].message").value(message));
                    break;
                case 2: {
                    resultActions
                            .andExpect(jsonPath("$.fieldErrors[0].message").value(message.split(",")[0]));
                    resultActions
                            .andExpect(jsonPath("$.fieldErrors[1].message").value(message.split(",")[1]));
                }
            }
        }
    }

    private CustomerSummary createMockCustomerSummary() {
        CustomerSummary mockCustomerSummary = new CustomerSummary();
        mockCustomerSummary.setCustomerCode("0119607005");
        mockCustomerSummary.setCustomerName("DSV AIR & SEA INC");
        mockCustomerSummary.setShopNumber("SUITE 101");
        mockCustomerSummary.setStreetNumber("8380");
        mockCustomerSummary.setStreetName("N. ARLINGTON HEIGHTS");
        mockCustomerSummary.setStreetType("ROAD");
        mockCustomerSummary.setCity("ORD");
        mockCustomerSummary.setState("VIC");
        mockCustomerSummary.setPostCode("3043");
        mockCustomerSummary.setCountryCode("AU");
        return mockCustomerSummary;
    }
}
