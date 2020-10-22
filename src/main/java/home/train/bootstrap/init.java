package home.train.bootstrap;


import home.train.domain.Measure;
import home.train.domain.Recipe;
import home.train.repository.CategoryRepository;
import home.train.repository.MeasureRepository;
import home.train.repository.RecipeRepository;
import org.springframework.boot.CommandLineRunner;

import java.util.List;
import java.util.Optional;


public class init implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final MeasureRepository measureRepository;

    public init(RecipeRepository recipeRepository, CategoryRepository categoryRepository, MeasureRepository measureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.measureRepository = measureRepository;
    }

    @Override
    public void run(String... args) throws Exception {

    }

    private List<Recipe> getRecipes(){

        Optional<Measure> EachMeasure=measureRepository.findByDescription("Each");
        if (!EachMeasure.isPresent()) throw new RuntimeException("Each Measure Not Found");

        return null;
    }
}
