package com.eungchaeungcha.juang.entity;

import com.eungchaeungcha.juang.domain.FamilyQuestion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "family_question")
public class FamilyQuestionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long familyId;

    private Long questionId;

    private String questionContent;

    private LocalDate openedAt;

    public FamilyQuestion toDomain(){
        return FamilyQuestion.builder()
                .id(this.id)
                .familyId(this.familyId)
                .questionId(this.questionId)
                .openedAt(this.openedAt)
                .build();
    }
}
