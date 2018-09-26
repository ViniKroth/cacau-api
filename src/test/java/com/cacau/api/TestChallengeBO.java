package com.cacau.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cacau.api.exception.ValidationException;
import com.cacau.api.model.bo.ChallengeBO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestChallengeBO {

    @Test(expected = ValidationException.class)
    public void testChallengeBODescriptionValidation() throws ValidationException {
        ChallengeBO challengeBO = new ChallengeBO();
        challengeBO.setDescription("");
    }

}
