package fr.huiitre.tools.tools_dtl.user_activity.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_activity", schema = "tools_dtl")
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser_activity", nullable = false)
    private Integer iduserActivity;

    @Column(name = "idhistoric", nullable = false)
    private Integer idhistoric;

    @Column(name = "iduser", nullable = false)
    private Integer iduser;

    @Column(name = "login", columnDefinition = "text", nullable = false)
    private String login;

    @Column(name = "token", columnDefinition = "text")
    private String token;

    @Column(name = "ip_address", columnDefinition = "inet")
    private String ipAddress;

    @Column(name = "user_agent", columnDefinition = "text")
    private String userAgent;

    @Column(name = "details", columnDefinition = "text")
    private String details;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public UserActivity() {
    }

    public Integer getIduserActivity() {
        return iduserActivity;
    }

    public void setIduserActivity(Integer iduserActivity) {
        this.iduserActivity = iduserActivity;
    }

    public Integer getIdhistoric() {
        return idhistoric;
    }

    public void setIdhistoric(Integer idhistoric) {
        this.idhistoric = idhistoric;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
