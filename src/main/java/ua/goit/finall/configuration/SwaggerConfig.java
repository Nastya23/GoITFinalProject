package ua.goit.finall.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ua.goit.final.controller.rest"))
                .paths(regex("/departments.*"))
                .paths(regex("/employees.*"))
                .paths(regex("/events.*"))
                .paths(regex("/eventTypes.*"))
                .paths(regex("/personalSalaries.*"))
                .paths(regex("/positions.*"))
                .paths(regex("/roles.*"))
                .paths(regex("/salaries.*"))
                .paths(regex("/statuses.*"))
                .paths(regex("/users.*"))
                .build();
    }
}
