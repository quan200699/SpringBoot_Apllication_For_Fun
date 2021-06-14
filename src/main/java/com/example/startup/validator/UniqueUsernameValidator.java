package com.example.startup.validator;

import com.example.startup.repository.IUserRepository;
import com.example.startup.validator.annotated.UniqueUsername;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    private IUserRepository userRepository;

    public UniqueUsernameValidator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return username != null && !userRepository.findByUsername(username).isPresent();
    }
}
