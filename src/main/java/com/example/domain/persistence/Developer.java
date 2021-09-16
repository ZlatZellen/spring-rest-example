package com.example.domain.persistence;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.domain.View;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "developers")
@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "about", "updatedAt"})
public class Developer extends BaseEntity {
    @NotBlank(message = "Name can't be empty")
    @Size(max = 50, message = "Name must be less than 50 characters")
    @JsonView({View.Request.class, View.Summary.class})
    private String name;

    @Size(max = 255, message = "About must be less than 50 characters")
    @JsonView({View.Request.class, View.Summary.class})
    private String about;

    @OneToMany(mappedBy = "developer", cascade = CascadeType.REMOVE)
    private List<Game> games;

    public Developer(String name, String about) {
        this.name = name;
        this.about = about;
    }

    public void updateDeveloper(Developer developer) {
        developer.name = name;
        developer.about = about;
    }
}
