package com.example.repository;

import com.example.domain.persistence.Customer;
import com.example.domain.persistence.Purchase;
import com.example.domain.projection.statistics.PurchaseCustomerStatistics;
import com.example.domain.projection.statistics.PurchaseGameStatistics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PurchaseRepository extends BaseEntityRepository<Purchase> {
    @Modifying
    @Query(value = "DELETE FROM purchases WHERE id = :id", nativeQuery = true)
    void deleteById(Integer id);

    @Query("SELECT g.id AS id, g.title AS title, SUM(p.amount) AS totalAmount "
            + "FROM Purchase p LEFT OUTER JOIN p.game g "
            + "WHERE p.customer = :customer "
            + "GROUP BY p.game")
    Page<PurchaseCustomerStatistics> findAllGamesByCustomer(Customer customer, Pageable pageable);

    @Query("SELECT g.id AS id, g.title AS title, g.price AS price, SUM(p.amount) AS totalAmount "
            + "FROM Purchase p LEFT OUTER JOIN p.game g "
            + "GROUP BY p.game")
    Page<PurchaseGameStatistics> findAllPurchaseStatistics(Pageable pageable);
}
