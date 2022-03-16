package ok.suxrob.repository;

import ok.suxrob.entity.ShoOrCoPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoOrCoPostRepository extends JpaRepository<ShoOrCoPostEntity, Integer>,
        JpaSpecificationExecutor<ShoOrCoPostEntity> {
}
