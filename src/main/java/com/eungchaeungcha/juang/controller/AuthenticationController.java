package com.eungchaeungcha.juang.controller;

import com.eungchaeungcha.juang.dto.AuthenticationRequestDTO;
import com.eungchaeungcha.juang.dto.AuthenticationResponseDTO;
import com.eungchaeungcha.juang.dto.RegisterRequestDTO;
import com.eungchaeungcha.juang.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @RequestBody RegisterRequestDTO request
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
