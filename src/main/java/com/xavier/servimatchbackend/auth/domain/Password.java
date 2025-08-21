package com.xavier.servimatchbackend.auth.domain;

import com.xavier.servimatchbackend.common.domain.ValueObject;
import lombok.Getter;

/**
 * Represents a user's password in the system.
 * The password is stored as a hashed value for security.
 * This class is immutable and implements equality based on the hashed value.
 */

@Getter
public class Password extends ValueObject {

    private final String hashedValue;

    public Password(String hashedValue) {
        if (hashedValue == null || hashedValue.isBlank()) {
            throw new IllegalArgumentException("Password hash cannot be null or empty");
        }
        this.hashedValue = hashedValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return hashedValue.equals(password.hashedValue);
    }

    @Override
    public int hashCode() {
        return hashedValue.hashCode();
    }
}
