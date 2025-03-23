package fr.huiitre.tools.tools_core.config.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "config_subcategory", schema = "tools_core")
public class ConfigSubcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idconfig_subcategory")
    private Integer id;

    @Column(name = "code", nullable = false, length = 255)
    private String code;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "idconfig_category", nullable = false)
    private ConfigCategory configCategory;

    // Constructeur par défaut
    public ConfigSubcategory() {
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

    public ConfigCategory getConfigCategory() {
        return configCategory;
    }

    public void setConfigCategory(ConfigCategory configCategory) {
        this.configCategory = configCategory;
    }
}