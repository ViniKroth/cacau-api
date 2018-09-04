package com.cacau.api.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import com.cacau.api.exception.ValidationException;
import com.cacau.api.model.dto.PrizeMongoDTO;
import com.cacau.api.model.dto.PrizeRequestDTO;
import com.cacau.api.validator.Validator;

public class PrizeBO {

	private String id;

	private String description;

	private int prizeRangeBegin;

	private int prizeRangeEnd;

	Validator validator = new Validator();

	public PrizeBO(String id) {
		this.id = id;
	}

	public PrizeBO(PrizeMongoDTO prize) throws ValidationException {
		this.setId(prize.getId());
		this.setDescription(prize.getDescription());
		this.setPrizeRangeBegin(prize.getPrizeRangeBegin());
		this.setPrizeRangeEnd(prize.getPrizeRangeEnd());
	}

	public PrizeBO(PrizeRequestDTO prize) throws ValidationException {
		this.setId(prize.getId().get());
		this.setDescription(prize.getDescription().get());
		this.setPrizeRangeBegin(prize.getPrizeRangeBegin().get());
		this.setPrizeRangeEnd(prize.getPrizeRangeEnd().get());
	}

	public PrizeBO() {

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

		public void setDescription(String description) throws ValidationException {
			this.description = description;

	}

	public int getPrizeRangeEnd() {
		return prizeRangeEnd;
	}

	public void setPrizeRangeEnd(int prizeRangeEnd) throws ValidationException {
		if (prizeRangeEnd > 0)
			this.prizeRangeEnd = prizeRangeEnd;
		else
			throw new ValidationException("O raio deve ser maior que 0");

	}

	public int getPrizeRangeBegin() {
		return prizeRangeBegin;
	}

	public void setPrizeRangeBegin(int prizeRangeBegin) {
		this.prizeRangeBegin = prizeRangeBegin;
	}

	@Override
	public String toString() {
		return "PrizeBO [id=" + id + ", description=" + description + ", prizeRangeBegin=" + prizeRangeBegin
				+ ", prizeRangeEnd=" + prizeRangeEnd + "]";
	}

	public PrizeMongoDTO toMongoDTO() {
		PrizeMongoDTO prize = new PrizeMongoDTO(id, description, prizeRangeBegin, prizeRangeEnd);
		return prize;
	}

}
