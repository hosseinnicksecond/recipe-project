package home.train.convertors;

import home.train.commands.NoteCommand;
import home.train.domain.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteCommandToNoteTest {

    NoteCommandToNote converter;

    @BeforeEach
    void setUp() {
        converter=new NoteCommandToNote();
    }

    @Test
    void nullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void notNullObject() {
        assertNotNull(converter.convert(new NoteCommand()));
    }

    @Test
    void convert() {
        NoteCommand command= new NoteCommand();
        command.setId(1L);
        command.setRecipeNote("dada");

        Note note=converter.convert(command);

        assertEquals(note.getId(),1l);
        assertEquals(note.getRecipeNotes(),"dada");
    }
}