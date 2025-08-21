package com.xavier.servimatchbackend.auth.domain.repository;

import com.xavier.servimatchbackend.auth.domain.Email;
import com.xavier.servimatchbackend.auth.domain.User;
import com.xavier.servimatchbackend.auth.domain.UserId;


import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(UserId id);

    Optional<User> findByEmail(Email email);

    boolean existsByEmail(Email email);

    void deleteById(UserId id);
}
