package com.xavier.servimatchbackend.auth.domain;

import com.xavier.servimatchbackend.common.domain.ValueObject;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserId extends ValueObject {

    private final UUID value;

    public UserId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        this.value = value;
    }

    public static UserId of(String value) {
        return new UserId(UUID.fromString(value));
    }

    public static UserId generate() {
        return new UserId(UUID.randomUUID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return value.equals(userId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
