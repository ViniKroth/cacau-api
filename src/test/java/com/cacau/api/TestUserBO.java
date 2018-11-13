package com.cacau.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cacau.api.exception.PersistencyException;
import com.cacau.api.exception.ValidationException;
import com.cacau.api.model.bo.UserBO;
import com.cacau.api.model.dto.UserMongoDTO;
import com.cacau.api.model.dto.UserRequestDTO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestUserBO {
	
	@Mock
	UserMongoDTO user;
	@Mock
	UserRequestDTO userRequest;
	
	UserBO userBO;
	
	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		
		//user
		assertNotNull(user);
		when(user.getId()).thenReturn("1");
		when(user.getEmail()).thenReturn("email@email.com");
		when(user.getName()).thenReturn("giancarlo");
		when(user.getPassword()).thenReturn("senhasenha");
		when(user.isAdmin()).thenReturn(true);
		when(user.getCreationDate()).thenReturn(new Date());
		when(user.isActive()).thenReturn(true);
		
		//userRequest
		assertNotNull(userRequest);
		Optional<String> email = Optional.of("email@email.com");
		Optional<String> senha = Optional.of("senhasenha");
		Optional<String> name = Optional.of("giancarlo");
		Optional<String> id = Optional.of("2");
		Optional<Boolean> adm = Optional.of(true);
		when(userRequest.getId()).thenReturn(id);
		when(userRequest.isActive()).thenReturn(true);
		when(userRequest.getEmail()).thenReturn(email);
		when(userRequest.getName()).thenReturn(name);
		when(userRequest.getPassword()).thenReturn(senha);
		when(userRequest.isAdmin()).thenReturn(adm);
	}
	
	@Test
	public void testCreateUserBO() throws ValidationException {

		userBO = new UserBO(user);
		assertNotNull(userBO);
	}
	
	@Test
	public void testCreateUserRequestBO() throws ValidationException, PersistencyException {
		userBO = new UserBO(userRequest);
		assertNotNull(userBO);
	}
	
	@Test
	public void testUpdateUserRequestBO() throws ValidationException, PersistencyException {
		userBO = new UserBO(user);
		userBO.updateWith(userRequest);
		assertEquals(userBO.getId(), "2");
	}
}
