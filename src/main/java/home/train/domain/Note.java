package home.train.domain;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Recipe recipe;
    @Lob
    private String recipeNotes;


    public void setRecipe(Recipe recipe) {

        this.recipe = recipe;
        recipe.setNote(this);
    }
}
