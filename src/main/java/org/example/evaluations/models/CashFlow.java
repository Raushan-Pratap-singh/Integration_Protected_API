package org.example.evaluations.models;

import lombok.Data;

/**
 * Represents the financial cash flow data of a company.
 *
 * <p>This model captures the cash flow information for a specific year and quarter of a company's
 * financial data, including the currency used and various financial metrics such as net income
 * and free cash flow.</p>
 *
 * @author Raushan Singh
 * @version 1.1
 */
@Data
public class CashFlow {

    /**
     * The year of the cash flow data.
     *
     * <p>This value indicates the fiscal year for the reported financial data.</p>
     */
    private Long year;

    /**
     * The quarter of the year for which the cash flow data is reported.
     *
     * <p>This value indicates the fiscal quarter (e.g., Q1, Q2, etc.) for the reported financial data.</p>
     */
    private Long quarter;

    /**
     * The currency in which the financial data is reported.
     *
     * <p>This value indicates the currency code (e.g., "USD", "EUR") used for the reported cash flow data.</p>
     */
    private String currency;

    /**
     * The net income of the company for the reported year and quarter.
     *
     * <p>This value represents the company's total earnings after expenses, taxes, and other costs.</p>
     */
    private Long net_income;

    /**
     * The free cash flow of the company for the reported year and quarter.
     *
     * <p>This value represents the cash that the company generates after accounting for capital expenditures.</p>
     */
    private Long free_cash_flow;
}
