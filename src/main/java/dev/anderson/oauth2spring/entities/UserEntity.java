package dev.anderson.oauth2spring.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public static UserEntity newUser(String name, String email, String picture) {
        return UserEntity.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", picture='" + picture + '\'' +
                ", role=" + role +
                '}';
    }
}
