package ok.suxrob.service;

import ok.suxrob.dto.profileDTO.ProfileDTO;
import ok.suxrob.entity.ProfileEntity;
import ok.suxrob.exceptions.BadRequestException;
import ok.suxrob.repository.ProfileRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public ProfileDTO create(ProfileDTO dto) {
        String password = DigestUtils.md5Hex(dto.getPassword());
        ProfileEntity entity = new ProfileEntity();
        entity.setFirstName(dto.getName());
        entity.setLastName(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(password);
        dto.setProfileType(dto.getProfileType());
        if (dto.getCompanyName() != null || !dto.getCompanyName().isEmpty()) {
            entity.setCompanyName(dto.getCompanyName());
        }
        if (dto.getAddress() != null || !dto.getAddress().isEmpty()) {
            entity.setCompanyName(dto.getAddress());
        }
        if (dto.getConsent() != null || dto.getConsent()) {
            entity.setCompanyName(dto.getCompanyName());
        }
        profileRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<ProfileDTO> getAll() {
        List<ProfileEntity> entityList = profileRepository.findAll();
        return entityList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ProfileDTO getById(Integer id) {
        Optional<ProfileEntity> optional = profileRepository.findById(id);
        if (optional.isPresent()) {
            throw new BadRequestException("Profile not found");
        }
        return toDTO(optional.get());
    }

    public ProfileDTO toDTO(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getFirstName());
        dto.setSurname(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setPassword(entity.getPassword());
        dto.setProfileType(entity.getProfileType());
        dto.setCompanyName(entity.getCompanyName());
        dto.setAddress(entity.getAddress());
        dto.setAboutUs(entity.getAboutUs());
        dto.setWeb(entity.getWeb());
        dto.setFacebook(entity.getFacebook());
        dto.setInstagram(entity.getInstagram());
        return dto;
    }

    public ProfileEntity get(Integer id) {
        return profileRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile not found"));
    }
}
