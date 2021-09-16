package com.example.domain.persistence;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.example.domain.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Summary.class)
    private Integer id;

    @JsonView(View.Summary.class)
    private LocalDateTime updatedAt;

    @PreUpdate
    @PrePersist
    public void initUpdatedAt() {
        updatedAt = LocalDateTime.now();
    }
}
