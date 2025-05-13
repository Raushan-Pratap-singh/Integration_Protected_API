package org.example.evaluations.dtos;

import lombok.Data;

/**
 * DTO that represents the result of a real-time news query.
 *
 * <p>This data transfer object encapsulates the result of a query for real-time news, which includes
 * the actual news data stored in a {@link RealTimeNewsData} object.</p>
 *
 * @author Raushan Singh
 * @version 1.1
 */
@Data
public class RealTimeNewsResult {

    /**
     * The real-time news data associated with the result.
     *
     * <p>This field holds the news data, represented by a {@link RealTimeNewsData} object, which
     * contains the list of news articles.</p>
     */
    private RealTimeNewsData data;
}
