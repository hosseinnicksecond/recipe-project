package home.train.Service;

import home.train.domain.Recipe;

import java.util.List;

public interface RecipeService {

    List<Recipe> findAll();
    Recipe findById(Long id);
    void deleteRecipeById(Long id);
    void deleteByName(Recipe recipe);
    Recipe save(Recipe recipe);
    Recipe update(Recipe recipe);
}