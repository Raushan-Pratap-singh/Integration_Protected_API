package org.example.evaluations.dtos;

import lombok.Data;
import org.example.evaluations.models.News;

import java.util.List;

/**
 * DTO that represents the real-time news data for a given entity (e.g., stock or currency).
 *
 * <p>This data transfer object encapsulates a list of news articles related to a specific subject,
 * such as stock or currency. The news articles are represented by a list of {@link News} objects.</p>
 *
 * @author Raushan Singh
 * @version 1.1
 */
@Data
public class RealTimeNewsData {

    /**
     * A list of news articles related to a specific entity.
     *
     * <p>This list contains the news articles, each represented by a {@link News} object. The articles
     * provide information such as article title, URL, source, and time of publication.</p>
     */
    private List<News> news;
}
