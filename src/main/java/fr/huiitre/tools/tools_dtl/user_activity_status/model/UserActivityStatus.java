package fr.huiitre.tools.tools_dtl.user_activity_status.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_activity_status", schema = "tools_dtl")
public class UserActivityStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser_activity_status", nullable = false)
    private Integer iduserActivityStatus;

    @Column(name = "iduser_activity", nullable = false)
    private Integer iduserActivity;

    @Column(name = "iduser", nullable = false)
    private Integer iduser;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public UserActivityStatus() {
    }

    public Integer getIduserActivityStatus() {
        return iduserActivityStatus;
    }

    public void setIduserActivityStatus(Integer iduserActivityStatus) {
        this.iduserActivityStatus = iduserActivityStatus;
    }

    public Integer getIduserActivity() {
        return iduserActivity;
    }

    public void setIduserActivity(Integer iduserActivity) {
        this.iduserActivity = iduserActivity;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
