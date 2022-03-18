package ok.suxrob.dto;

import lombok.Data;

@Data
public class MakeUpdateDTO {
    private String name;
    private Integer parentId;
    private Integer mainMake;
}
