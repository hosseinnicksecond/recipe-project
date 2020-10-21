package home.train.Service;

import home.train.domain.Recipe;
import home.train.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> findAll() {
        List<Recipe> recipes=new ArrayList<>();
        recipeRepository.findAll().forEach(c-> recipes.add(c));
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).get();
    }

    @Override
    public void deleteRecipeById(Long id) {
         recipeRepository.deleteById(id);
    }

    @Override
    public void deleteByName(Recipe recipe) {
          recipeRepository.delete(recipe);
    }

    @Override
    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe update(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
}
