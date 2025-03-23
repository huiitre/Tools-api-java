package fr.huiitre.tools.tools_core.user.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user", schema = "tools_core")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private Integer id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "remember_token", length = 100)
    private String rememberToken;

    @Column(name = "role", nullable = false, columnDefinition = "integer default 1")
    private Integer role;

    @Column(name = "is_active", nullable = false)
    private Integer isActive;

    @Column(name = "token_date")
    private LocalDateTime tokenDate;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "google_id", length = 255)
    private String googleId;

    @Column(name = "google_email", length = 100)
    private String googleEmail;

    @Column(name = "google_name", length = 255)
    private String googleName;

    @Column(name = "google_given_name", length = 255)
    private String googleGivenName;

    @Column(name = "google_family_name", length = 255)
    private String googleFamilyName;

    @Column(name = "google_picture", columnDefinition = "text")
    private String googlePicture;

    // Constructeur par défaut
    public User() {
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getTokenDate() {
        return tokenDate;
    }

    public void setTokenDate(LocalDateTime tokenDate) {
        this.tokenDate = tokenDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getGoogleEmail() {
        return googleEmail;
    }

    public void setGoogleEmail(String googleEmail) {
        this.googleEmail = googleEmail;
    }

    public String getGoogleName() {
        return googleName;
    }

    public void setGoogleName(String googleName) {
        this.googleName = googleName;
    }

    public String getGoogleGivenName() {
        return googleGivenName;
    }

    public void setGoogleGivenName(String googleGivenName) {
        this.googleGivenName = googleGivenName;
    }

    public String getGoogleFamilyName() {
        return googleFamilyName;
    }

    public void setGoogleFamilyName(String googleFamilyName) {
        this.googleFamilyName = googleFamilyName;
    }

    public String getGooglePicture() {
        return googlePicture;
    }

    public void setGooglePicture(String googlePicture) {
        this.googlePicture = googlePicture;
    }
}
