package com.example.domain.request;

import javax.validation.constraints.NotNull;

import com.example.domain.persistence.Customer;
import com.example.domain.persistence.Game;
import com.example.domain.persistence.Purchase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
@AllArgsConstructor
public class PurchaseRequest {
    @NotNull(message = "Customer id can't be null")
    private final Integer customerId;

    @NotNull(message = "Game id can't be null")
    private final Integer gameId;

    @NotNull(message = "Amount can't be null")
    @Range(min = 1, max = 10, message = "Amount must be between 1 and 10")
    private final Integer amount;

    public Purchase toPurchase() {
        Customer customer = new Customer();
        customer.setId(customerId);

        Game game = new Game();
        game.setId(gameId);

        return new Purchase(game, customer, amount);
    }
}
