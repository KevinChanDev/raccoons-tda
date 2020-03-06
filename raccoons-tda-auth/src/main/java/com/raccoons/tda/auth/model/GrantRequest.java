package com.raccoons.tda.auth.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GrantRequest {

    @JsonProperty("grant_type")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String grantType;

    @JsonProperty("code")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String code;

    @JsonProperty("access_type")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String accessType;

    @JsonProperty("client_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String clientId;

    @JsonProperty("redirect_uri")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String redirectUri;

    public String getGrantType() {
        return grantType;
    }

    public String getCode() {
        return code;
    }

    public String getAccessType() {
        return accessType;
    }

    public String getClientId() {
        return clientId;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
