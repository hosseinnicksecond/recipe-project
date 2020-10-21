package home.train.controller;

import home.train.domain.Category;
import home.train.domain.Measure;
import home.train.repository.CategoryRepository;
import home.train.repository.MeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final MeasureRepository measureRepository;

    public IndexController(CategoryRepository categoryRepository, MeasureRepository measureRepository) {
        this.categoryRepository = categoryRepository;
        this.measureRepository = measureRepository;
    }

    @RequestMapping({"","/"})
    public String getIndex(){

        Optional<Category> getCategoryId=categoryRepository.findByDescription("Italian");
        Optional<Measure> getMeasure=measureRepository.findByDescription("Cup");

        System.out.println("**Category Id***"+getCategoryId.get().getId());
        System.out.println("**Measure ID**"+getMeasure.get().getId());
        return "Index";
    }
}
