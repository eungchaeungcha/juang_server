package com.eungchaeungcha.juang.controller;


import com.eungchaeungcha.juang.TestSecurityConfig;
import com.eungchaeungcha.juang.common.CommonErrorCode;
import com.eungchaeungcha.juang.config.JwtService;
import com.eungchaeungcha.juang.dto.AuthenticationResponseDTO;
import com.eungchaeungcha.juang.dto.RegisterRequestDTO;
import com.eungchaeungcha.juang.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = AuthenticationController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@Import(TestSecurityConfig.class)
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AuthenticationService authenticationService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private AuthenticationProvider authenticationProvider;


    @Test
    @DisplayName("회원가입 성공 - 유효한 아이디와 비밀번호")
    void register_success() throws Exception {
        //given
        RegisterRequestDTO request = RegisterRequestDTO.builder().username("testu1").password("Password123!").build();

        AuthenticationResponseDTO response = AuthenticationResponseDTO.builder().token("success token").build();

        //when
        when(authenticationService.register(any(RegisterRequestDTO.class))).thenReturn(response);        // /then

        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(response.token()));
    }

    @Test
    @DisplayName("회원가입 실패 - 아이디 6자 미만")
    void register_fail_lessUsernameLength() throws Exception {
        // given
        RegisterRequestDTO request = new RegisterRequestDTO("12345", "Password123!");

        //when/then
        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(CommonErrorCode.INVALID_PARAMETER.getMessage()))
                .andExpect(jsonPath("$.errors[0].field").value("username"));
    }

    @Test
    @DisplayName("회원가입 실패 - 아이디 12자 초과")
    void register_fail_overUsernameLength() throws Exception {
        // given
        RegisterRequestDTO request = new RegisterRequestDTO("1234567891234", "Password123!");

        //when/then
        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(CommonErrorCode.INVALID_PARAMETER.getMessage()))
                .andExpect(jsonPath("$.errors[0].field").value("username"));
    }

    @Test
    @DisplayName("회원가입 실패 - 아이디 형식 오류")
    void register_fail_invalidUsername() throws Exception {
        // given
        RegisterRequestDTO request = new RegisterRequestDTO("TestUser!", "Password123!");

        //when/then
        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(CommonErrorCode.INVALID_PARAMETER.getMessage()))
                .andExpect(jsonPath("$.errors[0].field").value("username"))
                .andExpect(jsonPath("$.errors[0].message").value("아이디는 영어 소문자와 숫자로 이루어진 6~12자여야 합니다."));
    }

    @Test
    @DisplayName("회원가입 실패 - 비밀번호 길이 8자 미만")
    void register_fail_lessPasswordLength() throws Exception {
        //given
        RegisterRequestDTO request = new RegisterRequestDTO("validuser1", "acdefg");

        // when/then
        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(CommonErrorCode.INVALID_PARAMETER.getMessage()))
                .andExpect(jsonPath("$.errors[0].field").value("password"));
    }

    @Test
    @DisplayName("회원가입 실패 - 비밀번호 길이 20자 초과")
    void register_fail_overPasswordLength() throws Exception {
        //given
        RegisterRequestDTO request = new RegisterRequestDTO("validuser1", "123456789012345678901234");

        // when/then
        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(CommonErrorCode.INVALID_PARAMETER.getMessage()))
                .andExpect(jsonPath("$.errors[0].field").value("password"));
    }

    @Test
    @DisplayName("회원가입 실패 - 비밀번호 형식 오류")
    void register_fail_invalidPassword() throws Exception {
        // given
        RegisterRequestDTO request = new RegisterRequestDTO("validuser1", "abcdefgefefefefefe");

        // when/then
        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(CommonErrorCode.INVALID_PARAMETER.getMessage()))
                .andExpect(jsonPath("$.errors[0].field").value("password"))
                .andExpect(jsonPath("$.errors[0].message").value("비밀번호는 특수문자, 영어 대/소문자, 숫자 중 2개 이상의 조합으로 8~20자여야 합니다."));
    }
}
