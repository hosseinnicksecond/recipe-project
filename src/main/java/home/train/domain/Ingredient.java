package home.train.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;
    @ManyToOne
    private Recipe recipe;
    @OneToOne
    private Measure measures;

    public Ingredient(String description, BigDecimal amount , Measure measures, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.recipe = recipe;
        this.measures = measures;
    }

    public Ingredient(String description, BigDecimal amount, Measure measures) {
        this.description = description;
        this.amount = amount;
        this.measures = measures;
    }


}
