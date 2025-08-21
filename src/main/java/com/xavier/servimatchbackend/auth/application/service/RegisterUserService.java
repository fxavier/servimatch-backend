package com.xavier.servimatchbackend.auth.application.service;

import com.xavier.servimatchbackend.auth.application.dto.RegisterUserCommand;
import com.xavier.servimatchbackend.auth.application.dto.UserResponse;
import com.xavier.servimatchbackend.auth.application.port.inbound.RegisterUserUseCase;
import com.xavier.servimatchbackend.auth.application.port.outbound.LoadUserPort;
import com.xavier.servimatchbackend.auth.application.port.outbound.SaveUserPort;
import com.xavier.servimatchbackend.auth.domain.Email;
import com.xavier.servimatchbackend.auth.domain.User;
import com.xavier.servimatchbackend.common.domain.DomainException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterUserService implements RegisterUserUseCase {

    private final SaveUserPort saveUserPort;
    private final LoadUserPort loadUserPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse execute(RegisterUserCommand command) {
        // Check if email already exists
        Email email = new Email(command.getEmail());
        if (loadUserPort.existsByEmail(email)) {
            throw new DomainException("Email already registered: " + command.getEmail());
        }

        // Hash password
        String hashedPassword = passwordEncoder.encode(command.getPassword());

        // Create user
        User user = User.create(
                command.getEmail(),
                hashedPassword,
                command.getFullName(),
                command.getRole()
        );

        // Save user
        User savedUser = saveUserPort.save(user);

        // Map to response
        return UserResponse.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail().getValue())
                .fullName(savedUser.getFullName().getFullName())
                .role(savedUser.getRole())
                .active(savedUser.isActive())
                .build();
    }

    @Override
    public boolean validate(RegisterUserCommand input) {
        return false;
    }
}
