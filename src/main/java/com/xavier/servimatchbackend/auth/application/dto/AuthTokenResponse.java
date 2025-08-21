package com.xavier.servimatchbackend.auth.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenResponse {

    private String token;
    private String type;
    private Long expiresIn;
    private UserResponse user;

    public static AuthTokenResponse of(String token, Long expiresIn, UserResponse user) {
        return AuthTokenResponse.builder()
                .token(token)
                .type("Bearer")
                .expiresIn(expiresIn)
                .user(user)
                .build();
    }
}
