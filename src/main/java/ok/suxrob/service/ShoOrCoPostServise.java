package ok.suxrob.service;

import ok.suxrob.dto.ShoOrCoPostDTO;
import ok.suxrob.dto.ShoOrCoPostUpdateDTO;
import ok.suxrob.entity.ShoOrCoPostEntity;
import ok.suxrob.exceptions.BadRequestException;
import ok.suxrob.repository.ShoOrCoPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.SheetCollate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoOrCoPostServise {

    @Autowired
    private ShoOrCoPostRepository shoOrCoPostRepository;

    public ShoOrCoPostDTO create(ShoOrCoPostDTO dto) {
        ShoOrCoPostEntity entity = new ShoOrCoPostEntity();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setType(dto.getType());
        entity.setEmail(dto.getEmail());
        entity.setFacebook(dto.getFacebook());
        entity.setInstagram(dto.getInstagram());
        entity.setLocation(dto.getLocation());
        entity.setLunch(dto.getLunch());
        entity.setPhone(dto.getPhone());
        entity.setWeb(dto.getWeb());
        entity.setWorkingDays(dto.getWorkingDays());
        entity.setWorkingHours(dto.getWorkingHours());

        shoOrCoPostRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public PageImpl<ShoOrCoPostDTO> getAll(int pageNum, int size) {
        Pageable pageable = PageRequest.of(pageNum, size, Sort.Direction.DESC, "createdAt");
        Page<ShoOrCoPostEntity> page = shoOrCoPostRepository.findAll(pageable);
        List<ShoOrCoPostDTO> dtoList = page.getContent().stream()
                .map(this::toDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, page.getTotalElements());
    }

    public ShoOrCoPostDTO getById(Integer id) {
        ShoOrCoPostEntity entity = get(id);
        return toDTO(entity);
    }

    public boolean update(ShoOrCoPostUpdateDTO dto, Integer id) {
        ShoOrCoPostEntity entity = get(id);
        entity.setWorkingHours(dto.getWorkingHours());
        entity.setWeb(dto.getWeb());
        entity.setLocation(dto.getLocation());
        entity.setLunch(dto.getLunch());
        entity.setPhone(dto.getPhone());
        entity.setInstagram(dto.getInstagram());
        entity.setFacebook(dto.getFacebook());
        entity.setEmail(dto.getEmail());
        entity.setWorkingDays(dto.getWorkingDays());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        shoOrCoPostRepository.save(entity);
        return true;
    }

    public boolean delete(Integer id) {
        ShoOrCoPostEntity entity = get(id);
        shoOrCoPostRepository.delete(entity);
        return true;
    }

    public ShoOrCoPostDTO toDTO(ShoOrCoPostEntity entity) {
        ShoOrCoPostDTO dto = new ShoOrCoPostDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setEmail(entity.getEmail());
        dto.setFacebook(entity.getFacebook());
        dto.setInstagram(entity.getInstagram());
        dto.setLocation(entity.getLocation());
        dto.setLunch(entity.getLunch());
        dto.setPhone(entity.getPhone());
        dto.setTitle(entity.getTitle());
        dto.setType(entity.getType());
        dto.setWeb(entity.getWeb());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setWorkingDays(entity.getWorkingDays());
        dto.setWorkingHours(entity.getWorkingHours());
        return dto;
    }

    public ShoOrCoPostEntity get(Integer id) {
        return shoOrCoPostRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("ShoOrCoPost not found"));
    }
}
