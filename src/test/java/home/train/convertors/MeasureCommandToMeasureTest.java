package home.train.convertors;

import home.train.commands.MeasureCommand;
import home.train.domain.Measure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeasureCommandToMeasureTest {

    MeasureCommandToMeasure converter;

    @BeforeEach
    void setUp() {
        converter= new MeasureCommandToMeasure();
    }

    @Test
    void NullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void NotNullObject() {
        assertNotNull(converter.convert(new MeasureCommand()));
    }

    @Test
    void convert() {
        MeasureCommand command= new MeasureCommand();
        command.setId(1L);
        command.setDescription("dada");

        Measure measure=converter.convert(command);

        assertEquals(measure.getId(),1L);
        assertEquals(measure.getDescription(),"dada");
    }
}