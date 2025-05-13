package org.example.evaluations.configurations;

import org.example.evaluations.services.helpers.CompanyCurrencyHelperService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for the application.
 *
 * <p>This class is responsible for providing configuration beans used throughout the application.
 * It includes a {@link RestTemplate} bean that will be used for making HTTP requests.</p>
 *
 * @author Raushan Singh
 * @version 1.1
 */
@Configuration
public class ApplicationConfig {

    /**
     * Creates and provides a {@link RestTemplate} bean.
     *
     * <p>The {@link RestTemplate} is used for making HTTP requests to external APIs.
     * It is configured as a Spring Bean so it can be injected and reused throughout the application.</p>
     *
     * @return a new instance of {@link RestTemplate}
     */
    @Bean
    public RestTemplate createRestTemplateBean() {
        return new RestTemplate();
    }
}
