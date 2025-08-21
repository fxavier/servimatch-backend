package com.xavier.servimatchbackend.auth.application.service;

import com.xavier.servimatchbackend.auth.application.dto.UserResponse;
import com.xavier.servimatchbackend.auth.application.port.inbound.GetUserDetailsUseCase;
import com.xavier.servimatchbackend.auth.application.port.outbound.LoadUserPort;
import com.xavier.servimatchbackend.auth.domain.User;
import com.xavier.servimatchbackend.auth.domain.UserId;
import com.xavier.servimatchbackend.common.domain.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetUserDetailsService implements GetUserDetailsUseCase {

    private final LoadUserPort loadUserPort;

    @Override
    public UserResponse execute(String userId) {
        UserId id = UserId.of(userId);
        User user = loadUserPort.loadById(id)
                .orElseThrow(() -> new DomainException("User not found with id: " + userId));

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail().getValue())
                .fullName(user.getFullName().getFullName())
                .role(user.getRole())
                .active(user.isActive())
                .build();
    }

    @Override
    public boolean validate(String input) {
        return false;
    }
}
