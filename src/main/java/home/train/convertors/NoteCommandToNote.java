package home.train.convertors;

import home.train.commands.NoteCommand;
import home.train.domain.Note;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NoteCommandToNote implements Converter<NoteCommand, Note> {

    @Synchronized
    @Nullable
    @Override
    public Note convert(NoteCommand noteCommand) {

        if(noteCommand== null) return null;

        final Note note= new Note();
        note.setId(noteCommand.getId());
        note.setRecipeNotes(noteCommand.getRecipeNote());
        return note;
    }
}
