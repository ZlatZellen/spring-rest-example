package com.example.domain.projection;

import java.time.LocalDateTime;

public interface UpdatableProjection {
    LocalDateTime getUpdatedAt();
}
