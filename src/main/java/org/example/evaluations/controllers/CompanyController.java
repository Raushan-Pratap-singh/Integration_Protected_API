package org.example.evaluations.controllers;

import java.util.List;

import org.example.evaluations.dtos.RealTimeCashFlowResult;
import org.example.evaluations.dtos.RealTimeNewsResult;
import org.example.evaluations.models.CashFlow;
import org.example.evaluations.models.News;
import org.example.evaluations.services.IStockService;
import org.example.evaluations.services.helpers.CompanyCurrencyHelperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller class responsible for handling company-related financial endpoints.
 * <p>
 * This includes:
 * <ul>
 *   <li>Fetching latest stock news for a company</li>
 *   <li>Fetching recent cash flow data for a company</li>
 * </ul>
 * </p>
 *
 * Endpoints exposed:
 * <ul>
 *   <li><code>GET /company/stockNews?symbol=XYZ</code> - Returns news articles related to the given stock symbol.</li>
 *   <li><code>GET /company/cashFlow?symbol=XYZ</code> - Returns cash flow data related to the given stock symbol.</li>
 * </ul>
 *
 * Dependencies:
 * <ul>
 *   <li>{@link IStockService} - Service layer to fetch stock-related data.</li>
 *   <li>{@link CompanyCurrencyHelperService} - Helper service to format response objects.</li>
 * </ul>
 *
 * @author Raushan Singh
 * @version 1.1
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    private final IStockService companyService;

    private final CompanyCurrencyHelperService companyCurrencyHelperService;

    public CompanyController(IStockService companyService, CompanyCurrencyHelperService companyCurrencyHelperService) {
        this.companyService = companyService;
        this.companyCurrencyHelperService = companyCurrencyHelperService;
    }


    /**
     * Retrieves stock-related news articles for a given stock symbol.
     *
     * @param symbol the stock symbol (e.g., "AAPL" for Apple Inc.)
     * @return {@link ResponseEntity} containing a {@link RealTimeNewsResult} with news articles,
     * or an empty response if no news is found.
     */
    @GetMapping("/stockNews")
    public ResponseEntity<RealTimeNewsResult> getStockNews(@RequestParam(name = "symbol") String symbol) {
        List<News> news = companyService.getStockNews(symbol);
        return companyCurrencyHelperService.convertToRealTimeNewsResult(news);
    }

    /**
     * Retrieves the cash flow data for a given company based on the stock symbol.
     *
     * @param symbol the stock symbol (e.g., "AAPL" for Apple Inc.)
     * @return {@link ResponseEntity} containing a {@link RealTimeCashFlowResult} with cash flow details,
     * or a no-content response if data is unavailable.
     */
    @GetMapping("/cashFlow")
    public ResponseEntity<RealTimeCashFlowResult> getCompanyCashFlow(@RequestParam(name = "symbol") String symbol) {
        List<CashFlow> cashFlows = companyService.getCompanyCashFlow(symbol);
        if(cashFlows == null || cashFlows.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        RealTimeCashFlowResult cashFlowResult = RealTimeCashFlowResult.of(cashFlows);
        return ResponseEntity.ok(cashFlowResult);
    }
}

