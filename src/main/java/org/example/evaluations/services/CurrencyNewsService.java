package org.example.evaluations.services;

import org.example.evaluations.dtos.RealTimeNewsData;
import org.example.evaluations.dtos.RealTimeNewsResult;
import org.example.evaluations.models.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CurrencyNewsService implements ICurrencyService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String COMPANY_CASHFLOW_URL = "https://real-time-finance-data.p.rapidapi.com/currency-news";
    private static final String X_RapidAPI_Key = "062c40283bmshf542cb9122404e1p1a18e2jsn19c9052df889";
    private static final String X_RapidAPI_HOST = "real-time-finance-data.p.rapidapi.com";

    public List<News> getCurrencyNews(String fromCurrency,String toCurrency) {
        HttpEntity<String> httpEntity = createHttpEntity();
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(COMPANY_CASHFLOW_URL)
                .append("?")
                .append("from_symbol=")
                .append(fromCurrency)
                .append("&to_symbol=")
                .append(toCurrency)
                .append("&language=en");
        ResponseEntity<RealTimeNewsResult> response = restTemplate.exchange(urlBuilder.toString(), HttpMethod.GET, httpEntity, RealTimeNewsResult.class);
        if(response.getStatusCode().is2xxSuccessful()) {
            RealTimeNewsResult result = response.getBody();
            if(result != null) {
                return result.getData().getNews();
            }
        }
        return null;
    }

    private HttpEntity<String> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", X_RapidAPI_Key);
        return new HttpEntity<String>(headers);
    }
}
