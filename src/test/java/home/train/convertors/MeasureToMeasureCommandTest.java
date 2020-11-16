package home.train.convertors;

import home.train.commands.MeasureCommand;
import home.train.domain.Measure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeasureToMeasureCommandTest {

    MeasureToMeasureCommand converter;

    @BeforeEach
    void setUp() {
        converter=new MeasureToMeasureCommand();
    }

    @Test
    void nullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void notNullObject() {
        assertNotNull(converter.convert(new Measure()));
    }

    @Test
    void convert() {
        Measure measure= new Measure();
        measure.setId(1L);
        measure.setDescription("dada");

        MeasureCommand command=converter.convert(measure);

        assertEquals(command.getId(),1L);
        assertEquals(command.getDescription(),"dada");
    }
}