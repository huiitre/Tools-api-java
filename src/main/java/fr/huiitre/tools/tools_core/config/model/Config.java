package fr.huiitre.tools.tools_core.config.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "config", schema = "tools_core")
public class Config {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idconfig")
    private Integer id;

    @Column(name = "code", nullable = false, length = 255)
    private String code;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "type", nullable = false, length = 255)
    private String type = "string"; // valeur par défaut 'string'

    @Column(name = "is_active", nullable = false)
    private Integer isActive = 1;

    @Column(name = "comments", length = 255)
    private String comments;

    @Column(name = "idmodule")
    private Integer idModule;

    @Column(name = "idconfig_subcategory", nullable = false)
    private Integer idConfigSubcategory;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructeur par défaut
    public Config() {
    }

    // Getters et Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getIdModule() {
        return idModule;
    }

    public void setIdModule(Integer idModule) {
        this.idModule = idModule;
    }

    public Integer getIdConfigSubcategory() {
        return idConfigSubcategory;
    }

    public void setIdConfigSubcategory(Integer idConfigSubcategory) {
        this.idConfigSubcategory = idConfigSubcategory;
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
}
