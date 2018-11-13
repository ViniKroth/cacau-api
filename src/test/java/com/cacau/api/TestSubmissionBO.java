package com.cacau.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cacau.api.exception.PersistencyException;
import com.cacau.api.exception.ValidationException;
import com.cacau.api.model.bo.SubmissionBO;
import com.cacau.api.model.bo.UserBO;
import com.cacau.api.model.dto.MediaMongoDTO;
import com.cacau.api.model.dto.MediaRequestDTO;
import com.cacau.api.model.dto.SubmissionMongoDTO;
import com.cacau.api.model.dto.SubmissionRequestDTO;
import com.cacau.api.model.dto.UserMongoDTO;
import com.cacau.api.model.dto.UserRequestDTO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestSubmissionBO {
	
	@Mock
	SubmissionMongoDTO sub;
	@Mock
	SubmissionRequestDTO subRequest;
	
	SubmissionBO subBO;
	
	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		ArrayList<MediaMongoDTO> mediaMongoDTOs = new ArrayList<>();
		//user
		assertNotNull(sub);
		when(sub.getId()).thenReturn("1");
		when(sub.getDescription()).thenReturn("descricao");
		when(sub.getStatus()).thenReturn("status");
		when(sub.getMedias()).thenReturn(mediaMongoDTOs);
		when(sub.getCreationDate()).thenReturn(new Date());
		when(sub.isActive()).thenReturn(true);
		
		//userRequest
		assertNotNull(subRequest);
		Optional<String> descricao = Optional.of("descricao2");
		Optional<String> status = Optional.of("status2");
		Optional<String> id = Optional.of("2");
		ArrayList<MediaRequestDTO> mediaMongoDTO = new ArrayList<>();
		Optional<ArrayList<MediaRequestDTO>> mediaMongoDTOs2 = Optional.of(mediaMongoDTO);
		when(subRequest.isActive()).thenReturn(true);
		when(subRequest.getDescription()).thenReturn(descricao);
		when(subRequest.getStatus()).thenReturn(status);
		when(subRequest.getMedias()).thenReturn(mediaMongoDTOs2);
	}
	
	@Test
	public void testCreateUserBO() throws ValidationException {

		subBO = new SubmissionBO(sub);
		assertNotNull(subBO);
	}
	
	@Test
	public void testCreateUserRequestBO() throws ValidationException, PersistencyException {
		subBO = new SubmissionBO(subRequest);
		assertNotNull(subBO);
	}
	
	@Test
	public void testUpdateUserRequestBO() throws Exception {
		subBO = new SubmissionBO(sub);
		subBO.updateWith(subRequest);
		assertEquals(subBO.getId(), "1");
	}
}
