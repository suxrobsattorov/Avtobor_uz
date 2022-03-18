package ok.suxrob.dto;

import lombok.Data;

@Data
public class AnnouncementFilterDTO {
    private Integer id;
    private Integer makeId;
    private Integer year;
    private String transmission;
    private Long price;
    private Integer mileage;
}
