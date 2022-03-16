package ok.suxrob.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ok.suxrob.enums.ShoOrCoPostType;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShoOrCoPostDTO {
    private Integer id;
    @NotEmpty(message = "Type must not be empty or null")
    private ShoOrCoPostType type;
    @NotEmpty(message = "Title must not be empty or null")
    private String title;
    private String content;
    @NotEmpty(message = "Phone must not be empty or null")
    private String phone;
    private String location;
    private String email;
    private String web;
    private String workingDays;
    private String workingHours;
    private String lunch;
    private String facebook;
    private String instagram;
    private LocalDateTime createdAt;
}
