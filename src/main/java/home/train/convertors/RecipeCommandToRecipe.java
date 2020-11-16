package home.train.convertors;

import home.train.commands.RecipeCommand;
import home.train.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final NoteCommandToNote NoteConverter;
    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;


    public RecipeCommandToRecipe(NoteCommandToNote noteConverter, CategoryCommandToCategory categoryConverter,
                                 IngredientCommandToIngredient ingredientConverter) {
        NoteConverter = noteConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {

        if(recipeCommand== null) return null;

        final Recipe recipe= new Recipe();

        recipe.setId(recipeCommand.getId());
        recipe.setServings(recipeCommand.getServings());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setDirection(recipeCommand.getDirection());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setSource(recipeCommand.getSource());
        recipe.setNote(NoteConverter.convert(recipeCommand.getNote()));

        if(recipeCommand.getCategories()!=null && recipeCommand.getCategories().size()>0){
            recipeCommand.getCategories()
                    .forEach(categoryCommand -> recipe.getCategories().add(categoryConverter.convert(categoryCommand)));
        }

        if(recipeCommand.getIngredients()!=null && recipeCommand.getIngredients().size()>0){
            recipeCommand.getIngredients()
                    .forEach(ingredientCommand -> recipe.getIngredients().add(ingredientConverter.convert(ingredientCommand)));
        }

        return recipe;

    }
}
