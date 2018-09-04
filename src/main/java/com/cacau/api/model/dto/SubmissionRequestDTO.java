package com.cacau.api.model.dto;

import com.cacau.api.model.Media;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class SubmissionRequestDTO {

    private Optional<String> description;

    private boolean active;

    public Date creationDate;

    private Optional<String> id;

    private Optional<String> status;

    private Optional<ArrayList<MediaRequestDTO>> medias;

    public SubmissionRequestDTO(Optional<String> id, Optional<String> description, Date creationDate, Optional<String> status, Optional<ArrayList<MediaRequestDTO>>medias) {
        this.description = description;
        this.active = true;
        this.id = id;
        this.status = status;
        this.creationDate = creationDate;
        this.medias=medias;
    }

    public SubmissionRequestDTO() {
    }

    public Optional<String> getDescription() { return description; }

    public void setDescription(Optional<String> description) { this.description = description; }

    public boolean isActive() { return active; }

    public void setActive(boolean active) { this.active = active; }

    public Optional<String> getId() { return id; }

    public void setId(Optional<String> id) { this.id = id; }

    public Optional<String> getStatus() { return status; }

    public void setStatus(Optional<String> status) { this.status = status; }

    public Date getCreationDate() { return creationDate; }

    public Optional<ArrayList<MediaRequestDTO>> getMedias() {
        return medias;
    }

    public void setMedias(Optional<ArrayList<MediaRequestDTO>> medias) {
        this.medias = medias;
    }

    @Override
    public String toString() {
        return "SubmissionRequestDTO [description=" + description + " , status=" + status + " , active=" + active + " , id=" + id + " , creationDate=" + creationDate + "]";
    }
}
