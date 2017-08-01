package au.com.qantas.freight.intlcust;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.InputStreamReader;
import java.io.Reader;

public abstract class MockDataReader implements CommandLineRunner{

    private final ResourceLoader resourceLoader;

    public MockDataReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    protected abstract String getMockDataFile();
    protected abstract void convertToModelAndSave(CSVRecord record);

    @Override
    public void run(String... strings) throws Exception {
        Resource resource = resourceLoader.getResource(getMockDataFile());
        Reader reader = new InputStreamReader(resource.getInputStream());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(getHeaderClass())
                .withDelimiter('|')
                .withFirstRecordAsHeader()
                .parse(reader);
        records.forEach(this::convertToModelAndSave);
    }

    protected abstract Class<? extends Enum<?>> getHeaderClass();
}
