package org.example.evaluations.dtos;

import lombok.Data;
import org.example.evaluations.models.CashFlow;

import java.util.List;

@Data
public class RealTimeCashFlowData {
    private List<CashFlow> cash_flow;
}
