package ok.suxrob.entity;

import lombok.Getter;
import lombok.Setter;
import ok.suxrob.enums.ShoOrCoPostType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "showroom_or_company_post")
public class ShoOrCoPostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ShoOrCoPostType type;
    @Column(nullable = false)
    private String title;
    @Column
    private String content;
    @Column(nullable = true, unique = true)
    private String phone;
    @Column
    private String location;
    @Column(unique = true)
    private String email;
    @Column
    private String web;
    @Column
    private String workingDays;
    @Column
    private String workingHours;
    @Column
    private String lunch;
    @Column
    private String facebook;
    @Column
    private String instagram;
    @Column
    private LocalDateTime createdAt = LocalDateTime.now();
}
