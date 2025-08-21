package com.xavier.servimatchbackend.auth.adapter.rest.dto;

import com.xavier.servimatchbackend.auth.domain.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User registration request")
public class RegisterRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Schema(description = "User email address", example = "john.doe@example.com")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Schema(description = "User password", example = "SecurePass123!")
    private String password;

    @NotBlank(message = "Full name is required")
    @Schema(description = "User full name", example = "John Doe")
    private String fullName;

    @NotNull(message = "Role is required")
    @Schema(description = "User role", example = "CLIENT")
    private Role role;
}
