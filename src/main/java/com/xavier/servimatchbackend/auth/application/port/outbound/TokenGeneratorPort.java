package com.xavier.servimatchbackend.auth.application.port.outbound;

import com.xavier.servimatchbackend.auth.domain.User;

public interface TokenGeneratorPort {
    String generateToken(User user);

    String extractUsername(String token);

    boolean validateToken(String token, String username);
}
