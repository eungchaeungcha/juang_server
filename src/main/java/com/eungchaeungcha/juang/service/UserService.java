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

        UserEntity entity = userRepository.findOneById(userId);

        User updatedUser = entity.toDomain().changeCharacter(characterId);

        entity.setCharacterId(updatedUser.characterId());

        return UserResponseDTO.from(updatedUser);
    }
}
