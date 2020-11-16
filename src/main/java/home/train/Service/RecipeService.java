package home.train.Service;

import home.train.commands.RecipeCommand;
import home.train.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> findAll();
    Recipe findById(Long id);
    void deleteRecipeById(Long id);
    void deleteByName(Recipe recipe);
    RecipeCommand save(RecipeCommand recipe);
    Recipe update(Recipe recipe);
}
