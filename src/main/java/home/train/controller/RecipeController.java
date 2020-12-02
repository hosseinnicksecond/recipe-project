package home.train.controller;

import home.train.Service.RecipeService;
import home.train.commands.RecipeCommand;
import home.train.domain.Recipe;
import home.train.exception.ExceptionHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class RecipeController {

    public static final String RECIPE_FORM = "recipe/recipeForm";
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @InitBinder
    public void defaultBinder(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
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
        return RECIPE_FORM;
    }

    @PostMapping("/recipe/add")
    public String processForm(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand
            , BindingResult result){
        if(result.hasErrors()){
            result.getAllErrors().forEach(objectError -> log.error(objectError.toString()));

            return RECIPE_FORM;
        }
        RecipeCommand saved = recipeService.save(recipeCommand);
        return "redirect:/recipe/"+saved.getId()+"/show/";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable(name = "id")String id,Model model){
        RecipeCommand command= recipeService.findByIdCommand(Long.valueOf(id));
        model.addAttribute("recipe",command);
        return "recipe/recipeForm";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable(name = "id")String id){
        recipeService.deleteRecipeById(Long.valueOf(id));
        return "redirect:/recipe";
    }



}
