package home.train.convertors;

import home.train.commands.*;
import home.train.domain.Difficulty;
import home.train.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {

    RecipeCommandToRecipe converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeCommandToRecipe(new NoteCommandToNote()
                , new CategoryCommandToCategory()
                , new IngredientCommandToIngredient(new MeasureCommandToMeasure()));
    }

    @Test
    void nullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void notNullObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    void convert() {
        RecipeCommand recipeCommand= new RecipeCommand();
        recipeCommand.setId(1L);
        recipeCommand.setCookTime(20);
        recipeCommand.setPrepTime(15);
        recipeCommand.setDescription("DESCRIPTION");
        recipeCommand.setDifficulty(Difficulty.MODERATE);
        recipeCommand.setDirection("DIRECTIONS");
        recipeCommand.setServings(4);
        recipeCommand.setSource("SOURCE");
        recipeCommand.setUrl("www");

        NoteCommand noteCommand= new NoteCommand();
        noteCommand.setId(1L);
        noteCommand.setRecipeNote("recipeNote");
        recipeCommand.setNote(noteCommand);

        recipeCommand.getCategories().add(new CategoryCommand());
        recipeCommand.getCategories().add(new CategoryCommand());

        IngredientCommand ingredientCommand1= new IngredientCommand();
        IngredientCommand ingredientCommand2=new IngredientCommand();

        MeasureCommand measureCommand= new MeasureCommand();
        measureCommand.setId(1L);
        ingredientCommand1.setMeasure(measureCommand);

        measureCommand= new MeasureCommand();
        measureCommand.setId(2L);
        ingredientCommand2.setMeasure(measureCommand);

        recipeCommand.getIngredients().add(ingredientCommand1);
        recipeCommand.getIngredients().add(ingredientCommand2);

        Recipe recipe= converter.convert(recipeCommand);

        assertNotNull(recipe);
        assertEquals(1L,recipe.getId());
        assertEquals("www",recipe.getUrl());
        assertEquals(noteCommand.getRecipeNote(),recipe.getNote().getRecipeNotes());
        assertEquals(2,recipe.getCategories().size());
        assertEquals(2,recipe.getIngredients().size());
        assertNotNull(recipe.getIngredients().iterator().next().getMeasures());
        assertEquals(20, recipe.getCookTime());
        assertEquals(15, recipe.getPrepTime());
        assertEquals("DESCRIPTION", recipe.getDescription());
        assertEquals(Difficulty.MODERATE, recipe.getDifficulty());
        assertEquals("DIRECTIONS", recipe.getDirection());
        assertEquals(4, recipe.getServings());
        assertEquals("SOURCE", recipe.getSource());
    }
}