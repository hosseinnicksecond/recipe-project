package home.train.convertors;

import home.train.commands.NoteCommand;
import home.train.domain.Note;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NoteToNoteCommand implements Converter<Note, NoteCommand> {

    @Synchronized
    @Nullable
    @Override
    public NoteCommand convert(Note note) {
        if(note==null)return null;

        final NoteCommand noteCommand= new NoteCommand();
        noteCommand.setId(note.getId());
        noteCommand.setRecipeNote(note.getRecipeNotes());
        return noteCommand;
    }
}
