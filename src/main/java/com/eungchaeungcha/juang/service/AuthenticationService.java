package com.eungchaeungcha.juang.service;

import com.eungchaeungcha.juang.common.CommonErrorCode;
import com.eungchaeungcha.juang.common.BusinessException;
import com.eungchaeungcha.juang.config.JwtService;
import com.eungchaeungcha.juang.domain.Role;
import com.eungchaeungcha.juang.dto.UsernameDuplicateResponseDTO;
import com.eungchaeungcha.juang.entity.UserEntity;
import com.eungchaeungcha.juang.dto.AuthenticationRequestDTO;
import com.eungchaeungcha.juang.dto.AuthenticationResponseDTO;
import com.eungchaeungcha.juang.dto.RegisterRequestDTO;
import com.eungchaeungcha.juang.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UsernameDuplicateResponseDTO checkDuplicate(String username) {
        return UsernameDuplicateResponseDTO.builder()
                .duplicate(userRepository.existsByUsername(username))
                .build();
    }

    @Transactional
    public AuthenticationResponseDTO register(RegisterRequestDTO request) {

        if(checkUsernameDuplicate(request.username())){
            throw new BusinessException(CommonErrorCode.ALREADY_EXISTS_USERNAME);
        }

        userRepository.findByUsername(request.username());
        var user = UserEntity.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        var user = userRepository.findByUsername(request.username()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

    private boolean checkUsernameDuplicate(String username) {
        return userRepository.existsByUsername(username);
    }

}
