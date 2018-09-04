package com.cacau.api.model.bo;

import com.cacau.api.model.dto.ChallengeMongoDTO;
import com.cacau.api.model.dto.ChallengeRequestDTO;
import com.cacau.api.validator.Validator;
import com.cacau.api.exception.ValidationException;
import java.util.Date;

public class ChallengeBO {

	private String description;

	private String id;

	public Date timestamp;

	public Date start_time;

	public Date end_time;

	private Boolean active;

	Validator validator = new Validator();

	public ChallengeBO(ChallengeRequestDTO challenge) throws ValidationException {
		setDescription(challenge.getDescription().get());
		setStart_time(challenge.getStart_time());
		setEnd_time(challenge.getEnd_time());
		setActive(challenge.isActive());
		timestamp = new Date();
		checkTime(start_time, end_time);
	}

	public ChallengeBO(ChallengeMongoDTO challenge) throws ValidationException {
		setDescription(challenge.getDescription());
		setStart_time(challenge.getStart_time());
		setEnd_time(challenge.getEnd_time());
		setActive(challenge.isActive());
		this.id = challenge.getId();
		this.timestamp = challenge.getTimestamp();
	}

	public ChallengeMongoDTO toMongoDTO() {
		ChallengeMongoDTO challenge = new ChallengeMongoDTO(description, id, timestamp, start_time, end_time, active);
		return challenge;
	}

	public void updateWith(ChallengeRequestDTO challenge) throws ValidationException {
		setDescription(challenge.getDescription().isPresent() ? challenge.getDescription().get() : this.description);
		if (challenge.getStart_time() != null)
			this.start_time = challenge.getStart_time();
		if (challenge.getEnd_time() != null)
			this.end_time = challenge.getEnd_time();
		if (challenge.isActive() != null)
			this.active = challenge.isActive();
		checkTime(start_time, end_time);

	}

	public ChallengeBO() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) throws ValidationException {
		if (validator.validateStringCharacter(description, 1, 500)) {
			this.description = description;
		} else {
			throw new ValidationException("DESCRIPTION_TOO_BIG_OR_TOO_SMALL");
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	// Couldn't think of a better method name
	public void checkTime(Date startTime, Date endTime) throws ValidationException {
		if (!validator.validateDateRange(startTime, endTime))
			throw new ValidationException("END_TIME_PRIOR_TO_BEGIN_TIME");

	}

	@Override
	public String toString() {
		return "Challenge [description=" + description + ", id=" + id + ", timestamp=" + timestamp + ", start_time="
				+ start_time + ", end_time=" + end_time + ", active=" + active + "]";
	}
}
