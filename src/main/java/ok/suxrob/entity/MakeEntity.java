package ok.suxrob.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "make")
public class MakeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column
    private Integer parentId;
    @Column
    private Integer mainMake;

    @OneToMany(mappedBy = "make")
    private List<AnnouncementEntity> announcementEntityList;
}
