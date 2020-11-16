package home.train.convertors;

import home.train.commands.MeasureCommand;
import home.train.domain.Measure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class MeasureToMeasureCommand implements Converter<Measure, MeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public MeasureCommand convert(Measure measure) {
        if(measure==null) return null;

        final MeasureCommand measureCommand= new MeasureCommand();
        measureCommand.setId(measure.getId());
        measureCommand.setDescription(measure.getDescription());

        return measureCommand;
    }
}
