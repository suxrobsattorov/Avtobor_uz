package ok.suxrob.entity;

import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "attach")
public class AttachEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String key;
    @Column
    private String originName;
    @Column
    private Long size;
    @Column
    private String filePath;
    @Column
    private String extension;
    @Column
    private LocalDateTime createdAt=LocalDateTime.now();
}
