package ok.suxrob.service;

import ok.suxrob.dto.MakeDTO;
import ok.suxrob.dto.MakeUpdateDTO;
import ok.suxrob.entity.MakeEntity;
import ok.suxrob.exceptions.BadRequestException;
import ok.suxrob.repository.MakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MakeService {

    @Autowired
    private MakeRepository makeRepository;

    public MakeDTO create(MakeDTO dto) {
        MakeEntity entity = new MakeEntity();
        entity.setName(dto.getName());
        entity.setParentId(dto.getParentId());
        entity.setMainMake(dto.getMainMake());
        makeRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<MakeDTO> getAll() {
        List<MakeEntity> entityList = makeRepository.findAll();
        return entityList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public MakeDTO getById(Integer id) {
        MakeEntity entity = get(id);
        return toDTO(entity);
    }

    public boolean update(MakeUpdateDTO dto, Integer id) {
        MakeEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setParentId(dto.getParentId());
        entity.setMainMake(dto.getMainMake());
        makeRepository.save(entity);
        return true;
    }

    public boolean delete(Integer id) {
        MakeEntity entity = get(id);
        makeRepository.delete(entity);
        return true;
    }

    public MakeDTO toDTO(MakeEntity entity) {
        MakeDTO dto = new MakeDTO();
        dto.setParentId(entity.getParentId());
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setMainMake(entity.getMainMake());
        return dto;
    }

    public MakeEntity get(Integer id) {
        return makeRepository.findById(id).orElseThrow(() -> new BadRequestException("Make not found"));
    }
}
