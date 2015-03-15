package com.draka.hardi;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {

	
    public String loginToken;
    public int xpiredToken;

    @JsonProperty("access_token")
    
    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    
    }
 @JsonProperty("expires_in")
    
    public int getXpiredToken() {
        return xpiredToken;
    }

    public void setXpiredToken(int xpiredToken) {
        this.xpiredToken = xpiredToken;
    
    }
}