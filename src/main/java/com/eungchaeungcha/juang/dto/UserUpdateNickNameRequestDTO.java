package com.eungchaeungcha.juang.dto;

import jakarta.validation.constraints.Size;

public record UserUpdateNickNameRequestDTO (
        @Size(
                max = 9,
                message = "별명은 9자 이하로 작성해야 합니다."
        )
        String nickName
){ }
