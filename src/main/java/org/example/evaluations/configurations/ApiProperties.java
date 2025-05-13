package org.example.evaluations.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Configuration properties class that holds API-related configurations.
 *
 * <p>This class is used to load properties defined in the `application.yml` or `application.properties`
 * file under the prefix {@code api}. It contains endpoints for various API services and the headers
 * used for API requests.</p>
 *
 * <p>It includes specific API configurations for stock news, company cashflow, and currency news,
 * as well as headers that are to be sent in the requests.</p>
 *
 * @author Raushan Singh
 * @version 1.1
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "api")
public class ApiProperties {

    /**
     * Endpoint configuration for stock news API.
     *
     * <p>Contains the URL and query parameters specific to the stock news service API.</p>
     */
    private Endpoint stockNews;

    /**
     * Endpoint configuration for company cashflow API.
     *
     * <p>Contains the URL and query parameters specific to the company cashflow service API.</p>
     */
    private Endpoint companyCashflow;

    /**
     * Endpoint configuration for currency news API.
     *
     * <p>Contains the URL and query parameters specific to the currency news service API.</p>
     */
    private Endpoint currencyNews;

    /**
     * Map that holds headers to be included in the API requests.
     *
     * <p>These headers are fetched from the application configuration and are applied to all API requests.</p>
     */
    private Map<String, String> headers;

    /**
     * Represents an individual API endpoint configuration, including URL and query parameters.
     */
    @Setter
    @Getter
    public static class Endpoint {

        /**
         * The URL of the API endpoint.
         *
         * <p>This is the base URL that will be used for the API request.</p>
         */
        private String url;

        /**
         * The query parameters for the API endpoint.
         *
         * <p>This map holds key-value pairs where the key is the name of the query parameter
         * and the value is its corresponding value for the API request.</p>
         */
        private Map<String, String> queryParams;
    }
}
