package com.eungchaeungcha.juang.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;


@Builder
public record RegisterRequestDTO(
        @Pattern(regexp = "^[a-z0-9]{6,12}$", message = "아이디는 영어 소문자와 숫자로 이루어진 6~12자여야 합니다.")
        String username,

        @Pattern(
                regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])|(?=.*[a-zA-Z])(?=.*[!@#$%^&*])|(?=.*[0-9])(?=.*[!@#$%^&*]).{8,20}$",
                message = "비밀번호는 특수문자, 영어 대/소문자, 숫자 중 2개 이상의 조합으로 8~20자여야 합니다."
        )
        String password
) {
}
