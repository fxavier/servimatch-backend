package com.xavier.servimatchbackend.auth.domain;

import com.xavier.servimatchbackend.common.domain.ValueObject;
import lombok.Getter;

/**
 * Represents a user's full name, consisting of a first name and an optional last name.
 * Provides methods to create a full name from a single string or from separate first and last names.
 * * This class ensures that the full name is always valid and non-empty.
 * It also provides methods to retrieve the full name as a single string and to compare full names
 * for equality.
 */

@Getter
public class FullName extends ValueObject {

    private final String firstName;
    private final String lastName;

    public FullName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Full name cannot be null or empty");
        }

        String[] parts = fullName.trim().split("\\s+", 2);
        this.firstName = parts[0];
        this.lastName = parts.length > 1 ? parts[1] : "";
    }

    public FullName(String firstName, String lastName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        this.firstName = firstName.trim();
        this.lastName = lastName != null ? lastName.trim() : "";
    }

    public String getFullName() {
        return lastName.isEmpty() ? firstName : firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullName fullName = (FullName) o;
        return firstName.equals(fullName.firstName) && lastName.equals(fullName.lastName);
    }

    @Override
    public int hashCode() {
        return firstName.hashCode() + lastName.hashCode();
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
