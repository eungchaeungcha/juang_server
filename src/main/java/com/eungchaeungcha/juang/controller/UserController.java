package com.eungchaeungcha.juang.controller;

import com.eungchaeungcha.juang.dto.UserResponseDTO;
import com.eungchaeungcha.juang.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/users")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_USER')")
public class UserController {

    private final UserService userService;

    @PatchMapping("/{userId}/characters/{characterId}")
    private ResponseEntity<UserResponseDTO> updateCharacter(
            @PathVariable Long userId,
            @PathVariable Long characterId
    ) {

        UserResponseDTO response = userService.updateCharacter(userId, characterId);

        return ResponseEntity.ok(response);
    }
}
