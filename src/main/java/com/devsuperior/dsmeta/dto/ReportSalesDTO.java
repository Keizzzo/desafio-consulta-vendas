package com.devsuperior.dsmeta.dto;

import java.math.BigDecimal;

public class ReportSalesDTO {

    private Long id;
    private String date;
    private Double amount;
    private String sellerName;

    public ReportSalesDTO() {
    }

    public ReportSalesDTO(ReportSalesProjection  projection) {
        id = projection.getId();
        date = projection.getDate().toString();
        amount = projection.getAmount();
        sellerName = projection.getSellerName();
    } // This constructor helps to personalize the values returned in query (ReportSalesProjection

    public ReportSalesDTO(Long id, String date, Double amount, String sellerName) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.sellerName = sellerName;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getSellerName() {
        return sellerName;
    }
}
