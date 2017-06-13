package com.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Cecilia on 9/6/2017.
 */
public class LoginResponseWrapper {

    @JsonProperty
    String sessionId;

    public LoginResponseWrapper(String idSession){
        this.sessionId = idSession;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
