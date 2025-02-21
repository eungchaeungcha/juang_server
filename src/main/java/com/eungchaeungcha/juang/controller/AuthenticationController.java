package com.eungchaeungcha.juang.controller;

import com.eungchaeungcha.juang.dto.AuthenticationRequestDTO;
import com.eungchaeungcha.juang.dto.AuthenticationResponseDTO;
import com.eungchaeungcha.juang.dto.RegisterRequestDTO;
import com.eungchaeungcha.juang.dto.UsernameDuplicateResponseDTO;
import com.eungchaeungcha.juang.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @GetMapping("/{username}/duplicate")
    public ResponseEntity<UsernameDuplicateResponseDTO> usernameDuplicate(
            @PathVariable String username
    ) {
        return ResponseEntity.ok(authenticationService.checkDuplicate(username));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @Valid @RequestBody RegisterRequestDTO request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(
            @RequestBody AuthenticationRequestDTO request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
