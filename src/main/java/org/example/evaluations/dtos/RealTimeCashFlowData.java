package org.example.evaluations.dtos;

import lombok.Data;
import org.example.evaluations.models.CashFlow;

import java.util.List;

/**
 * DTO that represents the real-time cash flow data for a company.
 *
 * <p>This data transfer object encapsulates the cash flow information for a company over a period of time.</p>
 *
 * @author Raushan Singh
 * @version 1.1
 */
@Data
public class RealTimeCashFlowData {

    /**
     * A list of cash flow records for the company.
     *
     * <p>This list contains multiple cash flow objects, each representing the company's financial data for a specific
     * year and quarter.</p>
     */
    private List<CashFlow> cash_flow;
}
