package com.cacau.api.model.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Optional;

public class VersionDTO {

    public static final String CURRENT_VERSION = "0.0.5";

    private String version;

    public VersionDTO() {
        this.version = VersionDTO.CURRENT_VERSION;
    }

    public VersionDTO(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "VersionDTO [version=" + version + "]";
    }
}
