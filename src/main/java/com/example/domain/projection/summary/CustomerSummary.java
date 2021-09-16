package com.example.domain.projection.summary;

import com.example.domain.projection.EntityProjection;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "username", "email", "updatedAt"})
public interface CustomerSummary extends EntityProjection {
    String getUsername();

    String getEmail();
}
