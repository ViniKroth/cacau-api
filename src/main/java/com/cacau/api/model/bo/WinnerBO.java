package com.cacau.api.model.bo;

import com.cacau.api.exception.PersistencyException;
import com.cacau.api.exception.ValidationException;
import com.cacau.api.model.dto.WinnerMongoDTO;
import com.cacau.api.model.dto.WinnerRequestDTO;
import com.cacau.api.service.SubmissionService;
import com.cacau.api.validator.Validator;

public class WinnerBO {
    private int order;
    private int code;
    private String id_submission;

    public WinnerBO (WinnerRequestDTO winner) throws PersistencyException, ValidationException{
        setOrder(winner.getOrder().get());
        setCode(winner.getCode().get());
        setId_submission(winner.getId_submission().get());
    }

    public void updateWith(WinnerRequestDTO winner) throws Exception {
        setOrder(winner.getOrder().isPresent() ? winner.getOrder().get() : this.order);
        setCode(winner.getCode().isPresent() ? winner.getCode().get() : this.code);
        setId_submission(winner.getId_submission().isPresent() ? winner.getId_submission().get() : this.id_submission);
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) throws ValidationException {
            if (order > 0) {
                this.order = order;
            } else throw new ValidationException("ORDER_MUST_BE_POSITIVE");
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) throws ValidationException {
        if(code >= 0) {
            this.code = code;
        } else throw new ValidationException("CODE_CANNOT_BE_NEGATIVE");
    }

    public String getId_submission() {
        return id_submission;
    }

    public void setId_submission(String id_submission) throws ValidationException {
        SubmissionService submissionService = new SubmissionService();
        if (submissionService.get(id_submission).isPresent()) {
            this.id_submission = id_submission;
        } else throw new ValidationException("SUBMISSION_DOES_NOT_EXIST");
    }

    public WinnerMongoDTO toMongoDTO(WinnerBO winnerBO) {
        WinnerMongoDTO winner = new WinnerMongoDTO(this.order, this.code, this.id_submission);
        return winner;
    }

    @Override
    public String toString() {
        return "WinnerBO [order= " + order + ", code= " + code + ", id_submission= " + id_submission + "]";
    }
}
