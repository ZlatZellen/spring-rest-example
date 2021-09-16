package com.example.repository;

import java.util.Optional;

import com.example.domain.persistence.Developer;
import org.springframework.data.jpa.repository.EntityGraph;

public interface DeveloperRepository extends BaseEntityRepository<Developer> {
    @EntityGraph(attributePaths = "games")
    Optional<Developer> findDeveloperWithGamesById(Integer id);
}
