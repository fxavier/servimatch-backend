package com.xavier.servimatchbackend.auth.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    CLIENT("Client"),
    PROVIDER("Service Provider"),
    ADMIN("Admin");

    private final String displayName;
}
