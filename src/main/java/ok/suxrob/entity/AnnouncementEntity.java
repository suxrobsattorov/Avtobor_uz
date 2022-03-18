package ok.suxrob.entity;

import lombok.Getter;
import lombok.Setter;
import ok.suxrob.enums.*;
import ok.suxrob.enums.parameters.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    @Enumerated(EnumType.STRING)
    private IHave iHave;
    @Column(name = "avto_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AvtoType avtoType;
    @Column(name = "i_want_to", nullable = false)
    @Enumerated(EnumType.STRING)
    private IWantTo iWantTo;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Region region;
    @Column(name = "patment_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Column(nullable = false)
    private Long price;
    @Column(name = "price_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PriceType priceType;
    //Parameters
    @Column
    private Integer year;
    @Column
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    @Column
    @Enumerated(EnumType.STRING)
    private Condition condition;
    @Column
    @Enumerated(EnumType.STRING)
    private WheelDrive wheelDrive;
    @Column
    private Integer seatsCount;
    @Column
    @Enumerated(EnumType.STRING)
    private BodyColor bodyColor;
    @Column
    private Integer engineSize;
    @Column
    private Integer tireSize;
    @Column
    private Integer mileAge;
    @Column
    private Integer horsePower;
    @Column
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    //Conditions
    @Column
    private Boolean ABS;
    @Column
    private Boolean airbags;
    @Column
    private Boolean antiSlipRegulator;
    @Column
    private Boolean  audioSystem;
    @Column
    private Boolean cruiseControl;
    @Column
    private Boolean gasEquipment;
    @Column
    private Boolean heatedSeats;
    @Column
    private Boolean parktronic;
    @Column
    private Boolean powerWindows;
    @Column
    private Boolean sunroof;
    @Column
    private Boolean airCondition;
    @Column
    private Boolean alloyWheels;
    @Column
    private Boolean arcs;
    @Column
    private Boolean centralLocking;
    @Column
    private Boolean fogLights;
    @Column
    private Boolean  heatedMirrors;
    @Column
    private Boolean navigationSystem;
    @Column
    private Boolean powerSteering;
    @Column
    private Boolean signaling;
    @Column
    private String description;
    //photos
    //Confirmation code
    @Column
    private Boolean consent;
    @Column
    private LocalDateTime createdAt=LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private ProfileEntity profile;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "make_id")
    private MakeEntity make;

    @OneToMany(mappedBy = "announcement")
    private List<MessageEntity> chatEntityList;
}
