package fr.huiitre.tools.tools_core.release_note.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "release_note", schema = "tools_core")
public class ReleaseNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrelease", nullable = false)
    private Integer idrelease;

    @Column(name = "version", nullable = false, length = 255)
    private String version;

    @Column(name = "released_at", nullable = false)
    private LocalDateTime releasedAt;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "idmodule", nullable = false)
    private Integer idmodule;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Constructeur par défaut
    public ReleaseNote() {
    }

    // Getters et Setters
    public Integer getIdrelease() {
        return idrelease;
    }

    public void setIdrelease(Integer idrelease) {
        this.idrelease = idrelease;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LocalDateTime getReleasedAt() {
        return releasedAt;
    }

    public void setReleasedAt(LocalDateTime releasedAt) {
        this.releasedAt = releasedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIdmodule() {
        return idmodule;
    }

    public void setIdmodule(Integer idmodule) {
        this.idmodule = idmodule;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
