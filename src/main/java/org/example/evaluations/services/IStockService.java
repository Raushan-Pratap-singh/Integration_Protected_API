package org.example.evaluations.services;

import org.example.evaluations.models.CashFlow;
import org.example.evaluations.models.News;

import java.util.List;

/**
 * Interface defining the contract for services that interact with stock and financial data.
 *
 * <p>Implementing classes are expected to retrieve stock-related news and company financial
 * information based on a stock symbol.</p>
 *
 * @author Raushan Singh
 * @version 1.1
 */
public interface IStockService {

    /**
     * Retrieves stock news articles related to the specified stock symbol.
     *
     * @param symbol the stock symbol (e.g., "AAPL" for Apple)
     * @return a list of {@link News} objects related to the stock symbol,
     *         or null/empty if no data is available
     */
    List<News> getStockNews(String symbol);

    /**
     * Retrieves the cash flow data of a company based on the specified stock symbol.
     *
     * @param symbol the stock symbol of the company (e.g., "AAPL" for Apple)
     * @return a list of {@link CashFlow} objects representing the company's financial data,
     *         or null/empty if no data is available
     */
    List<CashFlow> getCompanyCashFlow(String symbol);
}
