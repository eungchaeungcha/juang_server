package com.eungchaeungcha.juang.controller;

import com.eungchaeungcha.juang.dto.CharacterResponseDTO;
import com.eungchaeungcha.juang.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/characters")
@RequiredArgsConstructor

public class CharacterController {
    private final CharacterService characterService;

    @GetMapping
    private ResponseEntity<CharacterResponseDTO> findCharacter(
            @RequestParam String name,
            @RequestParam String color
    ) {

        CharacterResponseDTO response = characterService.find(name, color);

        return ResponseEntity.ok(response);
    }
}
