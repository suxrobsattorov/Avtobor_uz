package ok.suxrob.dto.announcement;

import lombok.Data;
import ok.suxrob.enums.announcement.Region;
import ok.suxrob.enums.announcement.parameters.BodyColor;

@Data
public class AnnouncementFilterDTO {
    private Integer id;
    private Integer makeId;
    private Integer year;
    private String transmission;
    private Long price;
    private Integer mileage;
    private Region region;
    private BodyColor bodyColor;
}
