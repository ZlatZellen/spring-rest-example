package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.domain.persistence.Developer;
import com.example.domain.projection.summary.GameSummary;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.DeveloperRepository;
import com.example.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeveloperService {
    private final DeveloperRepository developerRepository;
    private final GameRepository gameRepository;

    public List<Developer> findAllDevelopers(Pageable pageable) {
        return developerRepository
                .findAll(pageable)
                .getContent();
    }

    public Developer findDeveloperById(Integer id) {
        return Optional.ofNullable(id)
                .flatMap(developerRepository::findById)
                .orElseThrow(() -> new ResourceNotFoundException("developer", id));
    }

    public List<GameSummary> findAllDeveloperGames(Integer id, Pageable pageable) {
        return gameRepository.findAllByDeveloper(developerRepository.getOne(id), pageable);
    }

    public Developer save(Developer developer) {
        developer.setId(null);
        return developerRepository.save(developer);
    }

    @Transactional
    public void updateById(Integer id, Developer developer) {
        Optional.ofNullable(id)
                .flatMap(developerRepository::findById)
                .map(d -> {
                    developer.updateDeveloper(d);
                    return developerRepository.save(d);
                })
                .orElseThrow(() -> new ResourceNotFoundException("developer", id));
    }

    @Transactional
    public void deleteById(Integer id) {
        Optional.ofNullable(id)
                .flatMap(developerRepository::findDeveloperWithGamesById)
                .ifPresent(developerRepository::delete);
    }
}
