package com.cacau.api.model.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Document(collection = "submissions")
public class SubmissionMongoDTO {

	@NotNull
	@NotBlank
	private String description;

	private ArrayList<MediaMongoDTO> medias;

	private boolean active;

	public Date creationDate;
    @Id
	private String id;

    private String status;

	public SubmissionMongoDTO(String description, boolean active, Date creationDate, String status, String id,
			ArrayList<MediaMongoDTO> medias) {
		this.description = description;
		this.active = active;
		this.creationDate = creationDate;
		this.status = status;
		this.id = id;
		this.medias = medias;
	}

	public SubmissionMongoDTO() {

	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SubmissionRequestDTO toRequestDTO() {
		ArrayList<MediaRequestDTO> medias2 = new ArrayList<>();

		for (MediaMongoDTO media : this.medias) {
			MediaRequestDTO requestDTO = media.toRequestDTO();
			medias2.add(requestDTO);
		}
		SubmissionRequestDTO submission = new SubmissionRequestDTO(Optional.of(id), Optional.of(description), creationDate, Optional.of(status), Optional.of(medias2));
		return submission;
	}

	public ArrayList<MediaMongoDTO> getMedias() {

		return medias;
	}

	@Override
	public String toString() {
		return "SubmissionMongoDTO [description=" + description + " , status=" + status + " , active=" + active
				+ " , id=" + id + " , creationDate=" + creationDate + "]";
	}
}