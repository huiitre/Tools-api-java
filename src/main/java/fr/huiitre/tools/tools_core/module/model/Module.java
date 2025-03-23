package fr.huiitre.tools.tools_core.module.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "module", schema = "tools_core")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmodule", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "is_active")
    private Integer isActive = 1;

    @Column(name = "picture", length = 255)
    private String picture;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "code", nullable = false, length = 255)
    private String code;

    @Column(name = "idmodule_group", length = 255)
    private String idmoduleGroup;

    // Constructeur par défaut
    public Module() {
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

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIdmoduleGroup() {
        return idmoduleGroup;
    }

    public void setIdmoduleGroup(String idmoduleGroup) {
        this.idmoduleGroup = idmoduleGroup;
    }
}
