package home.train.Service;

import home.train.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId,Long IngredientId);

    IngredientCommand saveIngredient(IngredientCommand command);
}
