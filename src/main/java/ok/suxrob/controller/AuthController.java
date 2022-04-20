package ok.suxrob.controller;

import io.swagger.annotations.*;
import ok.suxrob.dto.auth.AuthDTO;
import ok.suxrob.dto.auth.RegistrationDTO;
import ok.suxrob.dto.profileDTO.ProfileDTO;
import ok.suxrob.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Api(tags = "Auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @ApiOperation(value = "Loginning", notes = "Sign in", nickname = "searching")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = ProfileDTO.class, responseContainer = "List") })
    public ResponseEntity<ProfileDTO> login(@Valid @RequestBody AuthDTO dto) {
        ProfileDTO response = authService.authorization(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/registration")
    @ApiOperation(value = "Registration", notes = "without Token")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = ProfileDTO.class, responseContainer = "List") })
    public ResponseEntity registration(@Valid @RequestBody RegistrationDTO dto) {
        authService.registration(dto);
        return ResponseEntity.ok("Successfully!!!");
    }
}
