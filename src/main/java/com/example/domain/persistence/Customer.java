package com.example.domain.persistence;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.domain.View;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "username", "email", "address", "updatedAt"})
public class Customer extends BaseEntity {
    @NotBlank(message = "Username can't be blank")
    @Size(min = 3, max = 25, message = "Username length must be between 3 and 25 characters")
    @JsonView({View.Request.class, View.Summary.class})
    private String username;

    @NotBlank(message = "Email can't be blank")
    @Email(message = "Email has incorrect format")
    @JsonView({View.Request.class, View.Summary.class})
    private String email;

    @Valid
    @Embedded
    @NotNull(message = "Address can't be null")
    @JsonView({View.Request.class, View.Detailed.class})
    private Address address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<Purchase> purchases;

    public Customer(String username, String email, Address address) {
        this.username = username;
        this.email = email;
        this.address = address;
    }

    public void updateCustomerWithoutForeign(Customer customer) {
        customer.username = username;
        customer.email = email;
        customer.address = address;
    }
}
