package ok.suxrob.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDTO {
    private Integer id;
    private Integer profileId;
    private Integer announcementId;
    private String profileName;
    private String profileEmail;
    private String profilePhone;
    private String content;
}
