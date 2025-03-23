package fr.huiitre.tools.tools_core.history.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "history", schema = "tools_core")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhistory", nullable = false)
    private Integer id;

    @Column(name = "iduser", nullable = false)
    private Integer idUser;

    @Column(name = "idmodule", nullable = false)
    private Integer idModule;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "event_type", nullable = false, length = 50)
    private String eventType;

    @Column(name = "event_description", columnDefinition = "text")
    private String eventDescription;

    // Constructeur par défaut
    public History() {
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdModule() {
        return idModule;
    }

    public void setIdModule(Integer idModule) {
        this.idModule = idModule;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
}
