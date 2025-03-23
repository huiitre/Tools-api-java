package fr.huiitre.tools.tools_dofus_unity.set.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "set_share", schema = "tools_dofus_unity")
public class SetShare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idshare", nullable = false)
    private Integer idshare;

    @Column(name = "idset", nullable = false)
    private Integer idset;

    @Column(name = "iduser", nullable = false)
    private Integer iduser;

    @Column(name = "token", nullable = false, length = 64)
    private String token;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public SetShare() {
    }

    public Integer getIdshare() {
        return idshare;
    }

    public void setIdshare(Integer idshare) {
        this.idshare = idshare;
    }

    public Integer getIdset() {
        return idset;
    }

    public void setIdset(Integer idset) {
        this.idset = idset;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
