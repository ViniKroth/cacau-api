package com.cacau.api;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.cacau.api.validator.Validator;

public class TestValidator {
    Validator validator = new Validator();

    @Test
    public void testValidatePasswordEmpty() {
        assertFalse(validator.validatePassword("", 8, 100));
    }

    @Test
    public void testValidatePassword() {
        assertTrue(validator.validatePassword("senha1234", 8, 100));
    }
    
    @Test
    public void testValidateStringCharacterEmpty() {
        assertFalse(validator.validateStringCharacter("", 8, 100));
    }
    
    @Test
    public void testValidateStringCharacter() {
        assertTrue(validator.validateStringCharacter("viniciusfrantzkroth", 8, 100));
    }
    
    @Test
    public void testValidateDateRangeValid() {
        assertTrue(validator.validateDateRange(new Date(), new Date(System.currentTimeMillis() + 1000)));
    }
    
    @Test
    public void testValidateDateRangeInvalid() {
        assertFalse(validator.validateDateRange(new Date(), new Date(System.currentTimeMillis() - 10000)));
    }
    
    @Test
    public void testValidateEmailValid() {
        assertTrue(validator.validateEmail("teste@teste.com"));
    }
    
    @Test
    public void testValidateEmailInvalid() {
        assertFalse(validator.validateEmail("faketeste.com"));
    }
    
    
    

}
