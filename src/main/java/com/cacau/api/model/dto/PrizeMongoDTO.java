package com.cacau.api.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Optional;

@Document(collection = "prizes")

public class PrizeMongoDTO {

	@Id
	private String id;
	
	@NotBlank
	@NotNull
	private String description;

	private int prizeRangeBegin;

	private int prizeRangeEnd;

	public PrizeMongoDTO(String id, String description, int prizeRangeBegin, int prizeRangeEnd) {

		this.id = id;
		this.description = description;
		this.setPrizeRangeBegin(prizeRangeBegin);
		this.setPrizeRangeEnd(prizeRangeEnd);
	}
	public PrizeMongoDTO() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrizeRangeEnd() {
		return prizeRangeEnd;
	}

	public void setPrizeRangeEnd(int prizeRangeEnd) {
		this.prizeRangeEnd = prizeRangeEnd;
	}

	public int getPrizeRangeBegin() {
		return prizeRangeBegin;
	}

	public void setPrizeRangeBegin(int prizeRangeBegin) {
		this.prizeRangeBegin = prizeRangeBegin;
	}
	
	public PrizeRequestDTO toRequestDTO() {
		PrizeRequestDTO prize = new PrizeRequestDTO(Optional.of(prizeRangeBegin),Optional.of(prizeRangeEnd),Optional.of(id),Optional.of(description));
		return prize;
	}
	

	@Override
	public String toString() {
		return "PrizeMongoDTO [id=" + id + ", description=" + description + ", prizeRangeBegin=" + prizeRangeBegin
				+ ", prizeRangeEnd=" + prizeRangeEnd + "]";
	}

}
