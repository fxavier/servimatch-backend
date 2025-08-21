package com.xavier.servimatchbackend.auth.domain.event;

import com.xavier.servimatchbackend.common.domain.DomainEvent;
import lombok.Getter;

import java.util.UUID;

/**
 * Event triggered when a user registers in the system.
 * Contains the user's email and full name.
 */

@Getter
public class UserRegisteredEvent extends DomainEvent {

    private final String email;
    private final String fullName;

    public UserRegisteredEvent(String userId, String email, String fullName) {
        super(UUID.fromString(userId));
        this.email = email;
        this.fullName = fullName;
    }
}
