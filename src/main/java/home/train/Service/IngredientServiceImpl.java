package home.train.Service;

import home.train.commands.IngredientCommand;
import home.train.commands.MeasureCommand;
import home.train.convertors.IngredientCommandToIngredient;
import home.train.convertors.IngredientToIngredientCommand;
import home.train.domain.Ingredient;
import home.train.domain.Recipe;
import home.train.repository.MeasureRepository;
import home.train.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final MeasureRepository measureRepository;

    public IngredientServiceImpl(RecipeRepository recipeRepository,
                                 IngredientToIngredientCommand ingredientConverter, IngredientCommandToIngredient ingredientCommandToIngredient, MeasureRepository measureRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientConverter;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.measureRepository = measureRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long IngredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (recipeOptional.isEmpty()) {
            log.info("recipe by {} id not exist ", recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommand = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(IngredientId))
                .map(ingredientToIngredientCommand::convert).findFirst();

        if (ingredientCommand.isEmpty()) {
            log.info("ingredient by {} id not exist", IngredientId);
        }

        return ingredientCommand.get();
    }

    @Override
    public IngredientCommand saveIngredient(IngredientCommand command) {

      Optional<Recipe> recipeOptional=recipeRepository.findById(command.getRecipeId());

      if(recipeOptional.isEmpty()){
          log.info("recipe with {} id not found",command.getRecipeId());
          return new IngredientCommand();
      }else {
          Recipe recipe=recipeOptional.get();

          Optional<Ingredient> ingredientOptional= recipe.getIngredients().stream()
                  .filter(ingredient -> ingredient.getId().equals(command.getId()))
                  .findFirst();
          if(ingredientOptional.isPresent()){
              Ingredient ingredient=ingredientOptional.get();
              ingredient.setDescription(command.getDescription());
              ingredient.setAmount(command.getAmount());
              ingredient.setMeasures(measureRepository.findById(command.getMeasure()
                      .getId()).orElseThrow(()->new RuntimeException("Measure Not Found")));
          }else {
              recipe.addIngredient(ingredientCommandToIngredient.convert(command));
          }

          Recipe savedRecipe=recipeRepository.save(recipe);

          return ingredientToIngredientCommand.convert(savedRecipe.getIngredients().stream()
                 .filter(ingredient -> ingredient.getId().equals(command.getId()))
                  .findFirst().get());
      }
    }
}
