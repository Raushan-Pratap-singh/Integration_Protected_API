package org.example.evaluations.services;

import org.example.evaluations.configurations.ApiProperties;
import org.example.evaluations.dtos.RealTimeCashFlowResult;
import org.example.evaluations.dtos.RealTimeNewsResult;
import org.example.evaluations.models.CashFlow;
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
 * Service implementation responsible for fetching company-related stock news and financial data
 * such as cash flows by invoking external APIs.
 * <p>
 * This class utilizes {@link RestTemplate} to call APIs and leverages the {@link ApiHelperService}
 * to build URLs and prepare HTTP requests.
 * </p>
 *
 * <p>Supports methods to:</p>
 * <ul>
 *   <li>Fetch company stock-related news articles.</li>
 *   <li>Retrieve the cash flow details of a specific company.</li>
 * </ul>
 *
 * @author Raushan Singh
 * @version 1.1
 */
@Service
public class CompanyStockService implements IStockService {

    private final RestTemplate restTemplate;
    private final ApiHelperService apiHelperService;
    private final ApiProperties apiProperties;

    /**
     * Constructor for dependency injection.
     *
     * @param restTemplate        the RestTemplate to make HTTP requests
     * @param apiHelperService    helper service to build URLs and headers
     * @param apiProperties       configuration properties for external API endpoints
     */
    public CompanyStockService(RestTemplate restTemplate, ApiHelperService apiHelperService, ApiProperties apiProperties) {
        this.restTemplate = restTemplate;
        this.apiHelperService = apiHelperService;
        this.apiProperties = apiProperties;
    }

    /**
     * Fetches the latest news articles related to the given stock symbol.
     *
     * @param symbol the stock symbol (e.g., AAPL, MSFT)
     * @return a list of {@link News} articles or null if not found
     */
    @Override
    public List<News> getStockNews(String symbol) {
        String url = apiHelperService.buildUrl(apiProperties.getStockNews(), Map.of("key", symbol));
        HttpEntity<String> httpEntity = apiHelperService.createHttpEntity();
        ResponseEntity<RealTimeNewsResult> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, RealTimeNewsResult.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            RealTimeNewsResult realTimeNewsResult = response.getBody();
            if (realTimeNewsResult != null) {
                return realTimeNewsResult.getData().getNews();
            }
        }
        return null;
    }

    /**
     * Retrieves the cash flow information of a company using its stock symbol.
     *
     * @param symbol the stock symbol of the company
     * @return a list of {@link CashFlow} records or null if unavailable
     */
    @Override
    public List<CashFlow> getCompanyCashFlow(String symbol) {
        String url = apiHelperService.buildUrl(apiProperties.getCompanyCashflow(), Map.of("key", symbol));
        HttpEntity<String> httpEntity = apiHelperService.createHttpEntity();
        ResponseEntity<RealTimeCashFlowResult> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, RealTimeCashFlowResult.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            RealTimeCashFlowResult realTimeCashFlowResult = response.getBody();
            if (realTimeCashFlowResult != null) {
                return realTimeCashFlowResult.getData().getCash_flow();
            }
        }
        return null;
    }
}