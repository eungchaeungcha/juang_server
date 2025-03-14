package com.eungchaeungcha.juang.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class FamilyQuestion {
    Long id;
    Long familyId;
    Long questionId;
    String questionContent;
    LocalDate openedAt;
}
