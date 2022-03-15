package ok.suxrob.repository;

import ok.suxrob.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {

    Optional<ProfileEntity> findByPhoneAndPassword(String phone, String password);

    @Query("select p.email from ProfileEntity p where p.email=?1")
    String emailCheck(String email);

    @Query("select p.phone from ProfileEntity p where p.phone=?1")
    String phoneCheck(String phone);
}
