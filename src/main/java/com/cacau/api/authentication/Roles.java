package com.cacau.api.authentication;

public enum Roles {
    ADMIN("ADMIN"), USER("USER");

    private final String text;

    Roles(final String text) {
            this.text = text;
        }

    @Override
    public String toString() {
        return text;
    }
}
