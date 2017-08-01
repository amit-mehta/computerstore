package au.com.qantas.freight.intlcust.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

    @Bean
    public Docket defaultApiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
//                    .paths(PathSelectors.regex("/customers/.*"))
                .paths(PathSelectors.any())
                .build();
    }

    protected ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("International Customer") //Application name from the maven build information
                .description("This is api for the International Customer") //Application description from the maven build information
                .contact(new Contact("Qantas Freight. Support", "", "dummysupport@email.com"))
                .version("Application version from the maven build information")
                .build();
    }
}

