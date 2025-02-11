package com.eungchaeungcha.juang.service;

import com.eungchaeungcha.juang.common.CommonErrorCode;
import com.eungchaeungcha.juang.domain.Family;
import com.eungchaeungcha.juang.dto.FamilyRequestDTO;
import com.eungchaeungcha.juang.dto.FamilyResponseDTO;
import com.eungchaeungcha.juang.entity.FamilyEntity;
import com.eungchaeungcha.juang.repository.FamilyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class FamilyService {

    private final FamilyRepository familyRepository;
    private static final int CODE_LENGTH = 6;
    private static final String CODE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();

    @Transactional
    public FamilyResponseDTO create(FamilyRequestDTO request) {

        String code = generateUniqueCode();

        Family family = Family.create(request.name(), code);

        FamilyEntity savedFamilyEntity = familyRepository.save(FamilyEntity.from(family));

        return FamilyResponseDTO.from(savedFamilyEntity);
    }

    public Family find(String code) {
        FamilyEntity familyEntity = familyRepository.findOneByCode(code)
                .orElseThrow(() -> new RuntimeException(CommonErrorCode.FAMILY_NOT_FOUND.name()));

        return familyEntity.toDomain();
    }

    private String generateUniqueCode() {
        String newCode;

        do {
            newCode = generateRandomCode();
        } while (familyRepository.existsByCode(newCode));

        return newCode;
    }

    private String generateRandomCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = random.nextInt(CODE_CHARACTERS.length());
            code.append(CODE_CHARACTERS.charAt(index));
        }
        return code.toString();
    }
}
