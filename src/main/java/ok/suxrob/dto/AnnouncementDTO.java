package ok.suxrob.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ok.suxrob.enums.*;
import ok.suxrob.enums.parameters.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnnouncementDTO {
    private Integer id;
    @NotBlank(message = "name must not be empty or null")
    private String name;
    @NotBlank(message = "contact must not be empty or null")
    @Size(max = 13, min = 13)
    private String contact;
    @NotEmpty(message = "iHave must not be empty or null")
    private IHave iHave;
    @NotEmpty(message = "avtoType must not be empty or null")
    private AvtoType avtoType;
    @NotEmpty(message = "iWantTo must not be empty or null")
    private IWantTo iWantTo;
    //make,
    private Integer makeId;
    @NotEmpty(message = "region must not be empty or null")
    private Region region;
    @NotEmpty(message = "paymentType must not be empty or null")
    private PaymentType paymentType;
    private Long price;
    @NotEmpty(message = "priceType must not be empty or null")
    private PriceType priceType;
    //Parameters
    private Integer year;
    private Transmission transmission;
    private Condition condition;
    private WheelDrive wheelDrive;
    private Integer seatsCount;
    private BodyColor bodyColor;
    private Integer engineSize;
    private Integer tireSize;
    private Integer mileAge;
    private Integer horsePower;
    private FuelType fuelType;
    //Conditions
    private Boolean ABS;
    private Boolean airbags;
    private Boolean antiSlipRegulator;
    private Boolean audioSystem;
    private Boolean cruiseControl;
    private Boolean gasEquipment;
    private Boolean heatedSeats;
    private Boolean parktronic;
    private Boolean powerWindows;
    private Boolean sunroof;
    private Boolean airCondition;
    private Boolean alloyWheels;
    private Boolean arcs;
    private Boolean centralLocking;
    private Boolean fogLights;
    private Boolean heatedMirrors;
    private Boolean navigationSystem;
    private Boolean powerSteering;
    private Boolean signaling;

    private String description;
    //photos
    //Confirmation code
    private Boolean consent;
    private Integer profileId;
}
