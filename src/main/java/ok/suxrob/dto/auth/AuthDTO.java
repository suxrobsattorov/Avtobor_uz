package ok.suxrob.dto.auth;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthDTO {
    @NotEmpty(message = "phone must not be empty or null")
    private String phone;
    @NotEmpty(message = "password must not be empty or null")
    private String password;
}
