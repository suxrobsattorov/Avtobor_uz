package ok.suxrob.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShoOrCoPostUpdateDTO {
    private String title;
    private String content;
    private String phone;
    private String location;
    private String email;
    private String web;
    private String workingDays;
    private String workingHours;
    private String lunch;
    private String facebook;
    private String instagram;
}
