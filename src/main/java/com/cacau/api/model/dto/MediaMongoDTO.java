package com.cacau.api.model.dto;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;

import javax.xml.bind.DatatypeConverter;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Optional;

public class MediaMongoDTO {
    @Id
    private String id;

	private String mediaType;

	private Date timestamp;

    @NotBlank
    @NotNull
    private Binary content;

    public MediaMongoDTO(String id, String mediaType, Date timestamp, Binary content){
        this.id=id;
        this.mediaType=mediaType;
        this.timestamp=timestamp;
        this.content=content;
    }

	public MediaMongoDTO() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public Date getTimestamp() {
		return timestamp;
	}

    public Binary getContent() {
        return content;
    }

    public void setContet(Binary content) {
        this.content = content;
    }

    public MediaRequestDTO toRequestDTO(){
         String newContent = toBase64(content.getData());
        MediaRequestDTO media = new MediaRequestDTO(Optional.of(id),Optional.of(mediaType),timestamp,Optional.of(newContent));
        return media;
    }

    private static String toBase64(byte[] array) {
        return DatatypeConverter.printBase64Binary(array);
    }

    @Override
    public String toString(){
        return "MediaMongoDTO [id= "+ id + ", mediaType= " + mediaType + ", creationDate= " + timestamp + ", content= " + content + "]";
    }
}