package home.train.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Measure getMeasures() {
        return measures;
    }

    public void setMeasures(Measure measures) {
        this.measures = measures;
    }
}
