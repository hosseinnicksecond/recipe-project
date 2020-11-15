package home.train.controller;

import home.train.Service.RecipeService;
import home.train.domain.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/recipe"})
    public String getListRecipe(Model model){
        model.addAttribute("recipes",recipeService.findAll());
        return "recipes";
    }

    @RequestMapping("/recipe/show/{id}")
    public String getById(@PathVariable(name = "id") String id, Model model){
        Recipe recipe=recipeService.findById(Long.valueOf(id));
        model.addAttribute("recipe",recipe);
        return "recipe/show";
    }
}
