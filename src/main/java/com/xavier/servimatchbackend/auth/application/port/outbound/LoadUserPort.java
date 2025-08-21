package com.xavier.servimatchbackend.auth.application.port.outbound;

import com.xavier.servimatchbackend.auth.domain.Email;
import com.xavier.servimatchbackend.auth.domain.User;
import com.xavier.servimatchbackend.auth.domain.UserId;

import java.util.Optional;

public interface LoadUserPort {

    Optional<User> loadById(UserId id);

    Optional<User> loadByEmail(Email email);

    boolean existsByEmail(Email email);
}
