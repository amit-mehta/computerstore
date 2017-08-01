package au.com.qantas.freight.intlcust;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {
                "html:target/cucumber/internationalcustomer.html",
                "json:target/cucumber/internationalcustomer.json",
                "pretty:target/cucumber/internationalcustomer.txt",
                "junit:target/cucumber/internationalcustomer.xml"},
        features = {
                "classpath:validate.feature"
        }
)
public class InternationalCustomerTest {
}
