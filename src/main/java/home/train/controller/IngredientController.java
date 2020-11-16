package home.train.controller;

import home.train.Service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService service;

    public IngredientController(RecipeService service) {
        this.service = service;
    }

    @GetMapping("/recipe/{id}/ingredient")
    public String showIngredient(@PathVariable(name = "id")String id, Model model){
        log.info("id for show ingredient is {}",id);
        model.addAttribute("recipe",service.findByIdCommand(Long.valueOf(id)));
        return "recipe/ingredient/list";
    }
}
