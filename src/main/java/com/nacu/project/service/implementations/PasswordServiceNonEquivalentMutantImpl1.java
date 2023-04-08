package com.nacu.project.service.implementations;

import com.nacu.project.service.PasswordService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("non_equivalent_mutant_killed_by_test")
public class PasswordServiceNonEquivalentMutantImpl1 implements PasswordService
{
    private static final int MINIMUM_LENGTH = 8;
    private static final int MAXIMUM_LENGTH = 20;

    private static final String VALID_PASSWORD_MESSAGE = "The password is valid!";
    private static final String LENGTH_ERROR_MESSAGE = "The password must be at least " + MINIMUM_LENGTH + " characters and at most " + MAXIMUM_LENGTH;
    private static final String NO_LOWERCASE_ERROR_MESSAGE = "The password must have at least one lower case character";
    private static final String NO_UPPERCASE_ERROR_MESSAGE = "The password must have at least one upper case character";
    private static final String NO_DIGITS_ERROR_MESSAGE = "The password must have at least one digit";
    private static final String NO_SPECIAL_CHAR_ERROR_MESSAGE = "The password must have at least one special char";

    @Override
    public String validatePassword(String password)
    {
        if (!hasMinimumLength(password) || !hasMaximumLength(password))
        {
            return LENGTH_ERROR_MESSAGE;
        }

        if (!hasAtLeastOneLowercaseChar(password))
        {
            return NO_LOWERCASE_ERROR_MESSAGE;
        }

        if (!hasAtLeastOneUppercaseChar(password))
        {
            return NO_UPPERCASE_ERROR_MESSAGE;
        }

        if (!hasAtLeastOneDigit(password))
        {
            return NO_DIGITS_ERROR_MESSAGE;
        }

        if (!hasAtLeastOneSpecialChar(password))
        {
            return NO_SPECIAL_CHAR_ERROR_MESSAGE;
        }

        return VALID_PASSWORD_MESSAGE;
    }

    private boolean hasMinimumLength(String password)
    {
        return password.length() > MINIMUM_LENGTH; // it was >=
    }

    private boolean hasMaximumLength(String password)
    {
        return password.length() <= MAXIMUM_LENGTH;
    }

    private boolean hasAtLeastOneLowercaseChar(String password)
    {
        return !password.toUpperCase().equals(password);
    }

    private boolean hasAtLeastOneUppercaseChar(String password)
    {
        return !password.toLowerCase().equals(password);
    }

    private boolean hasAtLeastOneDigit(String password)
    {
        return password.matches(".*[0-9].*");
    }

    private boolean hasAtLeastOneSpecialChar(String password)
    {
        return password.matches(".*[~!@#$%^&*()|_+{}\\[\\]:;,.<>/?-].*");
    }
}
