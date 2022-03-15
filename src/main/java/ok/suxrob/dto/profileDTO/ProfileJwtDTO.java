package ok.suxrob.dto.profileDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ok.suxrob.enums.ProfileType;

@Getter
@Setter
@AllArgsConstructor
public class ProfileJwtDTO {
    private Integer id;
    private ProfileType type;
}
