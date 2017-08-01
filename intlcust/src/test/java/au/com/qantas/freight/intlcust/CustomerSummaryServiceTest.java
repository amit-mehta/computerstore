package au.com.qantas.freight.intlcust;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerSummaryServiceTest {

    @InjectMocks
    private CustomerSummaryService service;

    @Mock
    private CustomerSummaryRepository summaryRepository;

    @Mock
    private AgentRepository agentRepository;

    @Test
    public void getCustomerSummaryShouldReturnSummaryWhenItExsitsforAgentCodeAndCassCode() {
        String countryCode ="023", agentCode = "1100", cassCode="3322", customerCode="123123";
        String iataCode = countryCode + agentCode + cassCode;

        Agent mockAgent = mock(Agent.class);
        when(mockAgent.getIataCode()).thenReturn(agentCode+cassCode);
        when(mockAgent.getCustomerCode()).thenReturn(customerCode);
        when(agentRepository.findByIataCode(iataCode)).thenReturn(mockAgent);

        CustomerSummary mockCustomerSummary = mock(CustomerSummary.class);
        when(summaryRepository.findByCustomerCode(customerCode)).thenReturn(mockCustomerSummary);

        CustomerSummary customerSummary = service.getCustomerSummary(countryCode, agentCode, cassCode);
        assertThat(customerSummary, is(mockCustomerSummary));
    }

//    @Test
//    public void getCustomerSummaryShouldReturnSummaryWhenOnly

}