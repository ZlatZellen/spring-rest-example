package com.example.domain.projection.statistics;

import com.example.domain.projection.IdentifiableProjection;

public interface PurchaseCustomerStatistics extends IdentifiableProjection {
    String getTitle();

    Integer getTotalAmount();
}
