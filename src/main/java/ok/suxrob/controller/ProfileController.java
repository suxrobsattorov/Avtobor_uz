package ok.suxrob.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ok.suxrob.dto.profileDTO.ProfileDTO;
import ok.suxrob.dto.profileDTO.ProfileUpdateDTO;
import ok.suxrob.enums.ProfileRole;
import ok.suxrob.service.ProfileService;
import ok.suxrob.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/profile")
@Api(tags = "Profile Control", value = "Bu Controllerni faqat ADMIN aka ishlata oladi")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/private")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "@ApiResponse(code = 400, message = \"...\")")
    })
    public ResponseEntity<ProfileDTO> create(@Valid @RequestBody ProfileDTO dto,
                                 HttpServletRequest request) {
        JwtUtil.getProfile(request, ProfileRole.ADMIN);
        ProfileDTO response = profileService.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/private")
    public ResponseEntity<?> getAll(HttpServletRequest request) {
        JwtUtil.getProfile(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.getAll());
    }

    @GetMapping("/private/{id}")
    public ResponseEntity<ProfileDTO> getById(@PathVariable Integer id, HttpServletRequest request) {
        JwtUtil.getProfile(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.getById(id));
    }

    @PutMapping("/private/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable("id") Integer profileId,
                                 @RequestBody ProfileUpdateDTO dto,
                                 HttpServletRequest request) {
        JwtUtil.getProfile(request);
        return ResponseEntity.ok(profileService.update(dto, profileId));
    }

    @DeleteMapping("/private/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id,
                                     HttpServletRequest request) {
        JwtUtil.getProfile(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.delete(id));
    }
//
//    @PostMapping("/filter")
//    public ResponseEntity filter(@RequestParam("page") int page,
//                                 @RequestParam("size") int size,
//                                 @RequestBody ProfileFilterDTO dto) {
//        return ResponseEntity.ok(profileService.filterSpec(page, size, dto));
//}

}
