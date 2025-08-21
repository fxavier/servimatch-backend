package com.xavier.servimatchbackend.auth.application.port.outbound;

import com.xavier.servimatchbackend.auth.domain.User;



public interface SaveUserPort {

    User save(User user);
}
