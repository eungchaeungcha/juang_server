package com.eungchaeungcha.juang.service;

import com.eungchaeungcha.juang.domain.User;
import com.eungchaeungcha.juang.dto.UserResponseDTO;
import com.eungchaeungcha.juang.entity.UserEntity;
import com.eungchaeungcha.juang.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponseDTO updateCharacter(Long userId, Long characterId) {

        UserEntity userEntity = find(username);
        User user = userEntity.toDomain();

        Family family = familyService.find(code);

        user.changeFamily(family.getId());

        userEntity.setFamilyId(user.getFamilyId());

        return UserResponseDTO.from(userEntity.toDomain());
    }

    public UserEntity find(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException(CommonErrorCode.USER_NOT_FOUND.name()));
    }
}
