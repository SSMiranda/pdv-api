package com.mirandasidney.pdv.api.controller.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtToken {
    private final String token;
    private final String type;

    public JwtToken(String token) {
        this.token = token;
        this.type = "Bearer";
    }

    @JsonProperty("token")
    String getToken() {
        return token;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }
}
