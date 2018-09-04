package com.cacau.api.model.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Optional;

@Document(collection = "winners")
public class WinnerMongoDTO {

    private int order;
    private int code;
    private String id_submission;

    public WinnerMongoDTO(int order, int code, String id_submission) {
        this.order = order;
        this.code = code;
        this.id_submission = id_submission;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getId_submission() {
        return id_submission;
    }

    public void setId_submission(String id_submission) {
        this.id_submission = id_submission;
    }

    public WinnerRequestDTO toRequestDTO() {
        WinnerRequestDTO winner = new WinnerRequestDTO(Optional.of(order), Optional.of(code), Optional.of(id_submission));
        return winner;
    }

    @Override
    public String toString() {
        return "WinnerMongoDTO [order= " + order + ", code= " + code + ", id_submission= " + id_submission + "]";
    }
}
