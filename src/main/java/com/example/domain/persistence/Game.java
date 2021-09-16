package com.example.domain.persistence;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.domain.View;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "games")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonView(View.Summary.class)
@JsonPropertyOrder({"id", "title", "price", "releaseDate", "online", "developer"})
public class Game extends BaseEntity {
    private String title;

    private BigDecimal price;

    @JsonView(View.Detailed.class)
    private String releaseDate;

    @JsonView(View.Detailed.class)
    private Boolean online;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "developer_id")
    @JsonView(View.Detailed.class)
    private Developer developer;

    public void updateGameWithoutForeign(Game game) {
        game.title = title;
        game.price = price;
        game.releaseDate = releaseDate;
        game.online = online;
    }
}
