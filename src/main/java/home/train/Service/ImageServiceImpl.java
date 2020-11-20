package home.train.Service;

import home.train.domain.Recipe;
import home.train.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImage(Long recipeId, MultipartFile file) {

        Optional<Recipe> optionalRecipe= recipeRepository.findById(recipeId);

        if(optionalRecipe.isPresent()|| !file.isEmpty()){

            try {
                Byte[] savedFile=new Byte[(file.getBytes().length)];

                int i=0;

                for(byte b:file.getBytes()){
                    savedFile[i++]=b;
                }
                optionalRecipe.get().setImage(savedFile);
                recipeRepository.save(optionalRecipe.get());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        log.info("from image service ");
    }
}
