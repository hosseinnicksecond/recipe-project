package home.train.controller;

import home.train.Service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe")
    public String getListRecipe(Model model){
        model.addAttribute("recipes",recipeService.findAll());
        return "recipes";
    }
}
