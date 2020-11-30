package home.train.Service;

import home.train.commands.MeasureCommand;
import home.train.convertors.MeasureToMeasureCommand;
import home.train.domain.Measure;
import home.train.repository.MeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MeasureServiceImplTest {

    @Mock
    MeasureRepository repository;

    MeasureToMeasureCommand converter;
    MeasureService service;
    @BeforeEach
    void setUp() {
        converter= new MeasureToMeasureCommand();
        service= new MeasureServiceImpl(repository,converter);
    }

    @Test
    void findAll() {
        Set<Measure> measures= new HashSet<>();
        Measure measure1= new Measure();
        measure1.setId(1L);
        measures.add(measure1);
        Measure measure2= new Measure();
        measure2.setId(2L);
        measures.add(measure2);

        when(repository.findAll()).thenReturn(measures);

        Set<MeasureCommand> commands=service.findAll();

        assertEquals(2,commands.size());
//        assertEquals(2L,commands.iterator().next().getId());

        verify(repository).findAll();

    }
}