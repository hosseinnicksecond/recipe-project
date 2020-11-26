package home.train.controller;

import home.train.Service.IngredientService;
import home.train.Service.MeasureService;
import home.train.Service.RecipeService;
import home.train.commands.IngredientCommand;
import home.train.commands.MeasureCommand;
import home.train.commands.RecipeCommand;
import home.train.domain.Recipe;
import home.train.exception.ExceptionHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final MeasureService measureService;

    public IngredientController(RecipeService service, IngredientService ingredientService, MeasureService measureService) {
        this.recipeService = service;
        this.ingredientService = ingredientService;
        this.measureService = measureService;
    }

    @GetMapping("/recipe/{id}/ingredient")
    public String showIngredient(@PathVariable(name = "id") String id, Model model) {
        log.info("id for show recipe is {}", id);
        model.addAttribute("recipe", recipeService.findByIdCommand(Long.valueOf(id)));
        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String getIngredient(@PathVariable(value = "recipeId") String recipeId,
                                @PathVariable(value = "ingredientId") String ingredientId, Model model) {

        model.addAttribute("ingredient",
                ingredientService.
                        findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));

        return "recipe/ingredient/show";

    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String showIngredientForm(@PathVariable(value = "recipeId") String recipeId
            , @PathVariable(value = "ingredientId") String ingredientId, Model model) {

        log.info("id for ingredient {}", ingredientId);

        IngredientCommand command = ingredientService.
                findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId));

        model.addAttribute("ingredient", command);

        model.addAttribute("measureList", measureService.findAll());

        return "recipe/ingredient/ingredientForm";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String createNewIngredient(@PathVariable(value = "recipeId") String recipeId, Model model) {

        RecipeCommand recipeCommand = recipeService.findByIdCommand(Long.valueOf(recipeId));

        IngredientCommand command = new IngredientCommand();
        command.setRecipeId(Long.valueOf(recipeId));
        command.setMeasure(new MeasureCommand());

        model.addAttribute("ingredient", command);

        model.addAttribute("measureList", measureService.findAll());


        return "recipe/ingredient/ingredientForm";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable(value = "recipeId") String RecipeId,
                                   @PathVariable(value = "ingredientId") String ingredientId) {

        log.info("from delete ingredient recipeId->{} ingredientId->{}", RecipeId, ingredientId);

        IngredientCommand deletedIngredient = ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(RecipeId), Long.valueOf(ingredientId));
        if(deletedIngredient==null){
            throw  new ExceptionHandle("Not Found Recipe By ID Number : "+RecipeId);
        }
        ingredientService.deleteIngredient(deletedIngredient);

        return "redirect:/recipe/" + RecipeId + "/ingredient";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String processIngredientForm(@ModelAttribute IngredientCommand command, Model model) {

        model.addAttribute("command", command);
        IngredientCommand savedIngredient = ingredientService.saveIngredient(command);

        return "redirect:/recipe/" + savedIngredient.getRecipeId() + "/ingredient/" + savedIngredient.getId() + "/show";
    }


}
