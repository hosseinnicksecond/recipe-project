package home.train.controller;

import home.train.Service.IngredientService;
import home.train.Service.MeasureService;
import home.train.Service.RecipeService;
import home.train.commands.IngredientCommand;
import home.train.commands.MeasureCommand;
import home.train.commands.RecipeCommand;
import home.train.domain.Measure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {

    @Mock
    RecipeService service;

    @Mock
    IngredientService ingredientService;

    @Mock
    MeasureService measureService;

    IngredientController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        controller = new IngredientController(service, ingredientService, measureService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    void showIngredient() throws Exception {

        RecipeCommand command = new RecipeCommand();
        command.setId(2L);
        command.getIngredients().add(new IngredientCommand());
        command.getIngredients().add(new IngredientCommand());

        when(service.findByIdCommand(anyLong())).thenReturn(command);

        mockMvc.perform(get("/recipe/{id}/ingredient", 2))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));
        ;
        then(service).should().findByIdCommand(anyLong());

    }

    @Test
    void getIngredient() throws Exception {

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(1L);

        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

        mockMvc.perform(get("/recipe/{recipeId}/ingredient/{ingredientId}/show", 1L, 2L))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));

        verify(ingredientService).findByRecipeIdAndIngredientId(anyLong(), anyLong());
    }

    @Test
    void showIngredientForm() throws Exception {

        IngredientCommand ingredientCommand = new IngredientCommand();
        Set<MeasureCommand> measureSet = new HashSet<>();

        given(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).willReturn(ingredientCommand);
        given(measureService.findAll()).willReturn(measureSet);

        mockMvc.perform(get("/recipe/{recipeId}/ingredient/{ingredientId}/update", 1L, 2L))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientForm"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));

        then(ingredientService).should().findByRecipeIdAndIngredientId(1L, 2L);
        then(measureService).should().findAll();
    }

    @Test
    void processIngredientForm() throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(2L);
        ingredientCommand.setId(1L);
        given(ingredientService.saveIngredient(any())).willReturn(ingredientCommand);

        mockMvc.perform(post("/recipe/2/ingredient", 2L)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(model().attributeExists("command"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredient/1/show"));
    }
}