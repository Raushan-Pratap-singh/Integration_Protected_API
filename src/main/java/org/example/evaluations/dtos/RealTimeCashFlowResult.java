package org.example.evaluations.dtos;

import lombok.Data;
import org.example.evaluations.models.CashFlow;

import java.util.List;

/**
 * DTO that represents the result of fetching real-time cash flow data for a company.
 *
 * <p>This data transfer object encapsulates the cash flow data in a structure that can be returned in API responses.
 * It contains a {@link RealTimeCashFlowData} object that holds the list of cash flow records for a company.</p>
 *
 * @author Raushan Singh
 * @version 1.1
 */
@Data
public class RealTimeCashFlowResult {

    /**
     * The encapsulated cash flow data for the company.
     *
     * <p>This object holds the cash flow records for a specific company, including details such as net income and
     * free cash flow for each year and quarter.</p>
     */
    private RealTimeCashFlowData data;

    /**
     * Static factory method to create a {@link RealTimeCashFlowResult} from a list of cash flow records.
     *
     * <p>This method constructs the {@link RealTimeCashFlowResult} object and populates the {@link RealTimeCashFlowData}
     * with the provided list of cash flows.</p>
     *
     * @param cashFlows the list of cash flow records to populate the result
     * @return a {@link RealTimeCashFlowResult} containing the cash flow data
     */
    public static RealTimeCashFlowResult of(List<CashFlow> cashFlows) {
        RealTimeCashFlowData data = new RealTimeCashFlowData();
        data.setCash_flow(cashFlows);
        RealTimeCashFlowResult result = new RealTimeCashFlowResult();
        result.setData(data);
        return result;
    }
}