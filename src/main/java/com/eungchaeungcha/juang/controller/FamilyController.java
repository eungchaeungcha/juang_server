package com.eungchaeungcha.juang.controller;

import com.eungchaeungcha.juang.dto.FamilyRequestDTO;
import com.eungchaeungcha.juang.dto.FamilyResponseDTO;
import com.eungchaeungcha.juang.service.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/families")
@RequiredArgsConstructor
public class FamilyController {

    private final FamilyService familyService;

    @PostMapping
    public ResponseEntity<FamilyResponseDTO> create(
            @RequestBody FamilyRequestDTO request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        FamilyResponseDTO response = familyService.create(request);

        return ResponseEntity.ok(response);
    }
}
