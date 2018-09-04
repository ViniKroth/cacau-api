package com.cacau.api.model.bo;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.DatatypeConverterInterface;

import com.cacau.api.exception.PersistencyException;
import com.cacau.api.exception.ValidationException;
import com.cacau.api.model.dto.MediaMongoDTO;
import com.cacau.api.model.dto.MediaRequestDTO;
import com.cacau.api.validator.Validator;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.Optional;

public class MediaBO {

    @Id
    private String id;

    private String mediaType;

    public Date timestamp;

    private Binary content;

    Validator validator = new Validator();
    //This id will be generated where?
    public MediaBO(MediaRequestDTO media) throws PersistencyException, ValidationException {
        Binary newContent = new Binary(toByteArray(media.getContent().get()));
        setId(media.getId().get());
        setMediaType(media.getMediaType().get());
        setContent(newContent);
        timestamp = new Date();

    }
    
    public MediaBO(MediaMongoDTO media) {
    	this.id = media.getId();
    	this.mediaType = media.getMediaType();
    	this.content = media.getContent();
    	this.timestamp = media.getTimestamp();
    	
    }

    private static byte[] toByteArray(String s){
        byte[] b = DatatypeConverter.parseBase64Binary(s);
        return b;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Binary getContent() {
        return content;
    }

    public void setContent(Binary content) {
        this.content = content;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public MediaMongoDTO toMongoDTO(){
        MediaMongoDTO media = new MediaMongoDTO(id,mediaType,timestamp,content);
        return media;
    }

    private static String toBase64(byte[] array) {
        return DatatypeConverter.printBase64Binary(array);
    }


    public MediaRequestDTO toRequestDTO() {
        String newContent = toBase64(content.getData());
        MediaRequestDTO requestDTO = new MediaRequestDTO(Optional.of(this.id), Optional.of(this.mediaType), this.timestamp, Optional.of(newContent));
        return requestDTO;
    }

}
