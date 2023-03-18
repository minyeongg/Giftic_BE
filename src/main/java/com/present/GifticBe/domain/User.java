package com.present.GifticBe.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.management.relation.Role;
import javax.persistence.*;

@NoArgsConstructor
@DynamicUpdate
@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "provider")
    private String provider;

    @Column(name = "nickname")
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Role role;

    @Builder
    public User(Long id, String name, String email, Role role, String nickname) {
        this.role = role;
        this.id = id;
        this.name = name;
        this.email = email;
        this.nickname = nickname;
    }

    public User update(String name, String email) {
        this.name = name;
        this.email = email;
        return this;
    }
}
