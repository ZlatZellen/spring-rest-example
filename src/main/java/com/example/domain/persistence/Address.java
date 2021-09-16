package com.example.domain.persistence;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.domain.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonView({View.Request.class, View.Summary.class})
public class Address {
    @NotBlank(message = "Country code can't be blank")
    @Size(min = 2, max = 2, message = "Country code must be 2 characters long")
    private String countryCode;

    @NotBlank(message = "City can't be blank")
    @Size(max = 100, message = "City must be less than 100 characters")
    private String city;

    @NotBlank(message = "Postcode can't be blank")
    @Size(min = 3, max = 10, message = "Postcode length must be between 3 and 10 characters")
    private String postcode;
}
