package home.train.Service;

import home.train.domain.Recipe;
import home.train.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.SecondaryTable;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class RecipeServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    RecipeServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {

        Set<Recipe> recipeSet= new HashSet<>();
        recipeSet.add(new Recipe());
        given(recipeRepository.findAll()).willReturn(recipeSet);

        Set<Recipe> recipes=service.findAll();

        then(recipeRepository).should().findAll();
        assertThat(recipes).isEqualTo(recipeSet);
    }
}