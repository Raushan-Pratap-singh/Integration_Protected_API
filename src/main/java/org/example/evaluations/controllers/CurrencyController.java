package org.example.evaluations.controllers;

import java.util.List;
import org.example.evaluations.dtos.RealTimeNewsResult;
import org.example.evaluations.models.News;
import org.example.evaluations.services.ICurrencyService;
import org.example.evaluations.services.helpers.CompanyCurrencyHelperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class responsible for handling currency-related financial endpoints.
 * <p>
 * This includes:
 * <ul>
 *   <li>Fetching news related to currency conversion between two currencies</li>
 * </ul>
 * </p>
 *
 * Endpoint exposed:
 * <ul>
 *   <li><code>GET /currency/conversionNews?from_symbol=USD&to_symbol=INR</code> - Returns news articles relevant to the given currency pair.</li>
 * </ul>
 *
 * Dependencies:
 * <ul>
 *   <li>{@link ICurrencyService} - Service layer to fetch currency-related news data.</li>
 *   <li>{@link CompanyCurrencyHelperService} - Helper service to format response objects.</li>
 * </ul>
 *
 * @author Raushan Singh
 * @version 1.1
 */
@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private final ICurrencyService currencyService;

    private final CompanyCurrencyHelperService companyCurrencyHelperService;

    public CurrencyController(ICurrencyService currencyService, CompanyCurrencyHelperService companyCurrencyHelperService) {
        this.currencyService = currencyService;
        this.companyCurrencyHelperService = companyCurrencyHelperService;
    }


    /**
     * Retrieves currency-related news articles for a given currency conversion pair.
     *
     * @param from_symbol the source currency symbol (e.g., "USD")
     * @param to_symbol   the target currency symbol (e.g., "INR")
     * @return {@link ResponseEntity} containing a {@link RealTimeNewsResult} with relevant news articles,
     * or a no-content response if no data is available.
     */
    @GetMapping("/conversionNews")
    public ResponseEntity<RealTimeNewsResult> getCurrencyNews(@RequestParam(name = "from_symbol") String from_symbol, @RequestParam(name = "to_symbol") String to_symbol) {
        List<News> news = currencyService.getCurrencyNews(from_symbol, to_symbol);
        return companyCurrencyHelperService.convertToRealTimeNewsResult(news);
    }
}