package org.sample.store;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.sample.store"})
public class AppTestConfig {

    @Bean
    public OrderReader getOrderReader() {
        return new OrderReader();
    }
    @Bean
    public CatalogService getCatalogService() {
        return new CatalogService();
    }

    @Bean
    public DiscountRuleEngine getDiscountRuleEngine() {
        return new DiscountRuleEngine();
    }

    @Bean
    public CalculateDiscountService getCalculateDiscountService() {
        return new CalculateDiscountService(getCatalogService(), getDiscountRuleEngine());
    }

    @Bean
    public CalculateTotalService getCalculateTotalService() {
        return new CalculateTotalService(getCatalogService());
    }

    @Bean
    public BillingService getBillingService() {
        return new BillingService(getCalculateTotalService(),getCalculateDiscountService(),getOrderReader());
    }

}

