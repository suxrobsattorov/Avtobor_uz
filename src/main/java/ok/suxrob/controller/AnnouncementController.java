package ok.suxrob.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ok.suxrob.dto.AnnouncementDTO;
import ok.suxrob.dto.AnnouncementUpdateDTO;
import ok.suxrob.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/announcement")
@Api(tags = "Announcement Post")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @PostMapping("/private")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "@ApiResponse(code = 400, message = \"...\")")
    })
    public ResponseEntity<AnnouncementDTO> create(@Valid @RequestBody AnnouncementDTO dto,
                                                 HttpServletRequest request) {
        AnnouncementDTO response = announcementService.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @ApiOperation(value = "Get All on page")
    public ResponseEntity<?> getAll(@RequestParam("page") int page,
                                    @RequestParam("size") int size){
        return ResponseEntity.ok(announcementService.getAll(page, size));
    }

    @GetMapping("/private/{id}")
    public ResponseEntity<AnnouncementDTO> getById(@PathVariable Integer id,
                                                  HttpServletRequest request) {
        return ResponseEntity.ok(announcementService.getById(id));
    }

    @PutMapping("/private/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable("id") Integer id,
                                    @RequestBody AnnouncementUpdateDTO dto,
                                    HttpServletRequest request) {
        return ResponseEntity.ok(announcementService.update(dto, id));
    }

    @DeleteMapping("/private/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id,
                                        HttpServletRequest request) {
        return ResponseEntity.ok(announcementService.delete(id));
    }
}
