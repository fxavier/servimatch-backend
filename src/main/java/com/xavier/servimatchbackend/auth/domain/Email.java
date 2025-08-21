package com.xavier.servimatchbackend.auth.domain;

import com.xavier.servimatchbackend.common.domain.ValueObject;
import lombok.Getter;

import java.util.regex.Pattern;

/**
 * Represents an email address in the system.
 * Validates the format of the email address upon creation.
 * Ensures that the email is stored in lowercase.
 */

@Getter
public class Email extends ValueObject {

    private static final Pattern VALID_EMAIL_PATTERN =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private final String value;

    public Email(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        if (!VALID_EMAIL_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid email format: " + value);
        }

        this.value = value.toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return value.equals(email.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }

}
