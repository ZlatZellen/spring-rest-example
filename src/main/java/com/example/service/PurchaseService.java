package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.domain.persistence.Customer;
import com.example.domain.persistence.Game;
import com.example.domain.persistence.Purchase;
import com.example.domain.projection.statistics.PurchaseCustomerStatistics;
import com.example.domain.projection.statistics.PurchaseGameStatistics;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final GameService gameService;
    private final CustomerService customerService;
    private final PurchaseRepository repository;

    public List<PurchaseCustomerStatistics> findAllPurchaseCustomerStatistics(
            Integer id,
            Pageable pageable) {

        return repository
                .findAllGamesByCustomer(customerService.getOne(id), pageable)
                .getContent();
    }

    public List<PurchaseGameStatistics> findAllPurchaseGameStatistics(Pageable pageable) {
        return repository
                .findAllPurchaseStatistics(pageable)
                .getContent();
    }

    @Transactional
    public Purchase save(Purchase purchase) {
        Game game = gameService.findGameById(purchase.getGame().getId());
        Customer customer = customerService.findCustomerById(purchase.getCustomer().getId());

        purchase.setId(null);
        purchase.setGame(game);
        purchase.setCustomer(customer);

        return repository.save(purchase);
    }

    @Transactional
    public void updateById(Integer id, Purchase purchase) {
        Optional.ofNullable(id)
                .flatMap(repository::findById)
                .map(p -> {
                    Integer newGameId = purchase.getGame().getId();
                    if (!p.getGame().getId().equals(newGameId)) {
                        Game game = gameService.findGameById(newGameId);
                        p.setGame(game);
                    }

                    Integer newCustomerId = purchase.getCustomer().getId();
                    if (!p.getCustomer().getId().equals(newCustomerId)) {
                        Customer customer = customerService.findCustomerById(newCustomerId);
                        p.setCustomer(customer);
                    }

                    purchase.updatePurchaseWithoutForeign(p);

                    return repository.save(p);
                })
                .orElseThrow(() -> new ResourceNotFoundException("purchase", id));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
