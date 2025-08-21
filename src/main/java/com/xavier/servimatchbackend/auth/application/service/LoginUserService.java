package com.xavier.servimatchbackend.auth.application.service;

import com.xavier.servimatchbackend.auth.application.dto.AuthTokenResponse;
import com.xavier.servimatchbackend.auth.application.dto.LoginCommand;
import com.xavier.servimatchbackend.auth.application.dto.UserResponse;
import com.xavier.servimatchbackend.auth.application.port.inbound.LoginUserUseCase;
import com.xavier.servimatchbackend.auth.application.port.outbound.LoadUserPort;
import com.xavier.servimatchbackend.auth.application.port.outbound.TokenGeneratorPort;
import com.xavier.servimatchbackend.auth.domain.Email;
import com.xavier.servimatchbackend.auth.domain.User;
import com.xavier.servimatchbackend.auth.domain.event.UserLoggedInEvent;
import com.xavier.servimatchbackend.common.domain.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginUserService implements LoginUserUseCase {

    private final LoadUserPort loadUserPort;
    private final TokenGeneratorPort tokenGeneratorPort;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public AuthTokenResponse execute(LoginCommand command) {
        // Find user by email
        Email email = new Email(command.getEmail());
        User user = loadUserPort.loadByEmail(email)
                .orElseThrow(() -> new DomainException("Invalid email or password"));

        // Verify password
        if (!passwordEncoder.matches(command.getPassword(), user.getPassword().getHashedValue())) {
            throw new DomainException("Invalid email or password");
        }

        // Check if user is active
        if (!user.isActive()) {
            throw new DomainException("User account is deactivated");
        }

        // Generate token
        String token = tokenGeneratorPort.generateToken(user);

        // Publish login event
        eventPublisher.publishEvent(new UserLoggedInEvent(
                user.getId().toString(),
                user.getEmail().getValue()
        ));

        // Build response
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail().getValue())
                .fullName(user.getFullName().getFullName())
                .role(user.getRole())
                .active(user.isActive())
                .build();

        return AuthTokenResponse.of(token, 3600L, userResponse);
    }

    @Override
    public boolean validate(LoginCommand input) {
        return false;
    }
}
