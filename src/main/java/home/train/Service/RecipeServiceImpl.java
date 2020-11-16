package home.train.Service;

import home.train.commands.RecipeCommand;
import home.train.convertors.RecipeCommandToRecipe;
import home.train.convertors.RecipeToRecipeCommand;
import home.train.domain.Recipe;
import home.train.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeToRecipeCommand recipeToRecipeCommand;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeToRecipeCommand recipeToRecipeCommand,
                             RecipeCommandToRecipe recipeCommandToRecipe, RecipeRepository recipeRepository) {
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
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
    public RecipeCommand save(RecipeCommand command) {

        Recipe recipe= recipeCommandToRecipe.convert(command);
        Recipe savedRecipe=recipeRepository.save(recipe);
        log.info("saved recipe id {}",savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public Recipe update(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
}
