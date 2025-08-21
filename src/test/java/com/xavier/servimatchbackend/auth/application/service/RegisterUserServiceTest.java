package com.xavier.servimatchbackend.auth.application.service;

import com.xavier.servimatchbackend.auth.application.dto.RegisterUserCommand;
import com.xavier.servimatchbackend.auth.application.dto.UserResponse;
import com.xavier.servimatchbackend.auth.application.port.outbound.LoadUserPort;
import com.xavier.servimatchbackend.auth.application.port.outbound.SaveUserPort;
import com.xavier.servimatchbackend.auth.domain.Email;
import com.xavier.servimatchbackend.auth.domain.Role;
import com.xavier.servimatchbackend.auth.domain.User;
import com.xavier.servimatchbackend.common.domain.DomainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegisterUserServiceTest {

    @Mock
    private SaveUserPort saveUserPort;

    @Mock
    private LoadUserPort loadUserPort;

    @Mock
    private PasswordEncoder passwordEncoder;

    private RegisterUserService registerUserService;

    @BeforeEach
    void setUp() {
        registerUserService = new RegisterUserService(saveUserPort, loadUserPort, passwordEncoder);
    }

    @Test
    void shouldRegisterNewUser() {
        // Given
        RegisterUserCommand command = RegisterUserCommand.builder()
                .email("test@example.com")
                .password("password123")
                .fullName("John Doe")
                .role(Role.CLIENT)
                .build();

        when(loadUserPort.existsByEmail(any(Email.class))).thenReturn(false);
        when(passwordEncoder.encode(command.getPassword())).thenReturn("hashedPassword");
        when(saveUserPort.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        UserResponse response = registerUserService.execute(command);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo(command.getEmail());
        assertThat(response.getFullName()).isEqualTo(command.getFullName());
        assertThat(response.getRole()).isEqualTo(command.getRole());
        assertThat(response.isActive()).isTrue();

        verify(saveUserPort, times(1)).save(any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        // Given
        RegisterUserCommand command = RegisterUserCommand.builder()
                .email("existing@example.com")
                .password("password123")
                .fullName("John Doe")
                .role(Role.CLIENT)
                .build();

        when(loadUserPort.existsByEmail(any(Email.class))).thenReturn(true);

        // When/Then
        assertThatThrownBy(() -> registerUserService.execute(command))
                .isInstanceOf(DomainException.class)
                .hasMessageContaining("Email already registered");

        verify(saveUserPort, never()).save(any(User.class));
    }
}
