package home.train.controller;

import home.train.Service.IngredientService;
import home.train.Service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.h2.engine.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService service;
    private final IngredientService ingredientService;
    public IngredientController(RecipeService service, IngredientService ingredientService) {
        this.service = service;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/recipe/{id}/ingredient")
    public String showIngredient(@PathVariable(name = "id")String id, Model model){
        log.info("id for show ingredient is {}",id);
        model.addAttribute("recipe",service.findByIdCommand(Long.valueOf(id)));
        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String getIngredient(@PathVariable(value = "recipeId")String recipeId,
                                @PathVariable(value = "ingredientId") String ingredientId, Model model){

        model.addAttribute("ingredient",
                ingredientService.
                        findByRecipeIdAndIngredientId(Long.valueOf(recipeId),Long.valueOf(ingredientId)));

        return "recipe/ingredient/show";

    }
}
