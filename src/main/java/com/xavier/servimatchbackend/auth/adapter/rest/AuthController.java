package com.xavier.servimatchbackend.auth.adapter.rest;

import com.xavier.servimatchbackend.auth.adapter.rest.dto.LoginRequest;
import com.xavier.servimatchbackend.auth.adapter.rest.dto.RegisterRequest;
import com.xavier.servimatchbackend.auth.application.dto.AuthTokenResponse;
import com.xavier.servimatchbackend.auth.application.dto.LoginCommand;
import com.xavier.servimatchbackend.auth.application.dto.RegisterUserCommand;
import com.xavier.servimatchbackend.auth.application.dto.UserResponse;
import com.xavier.servimatchbackend.auth.application.port.inbound.GetUserDetailsUseCase;
import com.xavier.servimatchbackend.auth.application.port.inbound.LoginUserUseCase;
import com.xavier.servimatchbackend.auth.application.port.inbound.RegisterUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication and user management endpoints")
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;
    private final GetUserDetailsUseCase getUserDetailsUseCase;

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Email already exists",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        RegisterUserCommand command = RegisterUserCommand.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .fullName(request.getFullName())
                .role(request.getRole())
                .build();

        UserResponse response = registerUserUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate user and get JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<AuthTokenResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginCommand command = LoginCommand.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        AuthTokenResponse response = loginUserUseCase.execute(command);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    @Operation(summary = "Get current user details", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User details retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        // Extract user ID from the security context
        // In a real implementation, you would store the user ID in the JWT claims
        // For now, we'll use the email to look up the user
        UserResponse response = getUserDetailsUseCase.execute(userDetails.getUsername());
        return ResponseEntity.ok(response);
    }
}
