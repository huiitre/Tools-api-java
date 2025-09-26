package fr.huiitre.tools.tools_health.weight_log.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "weight_log", schema = "tools_health")
public class WeightLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)               // BIGINT IDENTITY
    private Long id;

    @Column(name = "iduser", nullable = false)           // BIGINT
    private Long iduser;

    @Column(name = "logged_at", nullable = false)        // TIMESTAMPTZ
    private OffsetDateTime loggedAt;

    @Column(name = "weight_kg", nullable = false, precision = 6, scale = 3) // NUMERIC(6,3)
    private BigDecimal weightKg;

    @Column(name = "notes", columnDefinition = "text")   // TEXT
    private String notes;

    @Column(name = "created_at", nullable = false)       // TIMESTAMPTZ (default now())
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)       // TIMESTAMPTZ (updated by trigger)
    private OffsetDateTime updatedAt;

    public WeightLog() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIduser() { return iduser; }
    public void setIduser(Long iduser) { this.iduser = iduser; }

    public OffsetDateTime getLoggedAt() { return loggedAt; }
    public void setLoggedAt(OffsetDateTime loggedAt) { this.loggedAt = loggedAt; }

    public BigDecimal getWeightKg() { return weightKg; }
    public void setWeightKg(BigDecimal weightKg) { this.weightKg = weightKg; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
}
