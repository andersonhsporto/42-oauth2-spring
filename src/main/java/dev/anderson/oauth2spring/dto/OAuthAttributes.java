package dev.anderson.oauth2spring.dto;

import dev.anderson.oauth2spring.entities.Role;
import dev.anderson.oauth2spring.entities.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@Builder
@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;

    private String nameAttributeKey;

    private String name;

    private String email;

    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email,
                           String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributes of(String registerationId, String userNameAttributeName,
                                     Map<String, Object> attributes) {
        return ofIntra42(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofIntra42(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> image = (Map<String, Object>) attributes.get("image");
        String imageUrl = (String) image.get("link");

        return OAuthAttributes.builder()
                .name((String) attributes.get("login"))
                .email((String) attributes.get("email"))
                .picture(imageUrl)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public UserEntity toEntity() {
        return UserEntity.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}
