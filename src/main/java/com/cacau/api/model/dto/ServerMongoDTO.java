package com.cacau.api.model.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "servers")
public class ServerMongoDTO {
    @Id
    private String id;
    private String serverId;
    private String serverSecret;
    private String tokenType;
    private String tokenValidType;

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getServerSecret() {
        return serverSecret;
    }

    public void setServerSecret(String serverSecret) {
        this.serverSecret = serverSecret;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getTokenValidType() {
        return tokenValidType;
    }

    public void setTokenValidType(String tokenValidType) {
        this.tokenValidType = tokenValidType;
    }

}
