package dev.anderson.oauth2spring.service;

import dev.anderson.oauth2spring.dto.OAuthAttributes;
import dev.anderson.oauth2spring.dto.SessionUser;
import dev.anderson.oauth2spring.entities.UserEntity;
import dev.anderson.oauth2spring.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;

    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String id = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        OAuthAttributes attributes = OAuthAttributes.of(id, userNameAttributeName, oAuth2User.getAttributes());

        UserEntity user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", SessionUser.fromEntity(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(
                user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private UserEntity saveOrUpdate(OAuthAttributes attributes) {
        UserEntity user = userRepository.findByEmail(attributes.getEmail());
        if (user == null) {
            user = UserEntity.newUser(
                    attributes.getName(),
                    attributes.getEmail(),
                    attributes.getPicture()
            );

            return userRepository.save(user);
        }

        user.setName(attributes.getName());
        user.setPicture(attributes.getPicture());
        return userRepository.save(user);
    }
}