package com.cacau.api.model;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class Media {

    @Id
    private String id;

    private String mediaType;

    public Date timestamp;

    private Binary content;

    public Media() { timestamp = new Date(); }

    public String getId() { return id; }

    public Binary getContent() { return content; }

    public String getMediaType() { return mediaType; }

    public void setId(String id) { this.id = id; }

    public void setMediaType(String mediaType) { this.mediaType = mediaType; }

    public void setContent(Binary content) { this.content = content; }

    @Override
    public String toString() {
        return "Media [id: " + id + ", mediaType: " + mediaType + ", size: " + content.length() + "]";
    }
}
