package home.train.Service;

import home.train.commands.MeasureCommand;
import home.train.convertors.MeasureToMeasureCommand;
import home.train.repository.MeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MeasureServiceImpl implements MeasureService {

    private final MeasureRepository repository;
    private final MeasureToMeasureCommand converter;


    public MeasureServiceImpl(MeasureRepository repository, MeasureToMeasureCommand converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public Set<MeasureCommand> findAll() {
        return StreamSupport.stream(repository.findAll()
                .spliterator(),false)
                .map(converter::convert)
                .collect(Collectors.toSet());

    }
}
