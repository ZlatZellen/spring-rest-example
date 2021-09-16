package com.example.domain.projection.summary;

import java.math.BigDecimal;

import com.example.domain.projection.EntityProjection;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "title", "price", "updatedAt"})
public interface GameSummary extends EntityProjection {
    String getTitle();

    BigDecimal getPrice();
}
