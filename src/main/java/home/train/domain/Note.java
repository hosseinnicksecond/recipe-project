package home.train.domain;

import lombok.*;

import javax.persistence.*;


@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Recipe recipe;
    @Lob
    private String recipeNotes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }

    public void setRecipe(Recipe recipe) {

        this.recipe = recipe;
        recipe.setNote(this);
    }

    @Override
    public String toString() {
        return recipeNotes ;
    }
}
