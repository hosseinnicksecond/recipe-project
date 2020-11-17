package home.train.controller;

import home.train.Service.IngredientService;
import home.train.Service.MeasureService;
import home.train.Service.RecipeService;
import home.train.commands.IngredientCommand;
import home.train.commands.MeasureCommand;
import home.train.domain.Measure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService service;
    private final IngredientService ingredientService;
    private final MeasureService measureService;
    public IngredientController(RecipeService service, IngredientService ingredientService, MeasureService measureService) {
        this.service = service;
        this.ingredientService = ingredientService;
        this.measureService = measureService;
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

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String showIngredientForm(@PathVariable(value = "recipeId")String recipeId
            ,@PathVariable(value = "ingredientId")String ingredientId, Model model){

        log.info("id for ingredient {}",ingredientId);

        IngredientCommand command= ingredientService.
                findByRecipeIdAndIngredientId(Long.valueOf(recipeId),Long.valueOf(ingredientId));

        log.info("id for ingredient measure {}",command.getMeasure().getId());

        model.addAttribute("ingredient",command);

        model.addAttribute("measureList",measureService.findAll());

        return "recipe/ingredient/ingredientForm";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String processIngredientForm(@ModelAttribute IngredientCommand command,Model model){

        log.info("description of command {}",command.getDescription());
        log.info("id of measure {}",command.getMeasure().getId());
        model.addAttribute("command",command);
        IngredientCommand savedIngredient= ingredientService.saveIngredient(command);

        return "redirect:/recipe/"+savedIngredient.getRecipeId()+"/ingredient/"+savedIngredient.getId()+"/show";
    }


}
