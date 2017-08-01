package au.com.qantas.freight.intlcust;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@Configuration
@ComponentScan(basePackages = {"au.com.qantas.freight.intlcust"})
@EnableHypermediaSupport(type = {EnableHypermediaSupport.HypermediaType.HAL})
public class ApplicationConfig {
}