package com.devsuperior.dsmeta.dto;

import java.util.Locale;

public class SellerSalesSummaryDTO {

    private String name;
    private String total;

    public SellerSalesSummaryDTO(SellerSalesSummary summary) {
        this.name = summary.getName();
        this.total = String.format(Locale.US, "%.1f", summary.getTotal());
    }

    public String getName() {
        return name;
    }

    public String getTotal() {
        return total;
    }
}
