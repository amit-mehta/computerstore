package au.com.qantas.freight.intlcust;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
@Profile("local-mocked")
public class CustomerMockDataReader extends MockDataReader {

    private final CustomerSummaryRepository repository;

    @Autowired
    public CustomerMockDataReader(ResourceLoader resourceLoader, CustomerSummaryRepository repository) {
        super(resourceLoader);
        this.repository = repository;
    }

    @Override
    protected String getMockDataFile() {
        return "classpath:international-customer-summary-mock-data.csv";
    }

    @Override
    protected void convertToModelAndSave(CSVRecord record) {
        CustomerSummary customerSummary = new CustomerSummary();
        customerSummary.setCustomerCode(record.get(Headers.CUSTOMER_CODE));
        customerSummary.setAccountNumber(record.get(Headers.ACCOUNT_NUMBER));
        customerSummary.setCustomerName(record.get(Headers.CUSTOMER_NAME));
        customerSummary.setShopNumber(record.get(Headers.SHOP_NUMBER));
        customerSummary.setStreetNumber(record.get(Headers.STREET_NUMBER));
        customerSummary.setStreetName(record.get(Headers.STREET_NAME));
        customerSummary.setStreetType(record.get(Headers.STREET_TYPE));
        customerSummary.setCity(record.get(Headers.CITY));
        customerSummary.setState(record.get(Headers.STATE));
        customerSummary.setPostCode(record.get(Headers.POSTCODE));
        customerSummary.setCountryCode(record.get(Headers.COUNTRY_CODE));
        repository.save(customerSummary);
    }

    @Override
    protected Class<? extends Enum<?>> getHeaderClass() {
        return Headers.class;
    }

    public enum  Headers {
        CUSTOMER_CODE,ACCOUNT_NUMBER,CUSTOMER_NAME,SHOP_NUMBER,STREET_NUMBER,STREET_NAME,STREET_TYPE,CITY,STATE,
        POSTCODE,COUNTRY_CODE
    }
}
