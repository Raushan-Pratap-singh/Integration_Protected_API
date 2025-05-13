package org.example.evaluations.services.helpers;

import org.example.evaluations.configurations.ApiProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.StringJoiner;

/**
 * Utility service responsible for:
 * <ul>
 *   <li>Creating HTTP entities with required headers</li>
 *   <li>Dynamically building URLs by combining static and dynamic query parameters</li>
 * </ul>
 *
 * This service reads API configuration from {@link ApiProperties} and helps standardize external API requests.
 *
 * Example usage:
 * <pre>
 *     Map.of("key", "AAPL") → will be translated using mapped query keys
 *     → full URL: https://api.example.com?symbol=AAPL&language=en
 * </pre>
 *
 * @author Raushan Singh
 * @version 1.1
 */
@Service
public class ApiHelperService {

    private final ApiProperties apiProperties;

    public ApiHelperService(ApiProperties apiProperties) {
        this.apiProperties = apiProperties;
    }

    /**
     * Constructs an {@link HttpEntity} with pre-configured headers (like API Key and Host).
     *
     * @return HttpEntity with headers from application configuration.
     */
    public HttpEntity<String> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        apiProperties.getHeaders().forEach(headers::add);
        return new HttpEntity<>(headers);
    }

    /**
     * Builds a complete URL by appending query parameters based on the given endpoint configuration
     * and runtime dynamic values (like symbol, from_currency, etc.).
     * <p>
     * Dynamic keys (e.g., "from", "to") are mapped via YAML configuration to actual query parameter names
     * (e.g., "from_symbol", "to_symbol").
     * <br>
     * Also includes static query parameters such as "language" if not overridden.
     * </p>
     *
     * @param endpoint      the endpoint configuration from the YAML
     * @param dynamicParams runtime query params passed by the service
     * @return fully constructed URL with both dynamic and static query parameters
     */
    public String buildUrl(ApiProperties.Endpoint endpoint, Map<String, String> dynamicParams) {
        StringJoiner queryJoiner = new StringJoiner("&");
        Map<String, String> configuredParams = endpoint.getQueryParams();

        // Add dynamic query parameters using mapped keys
        if (dynamicParams != null) {
            for (Map.Entry<String, String> entry : dynamicParams.entrySet()) {
                String mappedKey = configuredParams.get(entry.getKey());
                if (mappedKey != null && entry.getValue() != null) {
                    queryJoiner.add(mappedKey + "=" + entry.getValue());
                }
            }
        }

        // Add static query params like 'language' if not already included
        for (Map.Entry<String, String> entry : configuredParams.entrySet()) {
            if (!dynamicParams.containsKey(entry.getKey())) {
                queryJoiner.add(entry.getKey() + "=" + entry.getValue());
            }
        }

        return endpoint.getUrl() + "?" + queryJoiner;
    }
}