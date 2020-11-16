package home.train.convertors;

import home.train.commands.IngredientCommand;
import home.train.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {

    IngredientToIngredientCommand converter;

    @BeforeEach
    void setUp() {
        converter= new IngredientToIngredientCommand(new MeasureToMeasureCommand());
    }

    @Test
    void NullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void NotNullObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    void convert() {
        Ingredient ingredient= new Ingredient();
        ingredient.setId(1L);
        ingredient.setDescription("dada");
        ingredient.setAmount(new BigDecimal("3.20"));
        ingredient.setMeasures(null);

        IngredientCommand command=converter.convert(ingredient);

        assertEquals(command.getId(),1L);
        assertEquals(command.getDescription(),"dada");
        assertEquals(command.getAmount(),new BigDecimal("3.20"));
    }
}