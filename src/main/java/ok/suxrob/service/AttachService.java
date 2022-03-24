package ok.suxrob.service;

import ok.suxrob.dto.AttachDTO;
import ok.suxrob.entity.AttachEntity;
import ok.suxrob.exceptions.BadRequestException;
import ok.suxrob.exceptions.ItemNotFoundException;
import ok.suxrob.repository.AttachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttachService {

    @Autowired
    private AttachRepository attachRepository;

    @Value("${attach.upload.folder}")
    private String uploadFolder; // uploads
    @Value("${attach.open.url}")
    private String attachOpenUrl;

    public AttachDTO saveFile(MultipartFile file) {

        String filePath = getYmDString();
        String key = UUID.randomUUID().toString();
        String extension = getExtension(file.getOriginalFilename());

        File folder = new File(uploadFolder + "/" + filePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFolder + "/" + filePath + "/" + key + "." + extension);
            Files.write(path, bytes);

            AttachEntity entity = new AttachEntity();
            entity.setKey(key);
            entity.setExtension(extension);
            entity.setOriginName(file.getOriginalFilename());
            entity.setSize(file.getSize());
            entity.setFilePath(filePath);
            attachRepository.save(entity);
            return toDTO(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new BadRequestException("File cannot be null");
    }

    public static String getYmDString() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DATE);

        return year + "/" + month + "/" + day + "/";
    }

    public String getExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }

    public void delete(String key) {
        Optional<AttachEntity> optional = attachRepository.findByKey(key);
        if (!optional.isPresent()) {
            return;
        }
        String filePath = optional.get().getFilePath() + "/" + key + "." + optional.get().getExtension();
        File file = new File(uploadFolder + "/" + filePath);
        if (file.exists()) {
            file.delete();
        }
        attachRepository.delete(optional.get());
    }

    public AttachDTO toDTO(AttachEntity entity) {
        AttachDTO dto = new AttachDTO();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        dto.setExtension(entity.getExtension());
        dto.setOriginName(entity.getOriginName());
        dto.setSize(dto.getSize());
        dto.setFilePath(entity.getFilePath());
        dto.setCreatedDate(entity.getCreatedAt());
        dto.setUrl(attachOpenUrl + "/" + entity.getKey());
        return dto;
    }

    public AttachEntity get(Integer id) {
        return attachRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Attach not found"));
    }
}
