package com.eungchaeungcha.juang.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Family{
    private Long id;
    private String code;
    private String name;

    public static Family create(String name, String code) {
        return Family.builder()
                .id(null)
                .code(code)
                .name(name)
                .build();
    }
}