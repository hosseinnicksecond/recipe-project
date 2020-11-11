package home.train.repository;

import home.train.domain.Measure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class MeasureRepositoryTestIT {

    @Autowired
    MeasureRepository repository;

    @Autowired
    TestEntityManager entityManager;

    Measure measure;


    @BeforeEach
    void setUp() {
        measure= new Measure();
        measure.setDescription("Electric");
    }

    @Test
    void testWithNewEntity() {
        entityManager.persist(measure);
        entityManager.flush();

        Optional<Measure> foundMeasure=repository.findByDescription("Electric");

        assertThat("Electric").isEqualTo(foundMeasure.get().getDescription());
    }

    @Test
    void findByDescriptionPinch() {

        Optional<Measure> measure=repository.findByDescription("Pinch");

        assertThat("Pinch").isEqualTo(measure.get().getDescription());
    }

    @Test
//    @DirtiesContext // it's for load again SpringContext
    void findByDescriptionCup() {

        Optional<Measure> measure= repository.findByDescription("Cup");

        assertThat("Cup").isEqualTo(measure.get().getDescription());
    }
}