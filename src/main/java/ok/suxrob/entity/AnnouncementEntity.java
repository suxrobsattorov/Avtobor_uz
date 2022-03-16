package ok.suxrob.entity;

import lombok.Getter;
import lombok.Setter;
import ok.suxrob.enums.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "announcement")
public class AnnouncementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String contact;
    @Column(name = "i_have", nullable = false)
    private IHave iHave;
    @Column(name = "avto_type", nullable = false)
    private AvtoType avtoType;
    @Column(name = "i_want_to", nullable = false)
    private IWantTo iWantTo;
    //make,
    @Column
    private String make;
    @Column(nullable = false)
    private Region region;
    @Column(name = "patment_type", nullable = false)
    private PaymentType paymentType;
    @Column(nullable = false)
    private Long price;
    @Column(name = "price_type", nullable = false)
    private PriceType priceType;
    //Parameters
    @Column
    private String parameters;
    //Conditions
    @Column
    private String conditions;
    @Column
    private String description;
    //photos
    //Confirmation code
    @Column
    private Boolean consent;
}
