package com.example.repository;

import java.util.List;
import java.util.Optional;

import com.example.domain.persistence.Developer;
import com.example.domain.persistence.Game;
import com.example.domain.projection.summary.GameSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GameRepository extends BaseEntityRepository<Game> {
    @Modifying
    @Query(value = "DELETE FROM games WHERE id = :id", nativeQuery = true)
    void deleteById(Integer id);

    Page<GameSummary> findAllGameSummariesBy(Pageable pageable);

    @EntityGraph(attributePaths = "developer")
    Optional<Game> findGameWithDeveloperById(Integer id);

    List<GameSummary> findAllByDeveloper(Developer developer, Pageable pageable);
}
