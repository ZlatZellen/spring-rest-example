package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.domain.View;
import com.example.domain.persistence.Developer;
import com.example.domain.projection.summary.GameSummary;
import com.example.service.DeveloperService;
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
@RequestMapping("/api/developers")
@RequiredArgsConstructor
public class DeveloperController {
    private final DeveloperService service;

    @GetMapping
    @JsonView(View.Summary.class)
    public List<Developer> getAllDevelopers(Pageable pageable) {
        return service.findAllDevelopers(pageable);
    }

    @GetMapping("/{id}")
    @JsonView(View.Summary.class)
    public Developer getDeveloper(@PathVariable Integer id) {
        return service.findDeveloperById(id);
    }

    @GetMapping("/{id}/games")
    public List<GameSummary> getAllDeveloperGames(@PathVariable Integer id, Pageable pageable) {
        return service.findAllDeveloperGames(id, pageable);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(View.Summary.class)
    public Developer saveDeveloper(
            @Valid
            @RequestBody
            @JsonView(View.Request.class) Developer developer) {

        return service.save(developer);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDeveloper(
            @PathVariable Integer id,
            @Valid
            @RequestBody
            @JsonView(View.Request.class) Developer developer) {

        service.updateById(id, developer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDeveloper(@PathVariable Integer id) {
        service.deleteById(id);
    }
}
