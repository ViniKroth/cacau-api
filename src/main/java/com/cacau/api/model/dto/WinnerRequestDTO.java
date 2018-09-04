package com.cacau.api.model.dto;

import java.util.Optional;

public class WinnerRequestDTO {

    private Optional<Integer> order;
    private Optional<Integer> code;
    private Optional<String> id_submission;

    public WinnerRequestDTO(Optional<Integer> order, Optional<Integer> code, Optional<String> id_submission) {
        this.order = order;
        this.code = code;
        this.id_submission = id_submission;
    }

    public Optional<Integer> getOrder() {
        return order;
    }

    public void setOrder(Optional<Integer> order) {
        this.order = order;
    }

    public Optional<Integer> getCode() {
        return code;
    }

    public void setCode(Optional<Integer> code) {
        this.code = code;
    }

    public Optional<String> getId_submission() {
        return id_submission;
    }

    public void setId_submission(Optional<String> id_submission) {
        this.id_submission = id_submission;
    }

    @Override
    public String toString() {
        return "WinnerRequestDTO [order= " + order + ", code= " + code + ", id_submission= " + id_submission + "]";
    }
}
