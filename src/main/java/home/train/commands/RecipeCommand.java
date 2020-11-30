package home.train.commands;

import home.train.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    @NotBlank
    @Size
    private String description;

    @Min(1)
    @Max(100)
    private Integer prepTime;

    @Min(1)
    @Max(100)
    private Integer cookTime;

    @Min(1)
    @Max(100)
    private Integer servings;

    private String source;
    private String url;

    @NotBlank
    private String direction;

    private Byte[] image;
    private Set<IngredientCommand> ingredients=new HashSet<>();
    private Set<CategoryCommand> categories= new HashSet<>();
    private NoteCommand note;
    private Difficulty difficulty;
}
