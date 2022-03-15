package ok.suxrob.dto.profileDTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ProfileUpdatePasswordDTO {
    @NotEmpty(message = "password must not be empty or null")
    private String oldPassword;
    @NotEmpty(message = "password must not be empty or null")
    private String newPassword;
    @NotEmpty(message = "password must not be empty or null")
    private String reenterPassword;
}
