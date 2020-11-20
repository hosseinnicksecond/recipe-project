package home.train.controller;

import home.train.Service.ImageService;
import home.train.Service.RecipeService;
import home.train.commands.RecipeCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ImageControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    ImageService imageService;

    @InjectMocks
    ImageController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shoeForm() throws Exception {
        RecipeCommand command= new RecipeCommand();
        command.setId(1L);

        when(recipeService.findByIdCommand(1L)).thenReturn(command);

        mockMvc.perform(get("/recipe/{id}/image",command.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/imageUploadForm"));

        verify(recipeService).findByIdCommand(anyLong());
    }

    @Test
    void processFormImage() throws Exception {

        MockMultipartFile file=new MockMultipartFile("imageFile",
                "testing.txt","text/plain","something".getBytes());

        mockMvc.perform(multipart("/recipe/1/image").file(file))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("location","/recipe/1/show"));

        verify(imageService).saveImage(anyLong(),any());


    }
}