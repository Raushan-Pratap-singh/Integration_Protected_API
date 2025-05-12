package org.example.evaluations.dtos;

import lombok.Data;
import org.example.evaluations.models.News;

import java.util.List;

@Data
public class RealTimeNewsData {
    private List<News> news;
}
