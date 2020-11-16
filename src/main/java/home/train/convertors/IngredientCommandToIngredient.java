package home.train.convertors;

import home.train.commands.IngredientCommand;
import home.train.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final MeasureCommandToMeasure measureConverter;

    public IngredientCommandToIngredient(MeasureCommandToMeasure measureConverter) {
        this.measureConverter = measureConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand ingredientCommand) {

        if(ingredientCommand==null) return null;

        final Ingredient ingredient= new Ingredient();
        ingredient.setId(ingredientCommand.getId());
        ingredient.setAmount(ingredientCommand.getAmount());
        ingredient.setMeasures(measureConverter.convert(ingredientCommand.getMeasure()));
        ingredient.setDescription(ingredientCommand.getDescription());
        return ingredient;
    }
}
