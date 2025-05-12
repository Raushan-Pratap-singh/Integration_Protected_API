package org.example.evaluations.controllers;

import java.util.List;

import org.example.evaluations.dtos.RealTimeCashFlowData;
import org.example.evaluations.dtos.RealTimeCashFlowResult;
import org.example.evaluations.dtos.RealTimeNewsResult;
import org.example.evaluations.models.CashFlow;
import org.example.evaluations.models.News;
import org.example.evaluations.services.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.example.evaluations.controllers.CurrencyController.getRealTimeNewsResultResponseEntity;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private IStockService companyService;

    @GetMapping("/stockNews")
    public ResponseEntity<RealTimeNewsResult> getStockNews(@RequestParam(name = "symbol") String symbol) {
        List<News> news = companyService.getStockNews(symbol);
        return getRealTimeNewsResultResponseEntity(news);
    }

    @GetMapping("/cashFlow")
    public ResponseEntity<RealTimeCashFlowResult> getCompanyCashFlow(@RequestParam(name = "symbol") String symbol) {
        List<CashFlow> cashFlows = companyService.getCompanyCashFlow(symbol);
        if(cashFlows == null || cashFlows.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        RealTimeCashFlowData cashFlowData = new RealTimeCashFlowData();
        cashFlowData.setCash_flow(cashFlows);
        RealTimeCashFlowResult cashFlowResult = new RealTimeCashFlowResult();
        cashFlowResult.setData(cashFlowData);
        return ResponseEntity.ok(cashFlowResult);
    }
}

