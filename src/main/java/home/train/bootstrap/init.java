package home.train.bootstrap;

import home.train.domain.*;
import home.train.repository.CategoryRepository;
import home.train.repository.MeasureRepository;
import home.train.repository.RecipeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
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
        Recipe Guacamole = new Recipe();
        Guacamole.setDescription("Spicy Grilled Chicken Tacos");
        Guacamole.setPrepTime(20);
        Guacamole.setCookTime(15);
        Guacamole.setServings(2);

        Set<Ingredient> ingredients= new HashSet<>();

        Ingredient ingredient= new Ingredient();
        ingredient.setDescription("avocados");
        ingredient.setMeasures(measureRepository.findByDescription("Ripe").get());
        ingredient.setAmount(new BigDecimal("2"));
        ingredient.setRecipe(Guacamole);
        ingredients.add(ingredient);

        ingredient= new Ingredient();
        ingredient.setDescription("salt");
        ingredient.setMeasures(measureRepository.findByDescription("Teaspoon").get());
        ingredient.setAmount(new BigDecimal("1.4"));
        ingredient.setRecipe(Guacamole);
        ingredients.add(ingredient);

        ingredient= new Ingredient();
        ingredient.setDescription("fresh lime juice or lemon juice");
        ingredient.setMeasures(measureRepository.findByDescription("Tablespoon").get());
        ingredient.setAmount(new BigDecimal("1"));
        ingredient.setRecipe(Guacamole);
        ingredients.add(ingredient);

        ingredient= new Ingredient();
        ingredient.setDescription("minced red onion or thinly sliced green onion");
        ingredient.setMeasures(measureRepository.findByDescription("Cup").get());
        ingredient.setAmount(new BigDecimal("1.4"));
        ingredient.setRecipe(Guacamole);
        ingredients.add(ingredient);

        ingredient= new Ingredient();
        ingredient.setDescription("serrano chiles, stems and seeds removed, minced");
        ingredient.setMeasures(measureRepository.findByDescription("Tablespoon").get());
        ingredient.setAmount(new BigDecimal("1.5"));
        ingredient.setRecipe(Guacamole);
        ingredients.add(ingredient);

        ingredient= new Ingredient();
        ingredient.setDescription("cilantro (leaves and tender stems), finely chopped");
        ingredient.setMeasures(measureRepository.findByDescription("Tablespoon").get());
        ingredient.setAmount(new BigDecimal("2"));
        ingredient.setRecipe(Guacamole);
        ingredients.add(ingredient);

        ingredient= new Ingredient();
        ingredient.setDescription("of freshly grated black pepper");
        ingredient.setMeasures(measureRepository.findByDescription("Dash").get());
        ingredient.setAmount(new BigDecimal("1"));
        ingredient.setRecipe(Guacamole);
        ingredients.add(ingredient);

        ingredient= new Ingredient();
        ingredient.setDescription("ripe tomato, seeds and pulp removed, chopped");
        ingredient.setMeasures(measureRepository.findByDescription("Piece").get());
        ingredient.setAmount(new BigDecimal("1"));
        ingredient.setRecipe(Guacamole);
        ingredients.add(ingredient);

        ingredient= new Ingredient();
        ingredient.setDescription("Red radishes or jicama, to garnish");
        ingredient.setMeasures(measureRepository.findByDescription("").get());
        ingredient.setAmount(new BigDecimal("1"));
        ingredient.setRecipe(Guacamole);
        ingredients.add(ingredient);

        ingredient= new Ingredient();
        ingredient.setDescription("Tortilla chips, to serve");
        ingredient.setMeasures(measureRepository.findByDescription("").get());
        ingredient.setAmount(new BigDecimal("1"));
        ingredient.setRecipe(Guacamole);
        ingredients.add(ingredient);

        Guacamole.setIngredients(ingredients);

        Set<Category> categories=new HashSet<>();
        categories.add(categoryRepository.findByDescription("Mexican").get());
        categories.add(categoryRepository.findByDescription("Vegan").get());
        categories.add(categoryRepository.findByDescription("Side Dish").get());
        categories.add(categoryRepository.findByDescription("Dip").get());

        Guacamole.setCategories(categories);

        Guacamole.setDifficulty(Difficulty.MODERATE);

        Note note = new Note();
        note.setRecipeNotes("its good to go");
        note.setRecipe(Guacamole);
        Guacamole.setNote(note);

        System.out.println(recipeRepository.save(Guacamole));


    }
}
