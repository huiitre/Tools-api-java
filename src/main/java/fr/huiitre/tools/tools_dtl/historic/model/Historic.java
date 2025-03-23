package fr.huiitre.tools.tools_dtl.historic.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historic", schema = "tools_dtl")
public class Historic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhistoric", nullable = false)
    private Integer idhistoric;

    @Column(name = "module", length = 100)
    private String module;

    @Column(name = "priority", nullable = false)
    private Short priority = 1;

    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "is_visible", nullable = false)
    private Boolean isVisible = true;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Historic() {
    }

    public Integer getIdhistoric() {
        return idhistoric;
    }

    public void setIdhistoric(Integer idhistoric) {
        this.idhistoric = idhistoric;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Short getPriority() {
        return priority;
    }

    public void setPriority(Short priority) {
        this.priority = priority;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
