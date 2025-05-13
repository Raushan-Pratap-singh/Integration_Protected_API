package org.example.evaluations.models;

import lombok.Data;

/**
 * Represents a news article related to stock or currency data.
 *
 * <p>This model captures the details of a news article, including the title, URL, source, and
 * the time the article was posted in UTC format.</p>
 *
 * @author Raushan Singh
 * @version 1.1
 */
@Data
public class News {

    /**
     * The title of the news article.
     *
     * <p>This value represents the headline or title of the news article.</p>
     */
    private String article_title;

    /**
     * The URL of the news article.
     *
     * <p>This value contains the link to the full news article.</p>
     */
    private String article_url;

    /**
     * The source from which the news article originated.
     *
     * <p>This value represents the publication or organization that published the article (e.g., "Reuters", "Bloomberg").</p>
     */
    private String source;

    /**
     * The UTC timestamp when the article was posted.
     *
     * <p>This value represents the time the article was published, in Coordinated Universal Time (UTC) format.</p>
     */
    private String post_time_utc;
}
