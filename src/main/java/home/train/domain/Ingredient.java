package home.train.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
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

    public Ingredient(String description, BigDecimal amount, Measure measures,Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.measures = measures;
        this.recipe=recipe;
    }

    public Ingredient(String description, BigDecimal amount, Measure measures) {
        this.description = description;
        this.amount = amount;
        this.measures = measures;
    }


}
