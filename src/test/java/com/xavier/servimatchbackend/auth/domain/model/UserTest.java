package com.xavier.servimatchbackend.auth.domain.model;

import com.xavier.servimatchbackend.auth.domain.Email;
import com.xavier.servimatchbackend.auth.domain.Role;
import com.xavier.servimatchbackend.auth.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class UserTest {

    @Test
    @DisplayName("Should create user with valid data")
    void shouldCreateUserWithValidData() {
        // Given
        String email = "test@example.com";
        String passwordHash = "hashedPassword123";
        String fullName = "John Doe";
        Role role = Role.CLIENT;

        // When
        User user = User.create(email, passwordHash, fullName, role);

        // Then
        assertThat(user).isNotNull();
        assertThat(user.getId()).isNotNull();
        assertThat(user.getEmail().getValue()).isEqualTo(email);
        assertThat(user.getFullName().getFullName()).isEqualTo(fullName);
        assertThat(user.getRole()).isEqualTo(role);
        assertThat(user.isActive()).isTrue();
      //  assertThat(user.domainEvents()).hasSize(1);
    }

    @Test
    @DisplayName("Should throw exception for invalid email")
    void shouldThrowExceptionForInvalidEmail() {
        // Given
        String invalidEmail = "not-an-email";

        // When/Then
        assertThatThrownBy(() -> new Email(invalidEmail))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid email format");
    }

}
