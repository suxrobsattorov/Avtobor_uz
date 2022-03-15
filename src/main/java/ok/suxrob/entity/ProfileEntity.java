package ok.suxrob.entity;

import lombok.Getter;
import lombok.Setter;
import ok.suxrob.enums.ProfileRole;
import ok.suxrob.enums.ProfileType;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfileType profileType;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String phone;
    @Column(nullable = false)
    private String password;
    @Column
    @Enumerated(EnumType.STRING)
    private ProfileRole role;
    @Column
    private String companyName;
    @Column
    private String address;
    @Column
    private Boolean consent;
    @Column
    private String web;
    @Column
    private String facebook;
    @Column
    private String instagram;
    @Column
    private String aboutUs;
}
