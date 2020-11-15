package home.train.Service;

import home.train.domain.Recipe;
import home.train.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> findAll() {
        Set<Recipe> recipes=new HashSet<>();
        recipeRepository.findAll().forEach(c-> recipes.add(c));
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipe=recipeRepository.findById(id);
        if(!recipe.isPresent()){
            throw new RuntimeException("Recipe Not Found");
        }
        return recipe.get();
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
