package ok.suxrob.dto.profileDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ok.suxrob.enums.ProfileType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    private Integer id;
    @NotEmpty(message = "profileType must not be empty or null")
    private ProfileType profileType;
    @NotEmpty(message = "name must not be empty or null")
    private String name;
    @NotEmpty(message = "surname must not be empty or null")
    private String surname;
    @NotEmpty(message = "email must not be empty or null")
    @Email(message = "does not match email")
    private String email;
    @NotEmpty(message = "phone must not be empty or null")
    private String phone;
    @NotEmpty(message = "password must not be empty or null")
    private String password;
    private String companyName;
    private String address;
    private Boolean consent;
    private String web;
    private String facebook;
    private String instagram;
    private String aboutUs;

    private String jwt;//token
}
