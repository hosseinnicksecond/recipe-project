package home.train.Service;

import home.train.commands.IngredientCommand;
import home.train.commands.RecipeCommand;
import home.train.convertors.IngredientCommandToIngredient;
import home.train.convertors.IngredientToIngredientCommand;
import home.train.convertors.MeasureCommandToMeasure;
import home.train.convertors.MeasureToMeasureCommand;
import home.train.domain.Ingredient;
import home.train.domain.Measure;
import home.train.domain.Recipe;
import home.train.repository.MeasureRepository;
import home.train.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.only;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    MeasureRepository measureRepository;

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    IngredientService service;

    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new MeasureToMeasureCommand());
        this.ingredientCommandToIngredient= new IngredientCommandToIngredient(new MeasureCommandToMeasure());
    }

    @BeforeEach
    void setUp() {
        service= new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand,
                ingredientCommandToIngredient, measureRepository);
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

    @Test

    void saveIngredient() {
        Measure measure= new Measure();
        measure.setId(1L);
        measure.setDescription("cup");

        Ingredient ingredient1= new Ingredient();
        ingredient1.setId(3L);
        ingredient1.setDescription("dada");

        Ingredient ingredient= new Ingredient();
        ingredient.setId(3L);
        ingredient.setMeasures(measure);
        ingredient.setDescription("dodo");

        Recipe savedRecipe= new Recipe();
        savedRecipe.setId(2L);
        savedRecipe.addIngredient(ingredient);


        ingredient1.setMeasures(measure);

        Optional<Measure> measureOptional=Optional.of(measure);

        Recipe recipe= new Recipe();
        recipe.setId(2L);
        recipe.addIngredient(ingredient1);
        ingredient1.setRecipe(recipe);

        IngredientCommand command=ingredientToIngredientCommand.convert(ingredient);
        Optional<Recipe> optionalRecipe=Optional.of(recipe);

        given(recipeRepository.findById(anyLong())).willReturn(optionalRecipe);
        given(measureRepository.findById(anyLong())).willReturn(measureOptional);
        given(recipeRepository.save(any())).willReturn(savedRecipe);

        IngredientCommand command1=service.saveIngredient(command);

        assertEquals("dodo",command1.getDescription());

    }

    @Test
    void ingredientDelete() {
        Recipe recipe= new Recipe();
        recipe.setId(1L);
        Ingredient ingredient=new Ingredient();
        ingredient.setId(1L);
        recipe.addIngredient(ingredient);

        given(recipeRepository.findById(anyLong())).willReturn(Optional.of(recipe));

        service.deleteIngredient(ingredientToIngredientCommand.convert(ingredient));

        assertEquals(0,recipe.getIngredients().size());
    }
}