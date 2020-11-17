package home.train.Service;

import home.train.commands.IngredientCommand;
import home.train.commands.RecipeCommand;
import home.train.convertors.IngredientToIngredientCommand;
import home.train.convertors.MeasureToMeasureCommand;
import home.train.domain.Ingredient;
import home.train.domain.Recipe;
import home.train.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    private final IngredientToIngredientCommand ingredientConverter;

    IngredientService service;

    public IngredientServiceImplTest() {
        this.ingredientConverter = new IngredientToIngredientCommand(new MeasureToMeasureCommand());
    }

    @BeforeEach
    void setUp() {
        service= new IngredientServiceImpl(recipeRepository,ingredientConverter);
    }

    @Test
    void findByRecipeIdAndIngredientId() {
        Recipe command= new Recipe();
        command.setId(1L);

        Ingredient ingredient1= new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setDescription("ingredient1");

        Ingredient ingredient2= new Ingredient();
        ingredient2.setId(2L);
        ingredient2.setDescription("ingredient2");

        Ingredient ingredient3= new Ingredient();
        ingredient3.setId(3L);
        ingredient3.setDescription("ingredient3");

        command.addIngredient(ingredient1);
        command.addIngredient(ingredient2);
        command.addIngredient(ingredient3);
        Optional<Recipe> recipe=Optional.of(command);

        given(recipeRepository.findById(anyLong())).willReturn(recipe);

        IngredientCommand ingredientCommand=service.findByRecipeIdAndIngredientId(1L,3L);

        assertNotNull(ingredientCommand);
        assertEquals(ingredient3.getDescription(),ingredientCommand.getDescription());
        assertEquals(ingredient3.getId(),ingredientCommand.getId());
        assertEquals(ingredientCommand.getRecipeId(),command.getId());

    }
}