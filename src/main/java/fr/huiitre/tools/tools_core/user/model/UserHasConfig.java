package fr.huiitre.tools.tools_core.user.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_has_config", schema = "tools_core")
public class UserHasConfig {

    @EmbeddedId
    private UserHasConfigId id;

    @Column(name = "value", length = 255)
    private String value;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public UserHasConfig() {
    }

    public UserHasConfig(UserHasConfigId id, String value, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.value = value;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserHasConfigId getId() {
        return id;
    }

    public void setId(UserHasConfigId id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
