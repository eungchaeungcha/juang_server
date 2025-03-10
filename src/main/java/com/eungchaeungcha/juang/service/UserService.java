package com.eungchaeungcha.juang.service;

import com.eungchaeungcha.juang.common.BusinessException;
import com.eungchaeungcha.juang.common.CommonErrorCode;
import com.eungchaeungcha.juang.domain.Family;
import com.eungchaeungcha.juang.domain.User;
import com.eungchaeungcha.juang.dto.UserResponseDTO;
import com.eungchaeungcha.juang.entity.UserEntity;
import com.eungchaeungcha.juang.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FamilyService familyService;

    @Transactional
    public UserResponseDTO updateCharacter(String username, Long characterId) {

        UserEntity userEntity = find(username);

        User user = userEntity.toDomain();
        user.changeCharacter(characterId);

        userEntity.setCharacterId(user.getCharacterId());

        return UserResponseDTO.from(userEntity.toDomain());
    }

    @Transactional
    public UserResponseDTO updateNickName(String username, String nickName) {
        UserEntity userEntity = find(username);

        User user = userEntity.toDomain();
        user.changeNickName(nickName);

        userEntity.setNickName(user.getNickName());

        return UserResponseDTO.from(userEntity.toDomain());
    }

    @Transactional
    public UserResponseDTO updateFamily(String username, String code) {

        UserEntity userEntity = find(username);
        User user = userEntity.toDomain();

        Family family = familyService.find(code);

        user.changeFamily(family.getId());

        userEntity.setFamilyId(user.getFamilyId());

        return UserResponseDTO.from(userEntity.toDomain());
    }

    public UserResponseDTO findCurrentUser(UserDetails userDetails) {

        UserEntity userEntity = find(userDetails.getUsername());

        return UserResponseDTO.from(userEntity.toDomain());
    }

    public UserEntity find(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException(CommonErrorCode.USER_NOT_FOUND.name()));
    }

    public List<UserResponseDTO> find(Long familyId) {
        familyService.exist(familyId);

        List<UserEntity> userList = userRepository.findAllByFamilyId(familyId);

        if (userList.isEmpty()) {
            throw new BusinessException(CommonErrorCode.USER_NOT_FOUND);
        }

        return userList
                .stream().map(userEntity -> UserResponseDTO.from(userEntity.toDomain()))
                .collect(Collectors.toList());
    }
}
