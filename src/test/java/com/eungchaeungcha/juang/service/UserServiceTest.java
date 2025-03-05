package com.eungchaeungcha.juang.service;

import com.eungchaeungcha.juang.common.BusinessException;
import com.eungchaeungcha.juang.common.CommonErrorCode;
import com.eungchaeungcha.juang.domain.Role;
import com.eungchaeungcha.juang.dto.UserResponseDTO;
import com.eungchaeungcha.juang.entity.UserEntity;
import com.eungchaeungcha.juang.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private FamilyService familyService;

    @InjectMocks
    private UserService userService;


    @Test
    @DisplayName("family id 로 user 리스트 조회 테스트")
    public void findUserListByFamilyId() {
        //given
        int familySize = 4;
        Long familyId = 1L;
        List<UserEntity> userList = new ArrayList<>();
        for (long i = 0; i < familySize; i++) {
            UserEntity userEntity = createUserEntity(i, familyId);
            userList.add(userEntity);
        }

        doNothing().when(familyService).exist(familyId);
        when(userRepository.findAllByFamilyId(1L)).thenReturn(userList);

        // when
        List<UserResponseDTO> response = userService.find(1L);

        // then
        assertThat(response.size()).isEqualTo(familySize);
        assertThat(response.get(0).id()).isEqualTo(0L);
        assertThat(response.get(0).familyId()).isEqualTo(familyId);
        assertThat(response.get(1).id()).isEqualTo(1L);
    }

    @Test
    @DisplayName("family id 로 user 리스트 조회 시 예외 발생 테스트")
    public void findUserListByFamilyId_empty() {
        //given
        Long familyId = 99L;
        doThrow(new BusinessException(CommonErrorCode.FAMILY_NOT_FOUND))
                .when(familyService).exist(familyId);

        // when & then
        assertThatThrownBy(() -> userService.find(familyId))
                .isInstanceOf(BusinessException.class)
                .hasMessage(CommonErrorCode.FAMILY_NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("family id 로 user 리스트 조회 시 user 가 없을 때 예외 발생")
    public void findUserListByFamilyId_userNotFound() {
        // given
        Long familyId = 1L;
        doNothing().when(familyService).exist(familyId);
        when(userRepository.findAllByFamilyId(familyId)).thenReturn(List.of());

        // when & then
        assertThatThrownBy(() -> userService.find(familyId))
                .isInstanceOf(BusinessException.class)
                .hasMessage(CommonErrorCode.USER_NOT_FOUND.getMessage());
    }

    private UserEntity createUserEntity(Long id, Long familyId) {
        return UserEntity.builder()
                .id(id)
                .username("username")
                .password("password")
                .nickName("nickName")
                .familyId(familyId)
                .characterId(1L)
                .role(Role.ROLE_USER)
                .build();
    }

}
