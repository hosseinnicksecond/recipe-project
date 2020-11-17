package home.train.Service;

import home.train.commands.IngredientCommand;
import home.train.convertors.IngredientToIngredientCommand;
import home.train.domain.Recipe;
import home.train.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientConverter;

    public IngredientServiceImpl(RecipeRepository recipeRepository,
                                 IngredientToIngredientCommand ingredientConverter) {
        this.recipeRepository=recipeRepository;
        this.ingredientConverter = ingredientConverter;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long IngredientId) {
        Optional<Recipe> recipeOptional=recipeRepository.findById(recipeId);

        if(recipeOptional.isEmpty()){
            log.info("recipe by {} id not exist ",recipeId);
        }

        Recipe recipe=recipeOptional.get();

        Optional<IngredientCommand> ingredientCommand= recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(IngredientId))
                .map(ingredientConverter::convert).findFirst();

        if(ingredientCommand.isEmpty()){
            log.info("ingredient by {} id not exist",IngredientId);
        }

        return ingredientCommand.get();
    }
}
