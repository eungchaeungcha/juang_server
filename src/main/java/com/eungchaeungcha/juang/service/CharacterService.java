package com.eungchaeungcha.juang.service;

import com.eungchaeungcha.juang.common.BusinessException;
import com.eungchaeungcha.juang.common.CommonErrorCode;
import com.eungchaeungcha.juang.domain.Character;
import com.eungchaeungcha.juang.dto.CharacterResponseDTO;
import com.eungchaeungcha.juang.entity.CharacterEntity;
import com.eungchaeungcha.juang.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;

    @Transactional(readOnly = true)
    public CharacterResponseDTO find(Long characterId) {
        CharacterEntity entity = characterRepository.findOneById(characterId)
                .orElseThrow(() -> new BusinessException(CommonErrorCode.CHARACTER_NOT_FOUND));

        Character character = entity.toDomain();

        return CharacterResponseDTO.from(character);
    }

    @Transactional(readOnly = true)
    public CharacterResponseDTO find(String name, String color) {

        CharacterEntity entity = characterRepository.findOneByNameAndColor(name, color)
                .orElseThrow(() -> new BusinessException(CommonErrorCode.CHARACTER_NOT_FOUND));

        Character character = entity.toDomain();

        return CharacterResponseDTO.from(character);
    }
}
