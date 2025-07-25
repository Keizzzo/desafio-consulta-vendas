package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

public interface
ReportSalesProjection {
    Long getId();
    LocalDate getDate();
    Double getAmount();
    String getSellerName();
}
