package dev.anderson.oauth2spring.entities;

public enum Role {
    GUEST("ROLE_GUEST", "Guest"),
    USER("ROLE_USER", "User");

    private final String key;
    private final String title;

    Role(String key, String title) {
        this.key = key;
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }
}
