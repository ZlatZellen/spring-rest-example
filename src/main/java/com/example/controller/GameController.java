package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.domain.View;
import com.example.domain.persistence.Game;
import com.example.domain.projection.summary.GameSummary;
import com.example.domain.request.GameRequest;
import com.example.service.GameService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {
    private final GameService service;

    @GetMapping
    public List<GameSummary> getAllGames(Pageable pageable) {
        return service.findAllGameSummaries(pageable);
    }

    @GetMapping("/{id}")
    @JsonView(View.Detailed.class)
    public Game getGame(@PathVariable Integer id) {
        return service.findGameWithDeveloperById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(View.Detailed.class)
    public Game saveGame(@Valid @RequestBody GameRequest gameRequest) {
        return service.save(gameRequest.toGame());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(
            @PathVariable Integer id,
            @Valid
            @RequestBody GameRequest gameRequest) {

        service.updateById(id, gameRequest.toGame());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable Integer id) {
        service.deleteById(id);
    }
}
