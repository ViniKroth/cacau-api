package com.cacau.api.model.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Document(collection = "challenges")
public class ChallengeMongoDTO {
	@NotBlank
	@NotNull
	private String description;
	@Id
	private String id;

	public Date timestamp;

	@NotBlank
	@NotNull
	public Date start_time;

	@NotBlank
	@NotNull
	public Date end_time;

	private Boolean active;

	public ChallengeMongoDTO(String description, String id, Date timestamp, Date start_time, Date end_time,
			Boolean active) {
		this.description = description;
		this.id = id;
		this.timestamp = timestamp;
		this.start_time = start_time;
		this.end_time = end_time;
		this.active = active;
	}

	public ChallengeMongoDTO() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public ChallengeRequestDTO toRequestDTO() {
		ChallengeRequestDTO challenge = new ChallengeRequestDTO(Optional.of(description), Optional.of(id), timestamp, start_time, end_time);
		return challenge;
	}
}
