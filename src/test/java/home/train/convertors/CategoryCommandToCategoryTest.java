package home.train.convertors;

import home.train.commands.CategoryCommand;
import home.train.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {

    CategoryCommandToCategory converter;

    @BeforeEach
    void setUp() {
        converter= new CategoryCommandToCategory();
    }

    @Test
    void nullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void notNullObject() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    void convert() {

        CategoryCommand categoryCommand= new CategoryCommand();
        categoryCommand.setId(1L);

        Category category=converter.convert(categoryCommand);

        assertEquals(category.getId(),1L);

    }
}