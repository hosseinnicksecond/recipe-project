package home.train.convertors;

import home.train.commands.IngredientCommand;
import home.train.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final MeasureToMeasureCommand measureConverter;


    public IngredientToIngredientCommand(MeasureToMeasureCommand measureConverter) {
        this.measureConverter = measureConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {

        if(ingredient== null) return null;

        final IngredientCommand ingredientCommand= new IngredientCommand();
        ingredientCommand.setId(ingredient.getId());
        if(ingredient.getRecipe()!=null) {
            ingredientCommand.setRecipeId(ingredient.getRecipe().getId());
        }
        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setMeasure(measureConverter.convert(ingredient.getMeasures()));

        return ingredientCommand;
    }
}
