package com.xavier.servimatchbackend.auth.domain;

import com.xavier.servimatchbackend.auth.domain.event.UserRegisteredEvent;
import com.xavier.servimatchbackend.common.domain.AggregateRoot;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

/**
 * Represents a user in the system.
 * This class encapsulates user details and behaviors, including registration, profile updates, and role management.
 */


@Getter
public class User extends AggregateRoot<User> {

    private final Email email;
    private Password password;
    private FullName fullName;
    private final Role role;
    private boolean active;

    @Builder
    private User(UUID id, Email email, Password password, FullName fullName, Role role) {
        super(id != null ? id : UUID.randomUUID());
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.role = role != null ? role : Role.CLIENT;
        this.active = true;
    }

    public static User create(String email, String passwordHash, String fullName, Role role) {
        User user = User.builder()
                .email(new Email(email))
                .password(new Password(passwordHash))
                .fullName(new FullName(fullName))
                .role(role)
                .build();

        user.registerEvent(new UserRegisteredEvent(
                user.getId().toString(),
                user.getEmail().getValue(),
                user.getFullName().getFullName()
        ));

        return user;
    }

    public void updatePassword(String newPasswordHash) {
        this.password = new Password(newPasswordHash);
    }

    public void updateProfile(String fullName) {
        this.fullName = new FullName(fullName);
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    public boolean isProvider() {
        return role == Role.PROVIDER;
    }

    public boolean isClient() {
        return role == Role.CLIENT;
    }

    public boolean isAdmin() {
        return role == Role.ADMIN;
    }
}
