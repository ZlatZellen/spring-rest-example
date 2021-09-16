package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.domain.View;
import com.example.domain.persistence.Purchase;
import com.example.domain.projection.statistics.PurchaseCustomerStatistics;
import com.example.domain.projection.statistics.PurchaseGameStatistics;
import com.example.domain.request.PurchaseRequest;
import com.example.service.PurchaseService;
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
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService service;

    @GetMapping("/games/statistics")
    public List<PurchaseGameStatistics> getAllPurchaseGameStatistics(Pageable pageable) {
        return service.findAllPurchaseGameStatistics(pageable);
    }

    @GetMapping("/customers/{id}")
    public List<PurchaseCustomerStatistics> getAllPurchaseCustomerStatistics(
            @PathVariable Integer id,
            Pageable pageable) {

        return service.findAllPurchaseCustomerStatistics(id, pageable);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(View.Summary.class)
    public Purchase savePurchase(@Valid @RequestBody PurchaseRequest purchaseRequest) {
        return service.save(purchaseRequest.toPurchase());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePurchase(
            @PathVariable Integer id,
            @Valid
            @RequestBody PurchaseRequest purchaseRequest) {

        service.updateById(id, purchaseRequest.toPurchase());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePurchase(@PathVariable Integer id) {
        service.deleteById(id);
    }
}
