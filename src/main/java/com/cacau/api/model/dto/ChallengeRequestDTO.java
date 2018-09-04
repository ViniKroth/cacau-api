package com.cacau.api.model.dto;


import java.util.Date;
import java.util.Optional;

public class ChallengeRequestDTO {
    private Optional<String> description;
    private Optional<String> id;
    public Date timestamp;
    public Date start_time;
    public Date end_time;
    private Boolean active;


    public ChallengeRequestDTO(Optional<String> description, Optional<String> id, Date timestamp, Date start_time, Date end_time) {
        this.description = description;
        this.id = id;
        this.timestamp = timestamp;
        this.start_time = start_time;
        this.end_time = end_time;
        this.active = true;
    }

    public ChallengeRequestDTO() {
    }

    public Optional<String> getDescription() {
        return description;
    }

    public void setDescription(Optional<String> description) {
        this.description = description;
    }

    public Optional<String> getId() {
        return id;
    }

    public void setId(Optional<String> id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Challenge [description=" + description + ", id=" + id + ", timestamp=" + timestamp + ", start_time=" + start_time + ", end_time=" + end_time + ", active=" + active + "]";
    }
}
