package com.eungchaeungcha.juang.domain;

import lombok.*;

@Builder
@Getter
public class Character{
    private Long id;
    private String name;
    private String color;
    private String link;
}

