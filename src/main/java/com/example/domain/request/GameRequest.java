package com.example.domain.request;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.domain.persistence.Developer;
import com.example.domain.persistence.Game;
import com.example.validation.DateFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GameRequest {
    @NotBlank(message = "Title can't be blank")
    @Size(max = 100, message = "Title must be less than 100 characters")
    private final String title;

    @NotNull(message = "Price can't be null")
    @DecimalMin(value = "0.0", message = "Price can't be negative")
    @Digits(
            integer = 5,
            fraction = 2,
            message = "Price out of bands. Expected <5 digits>.<2 digits>")
    private final BigDecimal price;

    @DateFormat(pattern = "yyyy-MM-dd", message = "Release date must be correct yyyy-MM-dd")
    private final String releaseDate;

    private final Boolean online;

    @NotNull(message = "Developer id can't be null")
    private final Integer developerId;

    public Game toGame() {
        Developer developer = new Developer();
        developer.setId(developerId);
        return new Game(title, price, releaseDate, online, developer);
    }
}
