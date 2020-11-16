package home.train.convertors;

import home.train.commands.RecipeCommand;
import home.train.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest {

    RecipeToRecipeCommand converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeToRecipeCommand(new CategoryToCategoryCommand()
                , new NoteToNoteCommand()
                , new IngredientToIngredientCommand(new MeasureToMeasureCommand()));
    }

    @Test
    void nullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void NotNullObject() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    void convert() {
        Recipe recipe=new Recipe();
        recipe.setId(1L);
        recipe.setUrl("www");
        recipe.setCookTime(20);
        recipe.setPrepTime(15);
        recipe.setDescription("DESCRIPTION");
        recipe.setDifficulty(Difficulty.HARD);
        recipe.setDirection("DIRECTIONS");
        recipe.setServings(4);
        recipe.setSource("SOURCE");


        Note note= new Note();
        note.setId(1L);
        note.setRecipeNotes("recipe note");
        recipe.setNote(note);

        Category category1= new Category();
        category1.setId(1L);
        category1.setDescription("category 1");
        Category category2= new Category();
        category2.setId(2L);
        category2.setDescription("category 2");
        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);


        Measure measure1 =new Measure();
        Measure measure2= new Measure();
        measure1.setId(1L);
        measure2.setId(2L);
        measure1.setDescription("Cup");
        measure2.setDescription("teaspoon");
        Ingredient ingredient1= new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setMeasures(measure1);
        ingredient1.setDescription("sugar");
        Ingredient ingredient2= new Ingredient();
        ingredient2.setMeasures(measure2);
        ingredient2.setId(2L);
        ingredient2.setDescription("salt");
        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);

        RecipeCommand command=converter.convert(recipe);


        assertEquals(20, command.getCookTime());
        assertEquals(15, command.getPrepTime());
        assertEquals("DESCRIPTION", command.getDescription());
        assertEquals(Difficulty.HARD, command.getDifficulty());
        assertEquals("DIRECTIONS", command.getDirection());
        assertEquals(4, command.getServings());
        assertEquals("SOURCE", command.getSource());
        assertEquals("www", command.getUrl());
        assertEquals(command.getId(),recipe.getId());
        assertEquals(command.getUrl(),recipe.getUrl());
        assertEquals(command.getNote().getId(),recipe.getNote().getId());
        assertEquals(recipe.getCategories().size(),command.getCategories().size());
//        assertEquals(command.getCategories().iterator().next().getId(),1L);
//        assertEquals(command.getCategories().iterator().next().getDescription(),"category 2");

        assertEquals(2,command.getIngredients().size());
//        assertEquals(command.getIngredients().iterator().next().getId(),1L);
    }
}