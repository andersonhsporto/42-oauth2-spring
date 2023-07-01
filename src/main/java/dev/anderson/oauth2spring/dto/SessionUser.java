package dev.anderson.oauth2spring.dto;

import dev.anderson.oauth2spring.entities.UserEntity;

public record SessionUser(String name, String email, String avatarUrl) {

    public static SessionUser fromEntity(UserEntity user) {
        return new SessionUser(user.getName(), user.getEmail(), user.getPicture());
    }
}
