package com.eungchaeungcha.juang.service;

import com.eungchaeungcha.juang.common.BusinessException;
import com.eungchaeungcha.juang.common.CommonErrorCode;
import com.eungchaeungcha.juang.dto.FamilyQuestionResponseDTO;
import com.eungchaeungcha.juang.entity.FamilyQuestionEntity;
import com.eungchaeungcha.juang.entity.QuestionEntity;
import com.eungchaeungcha.juang.repository.FamilyQuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FamilyQuestionServiceTest {

    @Mock
    private FamilyQuestionRepository familyQuestionRepository;

    @Mock
    private FamilyService familyService;

    @InjectMocks
    private FamilyQuestionService familyQuestionService;

    @Test
    @DisplayName("family 에게 할당된 질문 리스트 조회 테스트")
    public void findFamilyQuestionListByFamilyId() {
        // given
        Long familyId = 1L;
        List<FamilyQuestionEntity> list = new ArrayList<>();
        for (long i = 0L; i < 5; i++) {
            QuestionEntity questionEntity = createQuestionEntity(i, "content" + i);
            FamilyQuestionEntity familyQuestionEntity = createFamilyQuestionEntity(i, familyId, questionEntity.getId(), questionEntity.getContent());
            list.add(familyQuestionEntity);
        }
        doNothing().when(familyService).exist(familyId);
        when(familyQuestionRepository.findAllByFamilyIdOrderByOpenedAtDesc(familyId)).thenReturn(list);

        // when
        List<FamilyQuestionResponseDTO> response = familyQuestionService.find(familyId);


        // then
        assertThat(response.size()).isEqualTo(5);
        assertThat(response.get(0).questionId()).isEqualTo(0L);
    }

    @Test
    @DisplayName("family 에게 할당된 질문 리스트 조회 시 예외 발생 테스트")
    public void findFamilyQuestionListByFamilyId_NotFound_ThrowsException() {
        // given
        Long familyId = 1L;
        List<FamilyQuestionEntity> list = new ArrayList<>();
        doNothing().when(familyService).exist(familyId);
        when(familyQuestionRepository.findAllByFamilyIdOrderByOpenedAtDesc(familyId)).thenReturn(list);

        // when & then
        assertThatThrownBy(() -> familyQuestionService.find(familyId))
                .isInstanceOf(BusinessException.class)
                .hasMessage(CommonErrorCode.FAMILY_QUESTION_NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("family 에게 할당된 질문 조회 테스트")
    public void findFamilyQuestionByFamilyId() {
        // given
        Long familyId = 1L;
        Long questionId = 1L;
        QuestionEntity questionEntity = createQuestionEntity(questionId, "content1");
        FamilyQuestionEntity familyQuestionEntity = createFamilyQuestionEntity(1L, familyId, questionId, questionEntity.getContent());
        doNothing().when(familyService).exist(familyId);
        when(familyQuestionRepository.findOneByFamilyIdAndQuestionId(familyId, questionEntity.getId())).thenReturn(Optional.ofNullable(familyQuestionEntity));

        // when
        FamilyQuestionResponseDTO response = familyQuestionService.find(familyId, questionId);


        // then
        assertThat(response.familyId()).isEqualTo(familyId);
        assertThat(response.questionId()).isEqualTo(questionId);
    }

    @Test
    @DisplayName("family 에게 할당된 질문 조회 예외 발생 테스트")
    public void findFamilyQuestionByFamilyId_NotFound_ThrowsException() {
        // given
        Long familyId = 1L;
        doNothing().when(familyService).exist(familyId);
        when(familyQuestionRepository.findOneByFamilyIdAndQuestionId(familyId, 99L)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> familyQuestionService.find(familyId, 99L))
                .isInstanceOf(BusinessException.class)
                .hasMessage(CommonErrorCode.FAMILY_QUESTION_NOT_FOUND.getMessage());
    }

    private FamilyQuestionEntity createFamilyQuestionEntity(Long id, Long familyId, Long questionId, String questionContent) {
        return FamilyQuestionEntity.builder()
                .id(id)
                .familyId(familyId)
                .questionId(questionId)
                .questionContent(questionContent)
                .openedAt(LocalDate.now())
                .build();
    }

    private QuestionEntity createQuestionEntity(Long id, String content) {
        return QuestionEntity.builder()
                .id(id)
                .content(content)
                .build();
    }
}
