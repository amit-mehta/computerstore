package au.com.qantas.freight.intlcust;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerSummaryService {

    private static final int IATA_CODE_LENGTH = 11, AGENT_CODE_LENGTH = 7;

    @Autowired
    private CustomerSummaryRepository summaryRepository;
    @Autowired
    private AgentRepository agentRepository;


    public CustomerSummary getCustomerSummary(String countryCode, String agentCode, String cassCode) {

        String iataCode = countryCode + agentCode + (cassCode!= null ? cassCode : "");
        Agent agentDetail = agentRepository.findByIataCode(iataCode);
        if(agentDetail != null) {
            return summaryRepository.findByCustomerCode(agentDetail.getCustomerCode());
        }
        return null;
    }

}
