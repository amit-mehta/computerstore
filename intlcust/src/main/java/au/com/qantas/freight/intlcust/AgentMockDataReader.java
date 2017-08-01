package au.com.qantas.freight.intlcust;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
@Profile("local-mocked")
public class AgentMockDataReader extends MockDataReader {

    private final AgentRepository repository;

    @Autowired
    public AgentMockDataReader(ResourceLoader resourceLoader, AgentRepository repository) {
        super(resourceLoader);
        this.repository = repository;
    }

    @Override
    protected String getMockDataFile() {
        return "classpath:agent-mock-data.csv";
    }

    @Override
    protected void convertToModelAndSave(CSVRecord record) {

        Agent agent = new Agent();
        agent.setIataCode(record.get(Headers.IATA_CODE));
        agent.setCustomerCode(record.get(Headers.CUSTOMER_CODE));
        repository.save(agent);
    }

    @Override
    protected Class<? extends Enum<?>> getHeaderClass() {
        return Headers.class;
    }

    public enum  Headers {
        CUSTOMER_CODE,IATA_CODE
    }
}
