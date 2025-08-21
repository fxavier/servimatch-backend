package com.xavier.servimatchbackend.auth.application.dto;

import com.xavier.servimatchbackend.auth.domain.Role;
import com.xavier.servimatchbackend.common.application.Command;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private UUID id;
    private String email;
    private String fullName;
    private Role role;
    private boolean active;
}
