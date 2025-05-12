package org.example.evaluations.services;

import org.example.evaluations.dtos.RealTimeCashFlowResult;
import org.example.evaluations.dtos.RealTimeNewsResult;
import org.example.evaluations.models.CashFlow;
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
public class CompanyStockService implements IStockService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String STOCK_NEWS_URL = "https://real-time-finance-data.p.rapidapi.com/stock-news?symbol=";
    private static final String COMPANY_CASHFLOW_URL = "https://real-time-finance-data.p.rapidapi.com/company-cash-flow?symbol=";
    private static final String X_RapidAPI_Key = "062c40283bmshf542cb9122404e1p1a18e2jsn19c9052df889";
    private static final String X_RapidAPI_HOST = "real-time-finance-data.p.rapidapi.com";

    public List<News> getStockNews(String symbol) {
        HttpEntity<String> httpEntity = createHttpEntity();
        ResponseEntity<RealTimeNewsResult> response = restTemplate.exchange(STOCK_NEWS_URL + symbol, HttpMethod.GET, httpEntity, RealTimeNewsResult.class);
        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().getData().getNews();
        }
        return null;
    }

    public List<CashFlow> getCompanyCashFlow(String symbol) {
        HttpEntity<String> httpEntity = createHttpEntity();
        ResponseEntity<RealTimeCashFlowResult> response = restTemplate.exchange(COMPANY_CASHFLOW_URL + symbol, HttpMethod.GET, httpEntity, RealTimeCashFlowResult.class);
        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().getData().getCash_flow();
        }
        return null;
    }

    private HttpEntity<String> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", X_RapidAPI_Key);
        headers.set("x-rapidapi-host", X_RapidAPI_HOST);
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
        return httpEntity;
    }
}
