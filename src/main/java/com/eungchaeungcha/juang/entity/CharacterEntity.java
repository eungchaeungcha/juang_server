package com.eungchaeungcha.juang.entity;

import com.eungchaeungcha.juang.domain.Character;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "characters")
public class CharacterEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String color;
    private String link;

    public Character toDomain() {
        return Character.builder()
                .id(this.id)
                .name(this.name)
                .color(this.color)
                .link(this.link)
                .build();
    }
}
