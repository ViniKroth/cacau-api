package com.cacau.api.validator;

import java.util.Date;

import javax.validation.Validation;
import org.apache.commons.validator.routines.EmailValidator;

public class Validator extends Validation {

    public boolean validateEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public boolean validatePassword(String password, int lowerLimit, int upperLimit) {
        return password.length() >= lowerLimit && password.length() <= upperLimit;
    }

    public boolean validateStringCharacter(String name, int lowerLimit, int upperLimit) {
        return name.length() >= lowerLimit && name.length() <= upperLimit;
    }

    public boolean validateDateRange(Date beginDate, Date endDate) {
        return endDate.after(beginDate) ? true : false;

    }

}
