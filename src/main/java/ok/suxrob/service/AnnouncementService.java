package ok.suxrob.service;

import ok.suxrob.dto.AnnouncementDTO;
import ok.suxrob.dto.AnnouncementUpdateDTO;
import ok.suxrob.entity.AnnouncementEntity;
import ok.suxrob.exceptions.BadRequestException;
import ok.suxrob.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    public AnnouncementDTO create(AnnouncementDTO dto) {
        AnnouncementEntity entity = new AnnouncementEntity();
        entity.setName(dto.getName());
        entity.setConditions(dto.getConditions());
        entity.setAvtoType(dto.getAvtoType());
        entity.setConsent(dto.getConsent());
        entity.setContact(dto.getContact());
        entity.setDescription(dto.getDescription());
        entity.setIHave(dto.getIHave());
        entity.setIWantTo(dto.getIWantTo());
        entity.setParameters(dto.getParameters());
        entity.setMake(dto.getMake());
        entity.setPaymentType(dto.getPaymentType());
        entity.setRegion(dto.getRegion());
        entity.setPriceType(dto.getPriceType());
        entity.setPrice(dto.getPrice());
        announcementRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public PageImpl<AnnouncementDTO> getAll(int pageNum, int size) {
        Pageable pageable = PageRequest.of(pageNum, size, Sort.Direction.DESC, "createdAt");
        Page<AnnouncementEntity> page = announcementRepository.findAll(pageable);
        List<AnnouncementDTO> dtoList = page.getContent().stream()
                .map(this::toDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, page.getTotalElements());
    }

    public AnnouncementDTO getById(Integer id) {
        AnnouncementEntity entity = get(id);
        return toDTO(entity);
    }

    public boolean update(AnnouncementUpdateDTO dto, Integer id) {
        AnnouncementEntity entity=get(id);
        entity.setConditions(dto.getConditions());
        entity.setName(dto.getName());
        entity.setAvtoType(dto.getAvtoType());
        entity.setConsent(dto.getConsent());
        entity.setContact(dto.getContact());
        entity.setDescription(dto.getDescription());
        entity.setIHave(dto.getIHave());
        entity.setIWantTo(dto.getIWantTo());
        entity.setParameters(dto.getParameters());
        entity.setMake(dto.getMake());
        entity.setPaymentType(dto.getPaymentType());
        entity.setRegion(dto.getRegion());
        entity.setPriceType(dto.getPriceType());
        entity.setPrice(dto.getPrice());
        announcementRepository.save(entity);
        return true;
    }

    public boolean delete(Integer id) {
        AnnouncementEntity entity = get(id);
        announcementRepository.delete(entity);
        return true;
    }

    public AnnouncementDTO toDTO(AnnouncementEntity entity) {
        AnnouncementDTO dto = new AnnouncementDTO();
        dto.setConditions(entity.getConditions());
        dto.setConsent(entity.getConsent());
        dto.setContact(entity.getContact());
        dto.setDescription(entity.getDescription());
        dto.setAvtoType(entity.getAvtoType());
        dto.setIHave(entity.getIHave());
        dto.setMake(entity.getMake());
        dto.setName(entity.getName());
        dto.setParameters(entity.getParameters());
        dto.setPrice(entity.getPrice());
        dto.setRegion(entity.getRegion());
        dto.setPaymentType(entity.getPaymentType());
        dto.setIWantTo(entity.getIWantTo());
        dto.setId(entity.getId());
        dto.setPriceType(entity.getPriceType());
        return dto;

    }

    public AnnouncementEntity get(Integer id) {
        return announcementRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Announcement not found"));
    }
}
