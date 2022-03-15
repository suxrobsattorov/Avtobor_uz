package ok.suxrob.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ok.suxrob.enums.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddListingDTO {
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
    @NotEmpty(message = "region must not be empty or null")
    private Region region;
    @NotEmpty(message = "paymentType must not be empty or null")
    private PaymentType paymentType;
    private Long price;
    @NotEmpty(message = "priceType must not be empty or null")
    private PriceType priceType;
    //Parameters
    //Conditions
    private String description;
    //photos
    //Confirmation code
    private Boolean consent;
}
