package com.devsuperior.dsmeta.dto;

import java.math.BigDecimal;

// This interface helps to personalize the values returned in query.
// In the case, maps the value of seller.NAME, ROUND(SUM(sales.AMOUNT), 1) as total.
public interface SellerSalesSummary {
    String getName();
    BigDecimal getTotal();
}
