package ok.suxrob.service;

import ok.suxrob.dto.auth.AuthDTO;
import ok.suxrob.dto.auth.RegistrationDTO;
import ok.suxrob.dto.profileDTO.ProfileDTO;
import ok.suxrob.entity.ProfileEntity;
import ok.suxrob.enums.ProfileRole;
import ok.suxrob.enums.ProfileType;
import ok.suxrob.exceptions.BadRequestException;
import ok.suxrob.repository.ProfileRepository;
import ok.suxrob.util.JwtUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private ProfileRepository profileRepository;

    public ProfileDTO authorization(AuthDTO dto) {
        String password = DigestUtils.md5Hex(dto.getPassword());
        Optional<ProfileEntity> optional = profileRepository.findByPhoneAndPassword(dto.getPhone(), password);
        if (!optional.isPresent()) {
            throw new BadRequestException("Login or Password incorrect");
        }
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setName(optional.get().getFirstName());
        profileDTO.setSurname(optional.get().getLastName());
        profileDTO.setJwt(JwtUtil.createJwt(optional.get().getId(), optional.get().getRole()));
        return profileDTO;
    }

    public void registration(RegistrationDTO dto) {
        String password = DigestUtils.md5Hex(dto.getPassword());
        String emailCheck = profileRepository.emailCheck(dto.getEmail());
        String phoneCheck = profileRepository.phoneCheck(dto.getPhone());
        if (emailCheck != null || phoneCheck != null) {
            throw new BadRequestException("Email or phone available");
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setFirstName(dto.getName());
        entity.setLastName(dto.getSurname());
        entity.setProfileType(dto.getProfileType());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setRole(ProfileRole.USER);
        if (dto.getProfileType().equals(ProfileType.COMPANY) || dto.getProfileType().equals(ProfileType.SHOWROOM)) {
            entity.setCompanyName(dto.getCompanyName());
            entity.setAboutUs(dto.getAddress());
        }
        if (dto.getConsent() != null) {
            entity.setConsent(dto.getConsent());
        }
        profileRepository.save(entity);
    }
}
