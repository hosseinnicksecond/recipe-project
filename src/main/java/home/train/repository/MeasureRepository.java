package home.train.repository;

import home.train.domain.Measure;
import org.springframework.data.repository.CrudRepository;

public interface MeasureRepository extends CrudRepository<Measure,Long> {
}
