package home.train.controller;

import home.train.Service.RecipeService;
import home.train.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        mockMvc.perform(get("/recipe/show/{id}",1))
                .andExpect(status().isOk())
                .andExpect(model().attribute("recipe",recipe))
                .andExpect(view().name("recipe/show"));
    }
}