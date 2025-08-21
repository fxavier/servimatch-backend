package com.xavier.servimatchbackend.auth.application.port.inbound;

import com.xavier.servimatchbackend.auth.application.dto.AuthTokenResponse;
import com.xavier.servimatchbackend.auth.application.dto.LoginCommand;
import com.xavier.servimatchbackend.common.application.UseCase;

public interface LoginUserUseCase extends UseCase<LoginCommand, AuthTokenResponse> {

}
