package home.train.repository;

import home.train.domain.Category;
import home.train.domain.Measure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,Long> {


    Optional<Category> findByDescription(String description);
}
