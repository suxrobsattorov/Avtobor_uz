package ok.suxrob.repository;

import ok.suxrob.entity.AnnouncementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Integer>,
        JpaSpecificationExecutor<AnnouncementEntity> {
}
