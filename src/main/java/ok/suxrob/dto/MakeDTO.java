package ok.suxrob.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MakeDTO {
    private Integer id;
    @NotEmpty(message = "name must not be empty or null")
    private String name;
    private Integer parentId;
    private Integer mainMake;
}
