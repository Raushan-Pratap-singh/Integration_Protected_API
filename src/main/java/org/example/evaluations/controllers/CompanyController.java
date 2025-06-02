package org.example.evaluations.controllers;

import java.util.List;

import org.example.evaluations.dtos.RealTimeCashFlowResult;
import org.example.evaluations.dtos.RealTimeNewsResult;
import org.example.evaluations.models.CashFlow;
import org.example.evaluations.models.News;
import org.example.evaluations.services.IStockService;
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
 * </ul>
 *
 * @author Raushan Singh
 * @version 1.1
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    private final IStockService companyService;

    public CompanyController(IStockService companyService) {
        this.companyService = companyService;
    }


    /**
     * Retrieves stock-related news articles for a given stock symbol.
     *
     * @param symbol the stock symbol (e.g., "AAPL" for Apple Inc.)
     * @return {@link ResponseEntity} containing a {@link RealTimeNewsResult} with news articles,
     * or an empty response if no news is found.
     */
    @GetMapping("/stockNews")
    public List<News> getStockNews(@RequestParam(name = "symbol") String symbol) {
        return companyService.getStockNews(symbol);
        //return companyCurrencyHelperService.convertToRealTimeNewsResult(news);
    }

    /**
     * Retrieves the cash flow data for a given company based on the stock symbol.
     *
     * @param symbol the stock symbol (e.g., "AAPL" for Apple Inc.)
     * @return {@link ResponseEntity} containing a {@link RealTimeCashFlowResult} with cash flow details,
     * or a no-content response if data is unavailable.
     */
    @GetMapping("/cashFlow")
    public List<CashFlow> getCompanyCashFlow(@RequestParam(name = "symbol") String symbol) {
        return companyService.getCompanyCashFlow(symbol);
    }
}

