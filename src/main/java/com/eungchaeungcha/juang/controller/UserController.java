package com.eungchaeungcha.juang.controller;

import com.eungchaeungcha.juang.dto.UserResponseDTO;
import com.eungchaeungcha.juang.dto.UserUpdateFamilyRequestDTO;
import com.eungchaeungcha.juang.dto.UserUpdateNickNameRequestDTO;
import com.eungchaeungcha.juang.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_USER')")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    private ResponseEntity<UserResponseDTO> get(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        UserResponseDTO response = userService.findCurrentUser(userDetails);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/characters/{characterId}")
    private ResponseEntity<UserResponseDTO> updateCharacter(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long characterId
    ) {
        String username = userDetails.getUsername();

        UserResponseDTO response = userService.updateCharacter(username, characterId);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/nick-name")
    private ResponseEntity<UserResponseDTO> updateNickName(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UserUpdateNickNameRequestDTO request
    ) {
        String username = userDetails.getUsername();

        UserResponseDTO response = userService.updateNickName(username, request.nickName());

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/families")
    private ResponseEntity<UserResponseDTO> updateFamily(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UserUpdateFamilyRequestDTO request
    ) {
        String username = userDetails.getUsername();

        UserResponseDTO response = userService.updateFamily(username, request.code());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/families/{familyId}")
    private ResponseEntity<List<UserResponseDTO>> get(
            @PathVariable Long familyId
    ) {

        List<UserResponseDTO> response = userService.find(familyId);

        return ResponseEntity.ok(response);

    }
}
