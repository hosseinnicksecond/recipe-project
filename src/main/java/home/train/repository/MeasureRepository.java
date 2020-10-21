package home.train.repository;

import home.train.domain.Measure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MeasureRepository extends CrudRepository<Measure,Long> {

    Optional<Measure> findByDescription(String description);

}
