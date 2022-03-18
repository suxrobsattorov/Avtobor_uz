package ok.suxrob.service;

import ok.suxrob.dto.announcement.AnnouncementDTO;
import ok.suxrob.dto.announcement.AnnouncementFilterDTO;
import ok.suxrob.dto.announcement.AnnouncementUpdateDTO;
import ok.suxrob.entity.AnnouncementEntity;
import ok.suxrob.entity.MakeEntity;
import ok.suxrob.entity.ProfileEntity;
import ok.suxrob.enums.announcement.AnnouncementSort;
import ok.suxrob.exceptions.BadRequestException;
import ok.suxrob.repository.AnnouncementRepository;
import ok.suxrob.specification.AnnouncementSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private MakeService makeService;

    public AnnouncementDTO create(AnnouncementDTO dto) {
        ProfileEntity profileEntity = profileService.get(dto.getProfileId());
        MakeEntity makeEntity = makeService.get(dto.getMakeId());
        AnnouncementEntity entity = new AnnouncementEntity();
        entity.setName(dto.getName());
        entity.setAvtoType(dto.getAvtoType());
        entity.setConsent(dto.getConsent());
        entity.setContact(dto.getContact());
        entity.setDescription(dto.getDescription());
        entity.setIHave(dto.getIHave());
        entity.setIWantTo(dto.getIWantTo());
        entity.setProfile(profileEntity);
        entity.setPaymentType(dto.getPaymentType());
        entity.setRegion(dto.getRegion());
        entity.setPriceType(dto.getPriceType());
        entity.setPrice(dto.getPrice());
        entity.setMake(makeEntity);
        entity.setYear(dto.getYear());
        entity.setTransmission(dto.getTransmission());
        entity.setCondition(dto.getCondition());
        entity.setWheelDrive(dto.getWheelDrive());
        entity.setSeatsCount(dto.getSeatsCount());
        entity.setBodyColor(dto.getBodyColor());
        entity.setEngineSize(dto.getEngineSize());
        entity.setTireSize(dto.getTireSize());
        entity.setMileAge(dto.getMileAge());
        entity.setHorsePower(dto.getHorsePower());
        entity.setFuelType(dto.getFuelType());
        entity.setABS(dto.getABS());
        entity.setAirbags(dto.getAirbags());
        entity.setAntiSlipRegulator(dto.getAntiSlipRegulator());
        entity.setAudioSystem(dto.getAudioSystem());
        entity.setCruiseControl(dto.getCruiseControl());
        entity.setGasEquipment(dto.getGasEquipment());
        entity.setHeatedSeats(dto.getHeatedSeats());
        entity.setParktronic(dto.getParktronic());
        entity.setPowerWindows(dto.getPowerWindows());
        entity.setSunroof(dto.getSunroof());
        entity.setAirCondition(dto.getAirCondition());
        entity.setAlloyWheels(dto.getAlloyWheels());
        entity.setArcs(dto.getArcs());
        entity.setCentralLocking(dto.getCentralLocking());
        entity.setFogLights(dto.getFogLights());
        entity.setHeatedMirrors(dto.getHeatedMirrors());
        entity.setNavigationSystem(dto.getNavigationSystem());
        entity.setPowerSteering(dto.getPowerSteering());
        entity.setSignaling(dto.getSignaling());
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
        MakeEntity makeEntity = makeService.get(dto.getMakeId());
        AnnouncementEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setAvtoType(dto.getAvtoType());
        entity.setConsent(dto.getConsent());
        entity.setContact(dto.getContact());
        entity.setDescription(dto.getDescription());
        entity.setIHave(dto.getIHave());
        entity.setIWantTo(dto.getIWantTo());
        entity.setMake(makeEntity);
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

    public Page<AnnouncementDTO> filterSpecification(Pageable pageable, AnnouncementFilterDTO dto) {
        MakeEntity make = null;
        if (dto.getMakeId() != null) {
            make = makeService.get(dto.getMakeId());
        }

        pageable.getSortOr(Sort.by(Sort.Direction.DESC, "createdAt"));
        Specification<AnnouncementEntity> specification =
                Specification.where(AnnouncementSpecification.idIsNotNull("id"));
        if (dto.getId() != null) {
            specification.and(AnnouncementSpecification.equal("id", dto.getId()));
        }
        if (dto.getYear() != null) {
            specification.and(AnnouncementSpecification.equal("year", dto.getYear()));
        }
        if (dto.getTransmission() != null) {
            specification.and(AnnouncementSpecification.transmission(dto.getTransmission()));
        }
        if (dto.getMileage() != null) {
            specification.and(AnnouncementSpecification.equal("mileage", dto.getMileage()));
        }
        if (dto.getPrice() != null) {
            specification.and(AnnouncementSpecification.price(dto.getPrice()));
        }
        if (dto.getBodyColor() != null) {
            specification.and(AnnouncementSpecification.bodyColor(dto.getBodyColor()));
        }
        if (dto.getRegion() != null) {
            specification.and(AnnouncementSpecification.region(dto.getRegion()));
        }

        return announcementRepository.findAll(specification, pageable).map(this::toDTO);
    }

    public PageImpl<AnnouncementDTO> getSortAll(int page, int size, AnnouncementSort sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");

        if (sort.equals(AnnouncementSort.NEWER_FIRST)) {
            pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        }
        if (sort.equals(AnnouncementSort.OLDER_FIRST)) {
            pageable = PageRequest.of(page, size, Sort.Direction.ASC, "createdAt");
        }
        if (sort.equals(AnnouncementSort.EXPENSIVE_FIRST)) {
            pageable = PageRequest.of(page, size, Sort.Direction.ASC, "price");
        }
        if (sort.equals(AnnouncementSort.CHEAPER_FIRST)) {
            pageable = PageRequest.of(page, size, Sort.Direction.DESC, "price");
        }

        Page<AnnouncementEntity> entityPage = announcementRepository.findAll(pageable);
        List<AnnouncementDTO> dtos = entityPage.getContent().stream()
                .map(this::toDTO).collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, entityPage.getTotalElements());
    }

    public AnnouncementDTO toDTO(AnnouncementEntity entity) {
        AnnouncementDTO dto = new AnnouncementDTO();
        dto.setConsent(entity.getConsent());
        dto.setContact(entity.getContact());
        dto.setDescription(entity.getDescription());
        dto.setAvtoType(entity.getAvtoType());
        dto.setIHave(entity.getIHave());
        dto.setMakeId(entity.getMake().getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setRegion(entity.getRegion());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setPaymentType(entity.getPaymentType());
        dto.setIWantTo(entity.getIWantTo());
        dto.setId(entity.getId());
        dto.setPriceType(entity.getPriceType());
        dto.setYear(entity.getYear());
        dto.setTransmission(entity.getTransmission());
        dto.setCondition(entity.getCondition());
        dto.setWheelDrive(entity.getWheelDrive());
        dto.setSeatsCount(entity.getSeatsCount());
        dto.setBodyColor(entity.getBodyColor());
        dto.setEngineSize(entity.getEngineSize());
        dto.setTireSize(entity.getTireSize());
        dto.setMileAge(entity.getMileAge());
        dto.setHorsePower(entity.getHorsePower());
        dto.setFuelType(entity.getFuelType());
        dto.setABS(entity.getABS());
        dto.setAirbags(entity.getAirbags());
        dto.setAntiSlipRegulator(entity.getAntiSlipRegulator());
        dto.setAudioSystem(entity.getAudioSystem());
        dto.setCruiseControl(entity.getCruiseControl());
        dto.setGasEquipment(entity.getGasEquipment());
        dto.setHeatedSeats(entity.getHeatedSeats());
        dto.setParktronic(entity.getParktronic());
        dto.setPowerWindows(entity.getPowerWindows());
        dto.setSunroof(entity.getSunroof());
        dto.setAirCondition(entity.getAirCondition());
        dto.setAlloyWheels(entity.getAlloyWheels());
        dto.setArcs(entity.getArcs());
        dto.setCentralLocking(entity.getCentralLocking());
        dto.setFogLights(entity.getFogLights());
        dto.setHeatedMirrors(entity.getHeatedMirrors());
        dto.setNavigationSystem(entity.getNavigationSystem());
        dto.setPowerSteering(entity.getPowerSteering());
        dto.setSignaling(entity.getSignaling());
        return dto;

    }

    public AnnouncementEntity get(Integer id) {
        return announcementRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Announcement not found"));
    }
}
