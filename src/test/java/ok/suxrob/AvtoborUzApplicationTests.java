package ok.suxrob;

import ok.suxrob.entity.ProfileEntity;
import ok.suxrob.enums.ProfileRole;
import ok.suxrob.enums.ProfileType;
import ok.suxrob.repository.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AvtoborUzApplicationTests {

    @Autowired
    private ProfileRepository profileRepository;

    @Test
    void contextLoads() {
    }




    @Test
    void profileCreate() {
        ProfileEntity entity=new ProfileEntity();
        entity.setRole(ProfileRole.ADMIN);
        entity.setEmail("sattorov6606@gmail.com");
        entity.setProfileType(ProfileType.PRIVATE_PERSON);
        entity.setFirstName("Suxrob");
        entity.setLastName("Sattorov");
        entity.setPassword("1111");
        entity.setPhone("+998886180111");
        profileRepository.save(entity);
    }

}
