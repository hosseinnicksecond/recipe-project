package home.train.controller;

import home.train.Service.RecipeService;
import home.train.commands.RecipeCommand;
import home.train.domain.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"","/recipe"})
    public String getListRecipe(Model model){
        model.addAttribute("recipes",recipeService.findAll());
        return "recipes";
    }

    @GetMapping("/recipe/{id}/show")
    public String getById(@PathVariable(name = "id") String id, Model model){
        Recipe recipe=recipeService.findById(Long.valueOf(id));
        model.addAttribute("recipe",recipe);
        return "recipe/show";
    }

    @GetMapping("/recipe/new")
    public String getFormView(Model model){
        model.addAttribute("recipe",new RecipeCommand());
        return "recipe/recipeForm";
    }

    @PostMapping("/recipe/add")
    public String processForm(@ModelAttribute("recipe") RecipeCommand recipeCommand){
        RecipeCommand saved = recipeService.save(recipeCommand);
        return "redirect:/recipe/"+saved.getId()+"/show/";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable(name = "id")Long id,Model model){
        RecipeCommand command= recipeService.findByIdCommand(id);
        model.addAttribute("recipe",command);
        return "recipe/recipeForm";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable(name = "id")Long id){
        recipeService.deleteRecipeById(id);
        return "redirect:/recipe";
    }
}
