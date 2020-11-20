package home.train.Service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void saveImage(Long recipeId, MultipartFile file);
}
