package com.cacau.api.model.bo;

import com.cacau.api.exception.PersistencyException;
import com.cacau.api.exception.ValidationException;
import com.cacau.api.model.dto.MediaMongoDTO;
import com.cacau.api.model.dto.MediaRequestDTO;
import com.cacau.api.model.dto.SubmissionMongoDTO;
import com.cacau.api.model.dto.SubmissionRequestDTO;
import com.cacau.api.validator.Validator;
import org.springframework.data.annotation.Id;
import com.cacau.util.ApiKey;

import java.util.ArrayList;
import java.util.Date;

public class SubmissionBO {

	private ArrayList<MediaBO> medias;

	private String description;

	public Date creationDate;

	@Id
	private String id;
	/*
	 * Should be 4 constants: DRAFT, PENDENT, APROVED & DECLINED.
	 */
	private String status;

	private boolean active;

	Validator validator = new Validator();

	// The status must be set to either draft or pendent
	public SubmissionBO(SubmissionRequestDTO submission) throws PersistencyException, ValidationException {
		setDescription(submission.getDescription().get());
		setActive(submission.isActive());
		setStatus(submission.getStatus().get());
		creationDate = new Date();

		ArrayList<MediaBO> medias = new ArrayList<>();
		ArrayList<MediaRequestDTO> mediaRequestDTOs = submission.getMedias().get();
		for (MediaRequestDTO requestDTO : mediaRequestDTOs) {
			MediaBO media = new MediaBO(requestDTO);
			medias.add(media);
		}
		this.medias = medias;
	}

	public SubmissionBO(SubmissionMongoDTO submission) {
		this.description = submission.getDescription();
		this.id = submission.getId();
		this.status = submission.getStatus();
		this.creationDate = submission.getCreationDate();

		ArrayList<MediaBO> medias = new ArrayList<>();
		ArrayList<MediaMongoDTO> mediaMongoDTOs = submission.getMedias();
		for (MediaMongoDTO m : mediaMongoDTOs) {
			MediaBO media = new MediaBO(m);
			medias.add(media);
		}
		this.medias = medias;
		this.active = submission.isActive();
	}

	public void updateWith(SubmissionRequestDTO submission) throws Exception {
		setDescription(
				submission.getDescription().isPresent() ? submission.getDescription().get() : this.getDescription());
		if (submission.getMedias().isPresent()) {
			ArrayList<MediaBO> medias = new ArrayList<>();
			ArrayList<MediaRequestDTO> mediaRequestDTOs = submission.getMedias().get();
			for (MediaRequestDTO requestDTO : mediaRequestDTOs) {
				MediaBO media = new MediaBO(requestDTO);
				medias.add(media);
			}
			this.medias = medias;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) throws ValidationException {
		if (validator.validateStringCharacter(description, 1, 140))
			this.description = description;
		else
			throw new ValidationException("DESCRIPTION_TOO_BIG_OR_TOO_SMALL");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SubmissionMongoDTO toMongoDTO() {
		ArrayList<MediaMongoDTO> medias2 = new ArrayList<>();

		for (MediaBO media : this.medias) {
			MediaMongoDTO mediaMongoDTO = media.toMongoDTO();
			medias2.add(mediaMongoDTO);
		}

		SubmissionMongoDTO submission = new SubmissionMongoDTO(description, active, creationDate, status, id, medias2);
		return submission;
	}
}