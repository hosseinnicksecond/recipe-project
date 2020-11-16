package home.train.convertors;

import home.train.commands.IngredientCommand;
import home.train.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {

    IngredientCommandToIngredient ingredientConverter;

    @BeforeEach
    void setUp() {
        ingredientConverter=new IngredientCommandToIngredient(new MeasureCommandToMeasure());
    }

    @Test
    void nullObject() {
        assertNull(ingredientConverter.convert(null));
    }

    @Test
    void NotNullObject() {
        assertNotNull(ingredientConverter.convert(new IngredientCommand()));
    }

    @Test
    void convert() {
        IngredientCommand command= new IngredientCommand();
        command.setId(1L);
        command.setMeasure(null);
        command.setDescription("dada");
        command.setAmount(new BigDecimal("1.2"));

        Ingredient ingredient= ingredientConverter.convert(command);

        assertEquals(ingredient.getId(),1L);
        assertEquals(ingredient.getDescription(),"dada");
        assertEquals(ingredient.getAmount(),new BigDecimal("1.2"));
    }
}