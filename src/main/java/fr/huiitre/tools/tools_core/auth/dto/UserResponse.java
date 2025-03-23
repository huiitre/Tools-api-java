package fr.huiitre.tools.tools_core.auth.dto;

import fr.huiitre.tools.tools_core.user.model.User;
import java.time.LocalDateTime;

public class UserResponse {
    private Integer id;
    private String email;
    private String name;
    private String token;
    private LocalDateTime tokenDate;
    private Integer isActive;

    public UserResponse() {
    }

    // Construction à partir du modèle User
    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.token = user.getRememberToken();
        this.tokenDate = user.getTokenDate();
        this.isActive = user.getIsActive();
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public LocalDateTime getTokenDate() {
        return tokenDate;
    }
    public void setTokenDate(LocalDateTime tokenDate) {
        this.tokenDate = tokenDate;
    }
    public Integer getIsActive() {
        return isActive;
    }
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
}
