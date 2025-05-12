package org.example.evaluations.controllers;

import java.util.List;

import org.example.evaluations.dtos.RealTimeNewsData;
import org.example.evaluations.dtos.RealTimeNewsResult;
import org.example.evaluations.models.News;
import org.example.evaluations.services.ICurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private ICurrencyService currencyService;


    @GetMapping("/conversionNews")
    public ResponseEntity<RealTimeNewsResult> getCurrencyNews(@RequestParam(name = "from_symbol") String from_symbol, @RequestParam(name = "to_symbol") String to_symbol) {
        List<News> news = currencyService.getCurrencyNews(from_symbol, to_symbol);
        return getRealTimeNewsResultResponseEntity(news);
    }

    static ResponseEntity<RealTimeNewsResult> getRealTimeNewsResultResponseEntity(List<News> news) {
        if(news == null || news.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        RealTimeNewsData newsData = new RealTimeNewsData();
        newsData.setNews(news);
        RealTimeNewsResult newsResult = new RealTimeNewsResult();
        newsResult.setData(newsData);
        return ResponseEntity.ok(newsResult);
    }


}