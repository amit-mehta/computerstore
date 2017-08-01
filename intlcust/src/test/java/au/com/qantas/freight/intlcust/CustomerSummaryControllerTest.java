package au.com.qantas.freight.intlcust;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerSummaryControllerTest {

    @InjectMocks
    private CustomerSummaryController controller;

    @Mock
    private CustomerSummaryService service;
    @Mock
    private CustomerSummaryAssembler assembler;

    @Test
    public void testValidateAgent() {
        AgentSummaryRequest agentSummaryRequest = mock(AgentSummaryRequest.class);
        String countryCode = "023",agentCode = "1011", cassCode = "2211";
        when(agentSummaryRequest.getCountryCode()).thenReturn(countryCode);
        when(agentSummaryRequest.getAgentCode()).thenReturn(agentCode);
        when(agentSummaryRequest.getCassCode()).thenReturn(cassCode);
        CustomerSummary mockCustomerSummary = mock(CustomerSummary.class);
        when(service.getCustomerSummary(countryCode, agentCode, cassCode)).thenReturn(mockCustomerSummary);
        ArgumentCaptor<CustomerSummary> captor = ArgumentCaptor.forClass(CustomerSummary.class);

        CustomerSummaryResource customerSummaryResource = controller.validateAgent(agentSummaryRequest);

        verify(assembler,times(1)).toResource(captor.capture());
        assertThat(captor.getValue(), is(mockCustomerSummary));
        verify(service, times(1)).getCustomerSummary(countryCode, agentCode, cassCode);
    }
}
