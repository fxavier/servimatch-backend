package com.xavier.servimatchbackend.auth.domain.event;

import com.xavier.servimatchbackend.common.domain.DomainEvent;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class UserLoggedInEvent extends DomainEvent {

    private final String email;
    private final Instant loginTime;

    public UserLoggedInEvent(String userId, String email) {
        super(UUID.fromString(userId));
        this.email = email;
        this.loginTime = Instant.now();
    }
}
