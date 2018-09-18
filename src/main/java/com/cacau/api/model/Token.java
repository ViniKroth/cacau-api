package com.cacau.api.model;

import com.cacau.api.model.dto.UserMongoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Token {
    @JsonProperty("acess_token")
    private String acess_token;
    @JsonProperty("scope")
    private String scope;
    @JsonProperty("user_id")
    private String user_id;
    @JsonProperty("username")
    private String username;

    public Token(UserMongoDTO user, String acess_token) {
        this.acess_token = acess_token;
        this.scope = user.isAdmin() ? "ADMIN" : "USER";
        this.user_id = user.getId();
        this.username = user.getEmail();

    }

    public String getAcess_token() {
        return acess_token;
    }

    public void setAcess_token(String acess_token) {
        this.acess_token = acess_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
