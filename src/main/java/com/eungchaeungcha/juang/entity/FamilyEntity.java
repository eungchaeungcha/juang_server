package com.eungchaeungcha.juang.entity;

import com.eungchaeungcha.juang.domain.Family;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "family")
public class FamilyEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;

    public static FamilyEntity from(Family family) {
        return FamilyEntity.builder()
                .name(family.getName())
                .code(family.getCode())
                .build();
    }

    public Family toDomain(){
        return Family.builder()
                .id(this.id)
                .code(this.code)
                .name(this.name)
                .build();
    }
}
