package com.xavier.servimatchbackend.auth.infrastructure.mapper;


import com.xavier.servimatchbackend.auth.domain.Email;
import com.xavier.servimatchbackend.auth.domain.FullName;
import com.xavier.servimatchbackend.auth.domain.Password;
import com.xavier.servimatchbackend.auth.domain.User;
import com.xavier.servimatchbackend.auth.infrastructure.persistence.entity.UserJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "passwordHash", expression = "java(user.getPassword().getHashedValue())")
    @Mapping(target = "email", expression = "java(user.getEmail().getValue())")
    @Mapping(target = "fullName", expression = "java(user.getFullName().getFullName())")
    @Mapping(target = "id", expression = "java(user.getId() == null ? null : user.getId())")
    @Mapping(target = "role", expression = "java(user.getRole())")
//    @Mapping(target = "active", expression = "java(user.isActive())")
//    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "updatedAt", ignore = true)
    UserJpaEntity toEntity(User user);

    default User toDomain(UserJpaEntity entity) {
        return User.builder()
                .id(entity.getId())
                .email(new Email(entity.getEmail()))
                .password(new Password(entity.getPasswordHash()))
                .fullName(new FullName(entity.getFullName()))
                .role(entity.getRole())
                .build();
    }
}
