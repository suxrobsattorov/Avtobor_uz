package ok.suxrob.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ok.suxrob.dto.MakeDTO;
import ok.suxrob.dto.MakeUpdateDTO;
import ok.suxrob.service.MakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/make")
@Api(tags = "Make")
public class MakeController {

    @Autowired
    private MakeService makeService;

    @PostMapping("/private")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "@ApiResponse(code = 400, message = \"...\")")
    })
    public ResponseEntity<MakeDTO> create(@Valid @RequestBody MakeDTO dto,
                                          HttpServletRequest request) {
        MakeDTO response = makeService.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @ApiOperation(value = "Get All on page")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(makeService.getAll());
    }

    @GetMapping("/private/{id}")
    public ResponseEntity<MakeDTO> getById(@PathVariable Integer id,
                                                   HttpServletRequest request) {
        return ResponseEntity.ok(makeService.getById(id));
    }

    @PutMapping("/private/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable("id") Integer id,
                                    @RequestBody MakeUpdateDTO dto,
                                    HttpServletRequest request) {
        return ResponseEntity.ok(makeService.update(dto, id));
    }

    @DeleteMapping("/private/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id,
                                        HttpServletRequest request) {
        return ResponseEntity.ok(makeService.delete(id));
    }
}
