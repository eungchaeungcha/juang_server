package com.eungchaeungcha.juang.controller;

import com.eungchaeungcha.juang.dto.FamilyQuestionResponseDTO;
import com.eungchaeungcha.juang.service.FamilyQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/families")
@RequiredArgsConstructor
public class FamilyQuestionController {

    private final FamilyQuestionService familyQuestionService;

    @GetMapping("/{familyId}/questions")
    public ResponseEntity<List<FamilyQuestionResponseDTO>> get(
            @PathVariable Long familyId
    ) {
        List<FamilyQuestionResponseDTO> response = familyQuestionService.find(familyId);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{familyId}/questions/{questionId}")
    public ResponseEntity<FamilyQuestionResponseDTO> get(
            @PathVariable Long familyId,
            @PathVariable Long questionId
    ) {

        FamilyQuestionResponseDTO response = familyQuestionService.find(familyId, questionId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{familyId}/questions/today")
    public ResponseEntity<FamilyQuestionResponseDTO> getToday(
            @PathVariable Long familyId
    ) {
        FamilyQuestionResponseDTO response = familyQuestionService.findToday(familyId);

        return ResponseEntity.ok(response);
    }
}
