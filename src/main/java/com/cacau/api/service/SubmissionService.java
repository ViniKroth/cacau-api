package com.cacau.api.service;

import com.cacau.api.model.bo.MediaBO;
import com.cacau.api.model.bo.SubmissionBO;
import com.cacau.api.model.dto.MediaMongoDTO;
import com.cacau.api.model.dto.MediaRequestDTO;
import com.cacau.api.model.dto.SubmissionMongoDTO;
import com.cacau.api.model.dto.SubmissionRequestDTO;
import com.cacau.api.repository.SubmissionRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

	@Autowired
	SubmissionRepository submissionRepository;

	SubmissionBO submissionBO;
	private SubmissionRepository submissionRepository1;

	public SubmissionRequestDTO create(SubmissionRequestDTO submission) throws Exception {
		try {
			SubmissionMongoDTO submissionMongo = new SubmissionBO(submission).toMongoDTO();
			return submissionRepository.insert(submissionMongo).toRequestDTO();

		} catch (Exception e) {
			throw e;
		}
	}

	public List<SubmissionRequestDTO> getAll() {
		ArrayList<SubmissionRequestDTO> submissions = new ArrayList<>();
		for (SubmissionMongoDTO s : submissionRepository.findAll()) {
			if (s.isActive()) {
				submissions.add(s.toRequestDTO());
			}
		}
		return submissions;
	}

	public Optional<SubmissionRequestDTO> get(String id) {
		Optional<SubmissionMongoDTO> submission = submissionRepository.findById(id);
		Optional<SubmissionRequestDTO> submissionRequest = null;
		if (submission.isPresent())
			submissionRequest = Optional.of(submission.get().toRequestDTO());
		return submissionRequest;
	}

	public SubmissionRequestDTO update(String id, SubmissionRequestDTO submission) throws Exception {
		Optional<SubmissionMongoDTO> mongoSubmission = submissionRepository.findById(id);
		if (mongoSubmission.isPresent()) {
			submissionBO = new SubmissionBO(mongoSubmission.get());
			submissionBO.updateWith(submission);
			return submissionRepository.save(submissionBO.toMongoDTO()).toRequestDTO();
		} else
			throw new Exception("ID_INFORMADA_INVALIDA");
	}

	public void delete(String id) throws Exception {
		Optional<SubmissionMongoDTO> submission = submissionRepository.findById(id);
		if (submission.isPresent()) {
			submission.get().setActive(false);
			submissionRepository.save(submission.get());
		} else
			throw new Exception("ID_INFORMADA_INVALIDA");

	}

	public MediaRequestDTO insertMedia(String id, MediaRequestDTO media) throws Exception {
		media.setId(Optional.of(new ObjectId().toString()));
		MediaBO mediaBO = new MediaBO(media);
		MediaMongoDTO mediaMongoDTO = mediaBO.toMongoDTO();
		Optional<SubmissionMongoDTO> submission = submissionRepository.findById(id);
		SubmissionMongoDTO aux = submission.get();
		aux.getMedias().add(mediaMongoDTO);
		submission = Optional.of(aux);
		submissionRepository.save(submission.get());
		return media;
	}
}
