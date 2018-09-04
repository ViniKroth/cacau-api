package com.cacau.api.model.dto;

import com.cacau.api.model.bo.MediaBO;

import java.util.Date;
import java.util.Optional;

public class MediaRequestDTO {
    private Optional<String> id;

    private Optional<String> mediaType;

    public Date timestamp;

    private Optional<String> content;

    public MediaRequestDTO(Optional<String> id, Optional<String> mediaType, Date timestamp, Optional<String> content){
        this.id=id;
        this.mediaType=mediaType;
        this.timestamp=timestamp;
        this.content=content;
    }

    public MediaRequestDTO(){

    }

    public Optional<String> getId() {
        return id;
    }

    public void setId(Optional<String> id) {
        this.id = id;
    }

    public Optional<String> getMediaType() {
        return mediaType;
    }

    public void setMediaType(Optional<String> mediaType) {
        this.mediaType = mediaType;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Optional<String> getContent() {
        return content;
    }

    public void setContent(Optional<String> content) {
        this.content = content;
    }

    @Override
    public String toString(){
        return "MediaRequestDTO [id= " + id + ", mediaType= " + mediaType + ", creationDate= " + timestamp + ", content= " + content + "]";
    }
}
