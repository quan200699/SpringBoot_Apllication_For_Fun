package com.example.startup.service.user;

import com.example.startup.model.entity.User;
import com.example.startup.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
