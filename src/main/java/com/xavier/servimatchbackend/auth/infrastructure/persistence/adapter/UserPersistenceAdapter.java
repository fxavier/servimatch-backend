package com.xavier.servimatchbackend.auth.infrastructure.persistence.adapter;

import com.xavier.servimatchbackend.auth.application.port.outbound.LoadUserPort;
import com.xavier.servimatchbackend.auth.application.port.outbound.SaveUserPort;
import com.xavier.servimatchbackend.auth.domain.Email;
import com.xavier.servimatchbackend.auth.domain.User;
import com.xavier.servimatchbackend.auth.domain.UserId;
import com.xavier.servimatchbackend.auth.domain.repository.UserRepository;
import com.xavier.servimatchbackend.auth.infrastructure.mapper.UserMapper;
import com.xavier.servimatchbackend.auth.infrastructure.persistence.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepository, LoadUserPort, SaveUserPort {

    private final UserJpaRepository jpaRepository;
    private final UserMapper mapper;

    @Override
    public User save(User user) {
        var entity = mapper.toEntity(user);
        var saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<User> findById(UserId id) {
        return jpaRepository.findById(id.getValue())
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return jpaRepository.findByEmail(email.getValue())
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsByEmail(Email email) {
        return jpaRepository.existsByEmail(email.getValue());
    }

    @Override
    public void deleteById(UserId id) {
        jpaRepository.deleteById(id.getValue());
    }

    @Override
    public Optional<User> loadById(UserId id) {
        return findById(id);
    }

    @Override
    public Optional<User> loadByEmail(Email email) {
        return findByEmail(email);
    }
}
