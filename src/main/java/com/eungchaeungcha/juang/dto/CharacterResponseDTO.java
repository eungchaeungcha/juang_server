package com.eungchaeungcha.juang.dto;

import com.eungchaeungcha.juang.domain.Character;

public record CharacterResponseDTO(
        Long id,
        String name,
        String color,
        String link
) {
    public static CharacterResponseDTO from(Character character) {
        return new CharacterResponseDTO(
                character.getId(),
                character.getName(),
                character.getColor(),
                character.getLink()
        );
    }
}
