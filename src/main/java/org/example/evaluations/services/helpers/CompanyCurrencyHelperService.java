package org.example.evaluations.services.helpers;

import org.example.evaluations.dtos.RealTimeNewsData;
import org.example.evaluations.dtos.RealTimeNewsResult;
import org.example.evaluations.models.News;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Helper service that provides utility methods for converting news data models
 * into standardized DTO responses for clients.
 * <p>
 * Primarily used by controllers handling stock and currency-related news to
 * wrap raw {@link News} data into {@link RealTimeNewsResult} format.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>
 * List&lt;News&gt; newsList = ...;
 * ResponseEntity&lt;RealTimeNewsResult&gt; response = helperService.convertToRealTimeNewsResult(newsList);
 * </pre>
 *
 * @author Raushan Singh
 * @version 1.1
 */
@Service
public class CompanyCurrencyHelperService {

    /**
     * Converts a list of {@link News} items into a structured {@link RealTimeNewsResult}.
     * If the input list is null or empty, returns a {@link ResponseEntity#noContent()}.
     *
     * @param news the list of news articles to convert
     * @return a ResponseEntity containing a {@link RealTimeNewsResult} or 204 No Content if empty
     */
    public ResponseEntity<RealTimeNewsResult> convertToRealTimeNewsResult(List<News> news) {
        if (news == null || news.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        RealTimeNewsData newsData = new RealTimeNewsData();
        newsData.setNews(news);
        RealTimeNewsResult newsResult = new RealTimeNewsResult();
        newsResult.setData(newsData);
        return ResponseEntity.ok(newsResult);
    }
}
