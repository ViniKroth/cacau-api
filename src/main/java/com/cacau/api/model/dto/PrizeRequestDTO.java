package com.cacau.api.model.dto;

import java.util.Optional;

public class PrizeRequestDTO {

	private Optional<Integer> prizeRangeBegin;

	private Optional<Integer> prizeRangeEnd;

	private Optional<String> id;

	private Optional<String> description;

	public PrizeRequestDTO(Optional<Integer> prizeRangeBegin, Optional<Integer> prizeRangeEnd, Optional<String> id, Optional<String> description) {
		this.prizeRangeBegin = prizeRangeBegin;
		this.prizeRangeEnd = prizeRangeEnd;
		this.id = id;
		this.description = description;
	}
	
	public PrizeRequestDTO() {
		
	}

	public Optional<String> getId() {
		return id;
	}

	public void setId(Optional<String> id) {
		this.id = id;
	}

	public Optional<String> getDescription() {
		return description;
	}

	public void setDescription(Optional<String> description) {
		this.description = description;
	}

	public Optional<Integer> getPrizeRangeBegin() {
		return prizeRangeBegin;
	}

	public void setPrizeRangeBegin(Optional<Integer> prizeRangeBegin) {
		this.prizeRangeBegin = prizeRangeBegin;
	}

	public Optional<Integer> getPrizeRangeEnd() {
		return prizeRangeEnd;
	}

	public void setPrizeRangeEnd(Optional<Integer> prizeRangeEnd) {
		this.prizeRangeEnd = prizeRangeEnd;
	}

}
