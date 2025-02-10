package com.eungchaeungcha.juang.entity;

import com.eungchaeungcha.juang.domain.Role;
import com.eungchaeungcha.juang.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    private String nickName;
    private Long familyId;
    private Long characterId;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User toDomain() {
        return User.builder()
                .id(this.id)
                .username(this.username)
                .password(this.password)
                .nickName(this.nickName)
                .familyId(this.familyId)
                .characterId(this.characterId)
                .role(this.role)
                .build();
    }

    public static UserEntity fromDomain(User user) {
        return UserEntity.builder()
                .username(user.username())
                .password(user.password())
                .nickName(user.nickName())
                .familyId(user.familyId())
                .characterId(user.characterId())
                .role(user.role())
                .build();
    }

    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
