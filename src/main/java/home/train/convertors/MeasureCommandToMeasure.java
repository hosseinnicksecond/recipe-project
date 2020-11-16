package home.train.convertors;

import home.train.commands.MeasureCommand;
import home.train.domain.Measure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class MeasureCommandToMeasure implements Converter<MeasureCommand, Measure> {

    @Synchronized
    @Nullable
    @Override
    public Measure convert(MeasureCommand measureCommand) {
        if(measureCommand==null)   return null;

        final Measure measure= new Measure();
        measure.setId(measureCommand.getId());
        measure.setDescription(measureCommand.getDescription());
        return measure;
    }
}
