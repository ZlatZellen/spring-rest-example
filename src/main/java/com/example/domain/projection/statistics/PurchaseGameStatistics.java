package com.example.domain.projection.statistics;

import java.math.BigDecimal;

import com.example.domain.projection.IdentifiableProjection;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.beans.factory.annotation.Value;

@JsonPropertyOrder({"id", "title", "price", "totalAmount", "totalGain"})
public interface PurchaseGameStatistics extends IdentifiableProjection {
    String getTitle();

    BigDecimal getPrice();

    Integer getTotalAmount();

    @Value("#{target.price * target.totalAmount}")
    BigDecimal getTotalGain();
}
