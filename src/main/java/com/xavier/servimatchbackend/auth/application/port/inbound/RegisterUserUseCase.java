package com.xavier.servimatchbackend.auth.application.port.inbound;

import com.xavier.servimatchbackend.auth.application.dto.RegisterUserCommand;
import com.xavier.servimatchbackend.auth.application.dto.UserResponse;
import com.xavier.servimatchbackend.common.application.UseCase;

public interface RegisterUserUseCase extends UseCase<RegisterUserCommand, UserResponse> {
}
