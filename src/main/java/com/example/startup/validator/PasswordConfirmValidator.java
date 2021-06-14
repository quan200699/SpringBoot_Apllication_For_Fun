package com.example.startup.validator;

import com.example.startup.model.dto.PasswordForm;
import com.example.startup.validator.annotated.PasswordConfirm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConfirmValidator implements ConstraintValidator<PasswordConfirm, PasswordForm> {
    @Override
    public boolean isValid(PasswordForm passwordForm, ConstraintValidatorContext context) {
        return passwordForm != null && passwordForm.getPassword().equals(passwordForm.getRePassword());
    }
}
