package com.cacau.api;

import static org.junit.Assert.*;

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
        assertFalse(validator.validatePassword("", 8, 100));
    }
    
    @Test
    public void testValidateStringCharacter() {
        assertTrue(validator.validatePassword("viniciusfrantzkroth", 8, 100));
    }

}
