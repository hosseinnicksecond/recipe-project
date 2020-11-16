package home.train.convertors;

import home.train.commands.NoteCommand;
import home.train.domain.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteToNoteCommandTest {

    NoteToNoteCommand converter;

    @BeforeEach
    void setUp() {
        converter= new NoteToNoteCommand();
    }

    @Test
    void nullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void NotNullObject() {
        assertNotNull(converter.convert(new Note()));
    }

    @Test
    void convert() {
        Note note=new Note();
        note.setId(1L);
        note.setRecipeNotes("dada");

        NoteCommand command=converter.convert(note);

        assertEquals(command.getId(),1L);
        assertEquals(command.getRecipeNote(),"dada");
    }
}