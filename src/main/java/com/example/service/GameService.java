package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.domain.persistence.Developer;
import com.example.domain.persistence.Game;
import com.example.domain.projection.summary.GameSummary;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GameService {
    private final DeveloperService developerService;
    private final GameRepository repository;

    public List<GameSummary> findAllGameSummaries(Pageable pageable) {
        return repository
                .findAllGameSummariesBy(pageable)
                .getContent();
    }

    public Game findGameById(Integer id) {
        return Optional.ofNullable(id)
                .flatMap(repository::findById)
                .orElseThrow(() -> new ResourceNotFoundException("game", id));
    }

    public Game findGameWithDeveloperById(Integer id) {
        return Optional.ofNullable(id)
                .flatMap(repository::findGameWithDeveloperById)
                .orElseThrow(() -> new ResourceNotFoundException("game", id));
    }

    @Transactional
    public Game save(Game game) {
        Developer developer = developerService.findDeveloperById(game.getDeveloper().getId());

        game.setId(null);
        game.setDeveloper(developer);

        return repository.save(game);
    }

    @Transactional
    public void updateById(Integer id, Game game) {
        Optional.ofNullable(id)
                .flatMap(repository::findById)
                .map(g -> {
                    Integer newDeveloperId = game.getDeveloper().getId();
                    if (!g.getDeveloper().getId().equals(newDeveloperId)) {
                        Developer developer = developerService.findDeveloperById(newDeveloperId);
                        g.setDeveloper(developer);
                    }

                    game.updateGameWithoutForeign(g);

                    return repository.save(g);
                })
                .orElseThrow(() -> new ResourceNotFoundException("game", id));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
