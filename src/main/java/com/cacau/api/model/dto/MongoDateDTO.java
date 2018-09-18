package com.cacau.api.model.dto;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class MongoDateDTO {
	@Id
	private String id;
	
	private Date creationDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
