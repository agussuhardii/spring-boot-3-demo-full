package com.agussuhardi.springdemofull.validation.impl;


import com.agussuhardi.springdemofull.repository.UserRepository;
import com.agussuhardi.springdemofull.validation.EmailConstraints;
import com.google.common.base.Strings;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


@RequiredArgsConstructor
public class EmailConstraintsImpl implements ConstraintValidator<EmailConstraints, String> {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Strings.isNullOrEmpty(value)) return true;
        return userRepository.findByEmail(value).isEmpty();
    }
}
