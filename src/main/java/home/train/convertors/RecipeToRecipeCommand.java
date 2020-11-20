package home.train.convertors;


import home.train.commands.RecipeCommand;
import home.train.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final CategoryToCategoryCommand categoryConverter;
    private final NoteToNoteCommand noteConverter;
    private final IngredientToIngredientCommand ingredientConverter;


    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConverter, NoteToNoteCommand noteConverter, IngredientToIngredientCommand ingredientConverter) {
        this.categoryConverter = categoryConverter;
        this.noteConverter = noteConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {

        if(recipe==null) return null;

        final RecipeCommand recipeCommand = new RecipeCommand();

        recipeCommand.setId(recipe.getId());
        recipeCommand.setSource(recipe.getSource());
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setDescription(recipe.getDescription());
        recipeCommand.setDifficulty(recipe.getDifficulty());
        recipeCommand.setDirection(recipe.getDirection());
        recipeCommand.setImage(recipe.getImage());
        recipeCommand.setPrepTime(recipe.getPrepTime());
        recipeCommand.setServings(recipe.getServings());
        recipeCommand.setUrl(recipe.getUrl());
        recipeCommand.setNote(noteConverter.convert(recipe.getNote()));

        if (recipe.getCategories() != null && recipe.getCategories().size() > 0) {
            recipe.getCategories()
                    .forEach((category) -> recipeCommand.getCategories().add(categoryConverter.convert(category)));
        }

        if(recipe.getIngredients() !=null && recipe.getIngredients().size()>0){
            recipe.getIngredients()
                    .forEach(ingredient -> recipeCommand.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return recipeCommand;
    }

}
