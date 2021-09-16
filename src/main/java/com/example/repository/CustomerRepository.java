package com.example.repository;

import java.util.Optional;

import com.example.domain.persistence.Customer;
import com.example.domain.projection.summary.CustomerSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

public interface CustomerRepository extends BaseEntityRepository<Customer> {
    Page<CustomerSummary> findAllCustomerSummariesBy(Pageable pageable);

    @EntityGraph(attributePaths = "purchases")
    Optional<Customer> findCustomerWithPurchasesById(Integer id);
}
