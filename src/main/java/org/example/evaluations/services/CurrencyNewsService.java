package org.example.evaluations.services;

import org.example.evaluations.configurations.ApiProperties;
import org.example.evaluations.dtos.RealTimeNewsResult;
import org.example.evaluations.models.News;
import org.example.evaluations.services.helpers.ApiHelperService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Service implementation responsible for fetching currency conversion-related news
 * from external APIs using configured endpoints.
 *
 * <p>This class handles:</p>
 * <ul>
 *     <li>Constructing requests using configured headers and dynamic parameters.</li>
 *     <li>Invoking remote APIs to retrieve currency news data.</li>
 *     <li>Parsing and returning the response in a structured format.</li>
 * </ul>
 *
 * @author Raushan Singh
 * @version 1.1
 */
@Service
public class CurrencyNewsService implements ICurrencyService {

    private final RestTemplate restTemplate;
    private final ApiHelperService apiHelperService;
    private final ApiProperties apiProperties;

    /**
     * Constructs a {@code CurrencyNewsService} with necessary dependencies.
     *
     * @param restTemplate       the RestTemplate for making HTTP calls
     * @param apiHelperService   helper service for URL construction and headers
     * @param apiProperties      configuration holding API endpoint information
     */
    public CurrencyNewsService(RestTemplate restTemplate, ApiHelperService apiHelperService, ApiProperties apiProperties) {
        this.restTemplate = restTemplate;
        this.apiHelperService = apiHelperService;
        this.apiProperties = apiProperties;
    }

    /**
     * Fetches news articles related to a currency conversion between two given symbols.
     *
     * @param fromCurrency the source currency symbol (e.g., "USD")
     * @param toCurrency   the target currency symbol (e.g., "EUR")
     * @return a list of {@link News} articles, or null if no content is returned
     */
    @Override
    public List<News> getCurrencyNews(String fromCurrency, String toCurrency) {
        HttpEntity<String> httpEntity = apiHelperService.createHttpEntity();
        String url = apiHelperService.buildUrl(apiProperties.getCurrencyNews(), Map.of("from", fromCurrency, "to", toCurrency));
        ResponseEntity<RealTimeNewsResult> response = restTemplate.exchange("https://real-time-finance-data.p.rapidapi.com/currency-news?from_symbol={currency1}&to_symbol={currency2}", HttpMethod.GET, httpEntity, RealTimeNewsResult.class, fromCurrency, toCurrency);
        if (response.getStatusCode().is2xxSuccessful()) {
            RealTimeNewsResult result = response.getBody();
            if (result != null) {
                return result.getData().getNews();
            }
        }
        return null;
    }
}
