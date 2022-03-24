package ok.suxrob.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttachDTO {
    private Integer id;
    private String key;
    private String originName;
    private Long size;
    private String filePath;
    private String extension;
    private LocalDateTime createdDate;
    private String url;
}
