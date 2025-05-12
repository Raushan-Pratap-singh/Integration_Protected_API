package org.example.evaluations.services;

import org.example.evaluations.models.CashFlow;
import org.example.evaluations.models.News;

import java.util.List;

public interface IStockService {
    List<News> getStockNews(String symbol);
    List<CashFlow> getCompanyCashFlow(String symbol);
}
