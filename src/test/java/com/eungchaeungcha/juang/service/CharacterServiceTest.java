package com.eungchaeungcha.juang.service;

import com.eungchaeungcha.juang.common.BusinessException;
import com.eungchaeungcha.juang.common.CommonErrorCode;
import com.eungchaeungcha.juang.dto.CharacterResponseDTO;
import com.eungchaeungcha.juang.entity.CharacterEntity;
import com.eungchaeungcha.juang.repository.CharacterRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CharacterServiceTest {

    @Mock
    private CharacterRepository characterRepository;

    @InjectMocks
    private CharacterService characterService;


    @Test
    @DisplayName("character id 로 character 를 조회 테스트")
    public void findCharacterTestById() {
        //given
        CharacterEntity characterEntity = createCharacter(1L, "gam1", "#f76f6a");
        when(characterRepository.findOneById(1L)).thenReturn(Optional.of(characterEntity));

        //when
        CharacterResponseDTO response = characterService.find(1L);

        //then
        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.name()).isEqualTo("gam1");
        assertThat(response.color()).isEqualTo("#f76f6a");
    }

    @Test
    @DisplayName("존재하지 않는 character id 조회 시 예외 발생 테스트")
    public void findCharacterById_NotFound_ThrowsException() {
        // given
        when(characterRepository.findOneById(99L)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> characterService.find(99L))
                .isInstanceOf(BusinessException.class)
                .hasMessage(CommonErrorCode.CHARACTER_NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("color, name 로 character 를 조회 테스트")
    public void findCharacterByNameAndColor() {
        //given
        CharacterEntity characterEntity = createCharacter(1L, "gam1", "#f76f6a");
        when(characterRepository.findOneByNameAndColor("gam1", "#f76f6a")).thenReturn(Optional.of(characterEntity));

        //when
        CharacterResponseDTO response = characterService.find("gam1", "#f76f6a");

        //then
        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.name()).isEqualTo("gam1");
        assertThat(response.color()).isEqualTo("#f76f6a");
    }

    @Test
    @DisplayName("존재하지 않는 color, name 조회 시 예외 발생 테스트")
    public void findCharacterByNameAndColor_NotFound_ThrowsException() {
        // given
        when(characterRepository.findOneByNameAndColor("gam99", "#f76f6a"))
                .thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> characterService.find("gam99", "#f76f6a"))
                .isInstanceOf(BusinessException.class)
                .hasMessage(CommonErrorCode.CHARACTER_NOT_FOUND.getMessage());
    }

    private CharacterEntity createCharacter(Long id, String name, String color) {
        return CharacterEntity.builder()
                .id(id)
                .name(name)
                .color(color)
                .link("link")
                .build();
    }

    private List<CharacterEntity> createCharacterList(CharacterEntity characterEntity) {
        List<CharacterEntity> characterList = new ArrayList<>();
        characterList.add(characterEntity);

        return characterList;
    }
}
