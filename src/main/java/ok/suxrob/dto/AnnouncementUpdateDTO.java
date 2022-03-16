package ok.suxrob.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ok.suxrob.enums.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnnouncementUpdateDTO {
    private String name;
    private String contact;
    private IHave iHave;
    private AvtoType avtoType;
    private IWantTo iWantTo;
    //make,
    private String make;
    private Region region;
    private PaymentType paymentType;
    private Long price;
    private PriceType priceType;
    //Parameters
    private String parameters;
    //Conditions
    private String conditions;
    private String description;
    //photos
    //Confirmation code
    private Boolean consent;
}
