package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.domain.persistence.Customer;
import com.example.domain.projection.summary.CustomerSummary;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;

    public Customer getOne(Integer id) {
        return repository.getOne(id);
    }

    public List<CustomerSummary> findAllCustomerSummaries(Pageable pageable) {
        return repository
                .findAllCustomerSummariesBy(pageable)
                .getContent();
    }

    public Customer findCustomerById(Integer id) {
        return Optional.ofNullable(id)
                .flatMap(repository::findById)
                .orElseThrow(() -> new ResourceNotFoundException("customer", id));
    }

    public Customer save(Customer customer) {
        customer.setId(null);
        return repository.save(customer);
    }

    @Transactional
    public void updateById(Integer id, Customer customer) {
        Optional.ofNullable(id)
                .flatMap(repository::findById)
                .map(c -> {
                    customer.updateCustomerWithoutForeign(c);
                    return repository.save(c);
                })
                .orElseThrow(() -> new ResourceNotFoundException("customer", id));
    }

    @Transactional
    public void deleteById(Integer id) {
        Optional.ofNullable(id)
                .flatMap(repository::findCustomerWithPurchasesById)
                .ifPresent(repository::delete);
    }
}
