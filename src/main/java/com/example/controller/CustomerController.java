package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.domain.View;
import com.example.domain.persistence.Customer;
import com.example.domain.projection.summary.CustomerSummary;
import com.example.service.CustomerService;
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
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;

    @GetMapping
    public List<CustomerSummary> getAllCustomers(Pageable pageable) {
        return service.findAllCustomerSummaries(pageable);
    }

    @GetMapping("/{id}")
    @JsonView(View.Detailed.class)
    public Customer getCustomer(@PathVariable Integer id) {
        return service.findCustomerById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(View.Detailed.class)
    public Customer saveCustomer(
            @Valid
            @RequestBody
            @JsonView(View.Request.class) Customer customer) {

        return service.save(customer);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(
            @PathVariable Integer id,
            @Valid
            @RequestBody
            @JsonView(View.Request.class) Customer customer) {

        service.updateById(id, customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Integer id) {
        service.deleteById(id);
    }
}
