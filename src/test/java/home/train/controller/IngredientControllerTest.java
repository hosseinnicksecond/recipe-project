package home.train.controller;

import home.train.Service.RecipeService;
import home.train.commands.IngredientCommand;
import home.train.commands.RecipeCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {

    @Mock
    RecipeService service;

    IngredientController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        controller= new IngredientController(service);
        mockMvc= MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    void showIngredient() throws Exception {

        RecipeCommand command= new RecipeCommand();
        command.setId(2L);
        command.getIngredients().add(new IngredientCommand());
        command.getIngredients().add(new IngredientCommand());

        when(service.findByIdCommand(anyLong())).thenReturn(command);

        mockMvc.perform(get("/recipe/{id}/ingredient",2))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));
                  ;
        then(service).should().findByIdCommand(anyLong());

    }
}