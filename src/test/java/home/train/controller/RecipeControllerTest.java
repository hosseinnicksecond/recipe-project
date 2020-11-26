package home.train.controller;

import home.train.Service.RecipeService;
import home.train.commands.RecipeCommand;
import home.train.domain.Recipe;
import home.train.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    RecipeService service;

    @Mock
    Model model;

    @InjectMocks
    RecipeController controller;

    @Captor
    ArgumentCaptor<Set<Recipe>> setArgumentCaptor;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc=MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getListRecipe() {

        Set<Recipe> recipes= new HashSet<>();
        recipes.add(new Recipe());
        recipes.add(new Recipe());

        given(service.findAll()).willReturn(recipes);

        controller.getListRecipe(model);

        then(service).should().findAll();
        then(model).should().addAttribute(eq("recipes"),setArgumentCaptor.capture());
        assertThat(2).isEqualTo(setArgumentCaptor.getValue().size());
    }
    @Test
    void NotFoundExceptionHandle() throws Exception {
        given(service.findById(anyLong())).willThrow(NotFoundException.class);

        mockMvc.perform(get("/recipe/{recipeId}/show",1))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }

    @Test
    void testWebInteraction() throws Exception {

        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/recipe"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipes"));
    }

    @Test
    void testWebGetById() throws Exception {

        Recipe recipe= new Recipe();
        recipe.setId(1L);

        given(service.findById(1L)).willReturn(recipe);
        mockMvc.perform(get("/recipe/{id}/show",1))
                .andExpect(status().isOk())
                .andExpect(model().attribute("recipe",recipe))
                .andExpect(view().name("recipe/show"));
    }

    @Test
    void shoeFormHtml() throws Exception {
        mockMvc.perform(get("/recipe/new"))
                .andExpect(view().name("recipe/recipeForm"))
                .andExpect(status().isOk());
    }

    @Test
    void PostMethod() throws Exception {

        RecipeCommand command= new RecipeCommand();
        command.setId(2L);

        given(service.save(any())).willReturn(command);

        mockMvc.perform(post("/recipe/add").contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(view().name("redirect:/recipe/2/show/"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void updateMethod() throws Exception {
        RecipeCommand command=new RecipeCommand();
        command.setId(2L);

        given(service.findByIdCommand(anyLong())).willReturn(command);

        mockMvc.perform(get("/recipe/{id}/update",2))
                .andExpect(view().name("recipe/recipeForm"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void deleteRecipe() throws Exception {

        mockMvc.perform(get("/recipe/{id}/delete",2))
                .andExpect(view().name("redirect:/recipe"))
                .andExpect(status().is3xxRedirection());

        verify(service,times(1)).deleteRecipeById(anyLong());
    }
}