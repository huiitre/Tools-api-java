package fr.huiitre.tools.tools_core.auth.dto;

public class LoginWithGoogleRequest {
    private String googleJwt;

    public LoginWithGoogleRequest()
    {

    }

    public String getGoogleJwt() {
        return googleJwt;
    }

    public void setGoogleJwt(String googleJwt) {
        this.googleJwt = googleJwt;
    }
}
