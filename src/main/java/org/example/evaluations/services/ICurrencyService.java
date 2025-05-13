package org.example.evaluations.services;

import org.example.evaluations.models.News;

import java.util.List;

/**
 * Interface defining the contract for services that fetch news related to currency conversion.
 *
 * <p>Implementing classes are expected to interact with external data sources
 * to retrieve news articles based on currency pairs.</p>
 *
 * @author Raushan Singh
 * @version 1.1
 */
public interface ICurrencyService {

    /**
     * Retrieves news articles related to currency conversion between two specified currencies.
     *
     * @param fromCurrency the source currency symbol (e.g., "USD")
     * @param toCurrency   the target currency symbol (e.g., "EUR")
     * @return a list of {@link News} objects relevant to the currency pair,
     *         or null/empty if no data is available
     */
    List<News> getCurrencyNews(String fromCurrency, String toCurrency);
}
