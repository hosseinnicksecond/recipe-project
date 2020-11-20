package home.train.controller;

import home.train.Service.ImageService;
import home.train.Service.RecipeService;
import home.train.commands.RecipeCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/image")
    public String showForm(@PathVariable(value = "id")String id, Model model){

        RecipeCommand command=recipeService.findByIdCommand(Long.valueOf(id));

        model.addAttribute("recipe",command);

        return "recipe/imageUploadForm";
    }

    @PostMapping("/recipe/{id}/image")
    public String processFormImage(@PathVariable(value = "id") String id,
                                   @RequestParam ("imageFile")MultipartFile file){
        imageService.saveImage(Long.valueOf(id),file);
        return "redirect:/recipe/"+id+"/show";
    }

    @GetMapping("/recipe/{id}/recipeImage")
    public void showImage(@PathVariable(value = "id")String id, HttpServletResponse response) throws IOException {

        log.info("from show image method");
        RecipeCommand command= recipeService.findByIdCommand(Long.valueOf(id));

        System.out.println(command.getId());
        if(command.getImage()!=null){

            byte[] loadedImage=new byte[command.getImage().length];

            int i=0;

            log.info("recipe have image ");

            for(Byte b:command.getImage()){
                loadedImage[i++]=b;
            }

            response.setContentType("image/jpeg");
            InputStream is=new ByteArrayInputStream(loadedImage);
            IOUtils.copy(is,response.getOutputStream());
        }
        log.info("image not found");
    }
}
