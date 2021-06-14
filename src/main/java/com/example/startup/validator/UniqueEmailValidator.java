package com.example.startup.validator;

import com.example.startup.repository.IUserRepository;
import com.example.startup.validator.annotated.UniqueEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private IUserRepository userRepository;

    public UniqueEmailValidator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && !userRepository.findByEmail(email).isPresent();
    }
}
