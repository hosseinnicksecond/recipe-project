package home.train.convertors;

import home.train.commands.CategoryCommand;
import home.train.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {

    CategoryToCategoryCommand converter;

    @BeforeEach
    void setUp() {
        converter= new CategoryToCategoryCommand();
    }

    @Test
    void nullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void NotNullObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    void convert() {
        Category category= new Category();
        category.setId(1L);
        category.setDescription("dada");

        CategoryCommand command=converter.convert(category);

        assertEquals(command.getId(),1L);
        assertEquals(command.getDescription(),"dada");
    }
}