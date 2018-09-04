package com.cacau.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "prize")
public class Prize {
	@Id
	private String id;
	
	@NotBlank
	@NotNull
	private String description;
	 
	private int order;
	
	
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
	
	public int getOrder() {
		return order;
	}
	
	public void setOrder(int order) {
		this.order = order;
	}
	
	@Override
	public String toString() {
		return "Prize [id=" + id + ", description=" + description + ", order=" + order + "]";
	}
	
}



