package ok.suxrob.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ok.suxrob.dto.ShoOrCoPostDTO;
import ok.suxrob.dto.ShoOrCoPostUpdateDTO;
import ok.suxrob.enums.ProfileRole;
import ok.suxrob.service.ShoOrCoPostServise;
import ok.suxrob.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/ShoOrCoPost")
@Api(tags = "Showroom or Company Post", value = "Bu Controllerni faqat ADMIN aka ishlata oladi")
public class ShoOrCoPostController {

    @Autowired
    private ShoOrCoPostServise shoOrCoPostServise;

    @PostMapping("/private")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "@ApiResponse(code = 400, message = \"...\")")
    })
    public ResponseEntity<ShoOrCoPostDTO> create(@Valid @RequestBody ShoOrCoPostDTO dto,
                                                 HttpServletRequest request) {
        JwtUtil.getProfile(request, ProfileRole.ADMIN);
        ShoOrCoPostDTO response = shoOrCoPostServise.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @ApiOperation(value = "Get All on page")
    public ResponseEntity<?> getAll(@RequestParam("page") int page,
                                 @RequestParam("size") int size){
        return ResponseEntity.ok(shoOrCoPostServise.getAll(page, size));
    }

    @GetMapping("/private/{id}")
    public ResponseEntity<ShoOrCoPostDTO> getById(@PathVariable Integer id,
                                                  HttpServletRequest request) {
        JwtUtil.getProfile(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(shoOrCoPostServise.getById(id));
    }

    @PutMapping("/private/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable("id") Integer id,
                                    @RequestBody ShoOrCoPostUpdateDTO dto,
                                    HttpServletRequest request) {
        JwtUtil.getProfile(request);
        return ResponseEntity.ok(shoOrCoPostServise.update(dto, id));
    }

    @DeleteMapping("/private/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id,
                                        HttpServletRequest request) {
        JwtUtil.getProfile(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(shoOrCoPostServise.delete(id));
    }
}
