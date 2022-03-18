package ok.suxrob.repository;

import ok.suxrob.entity.MakeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakeRepository extends JpaRepository<MakeEntity, Integer> {
}
