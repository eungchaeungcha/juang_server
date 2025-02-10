package com.eungchaeungcha.juang.domain;

import lombok.*;

@Builder
public record Character(Long id, String name, String color, String link) {
}

