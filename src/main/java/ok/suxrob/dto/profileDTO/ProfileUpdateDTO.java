package ok.suxrob.dto.profileDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileUpdateDTO {
    private String name;
    private String surname;
    @Email(message = "does not match email")
    private String email;
    private String phone;
    private String companyName;
    private String address;
    private String web;
    private String facebook;
    private String instagram;
    private String aboutUs;
}
