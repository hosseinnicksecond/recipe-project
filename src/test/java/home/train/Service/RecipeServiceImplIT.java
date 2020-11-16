package home.train.Service;

import home.train.commands.RecipeCommand;
import home.train.convertors.RecipeCommandToRecipe;
import home.train.convertors.RecipeToRecipeCommand;
import home.train.domain.Recipe;
import home.train.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RecipeServiceImplIT {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeService service;


    @Test
    @Transactional
    void save() {
        Iterable<Recipe> recipes=recipeRepository.findAll();
        Recipe testRecipe=recipes.iterator().next();
        RecipeCommand command=recipeToRecipeCommand.convert(testRecipe);

        assert command != null;
        command.setDescription("new description");
        RecipeCommand foundCommend=service.save(command);

        assertEquals(command.getId(),foundCommend.getId());
        assertEquals("new description",foundCommend.getDescription());
        assertEquals(testRecipe.getCategories().size(),foundCommend.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(),foundCommend.getIngredients().size());

    }
}