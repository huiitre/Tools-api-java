package fr.huiitre.tools.tools_dofus_unity.set.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item_has_set", schema = "tools_dofus_unity")
public class ItemHasSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iditem_has_set", nullable = false)
    private Integer iditemHasSet;

    @Column(name = "iditem", nullable = false)
    private Integer iditem;

    @Column(name = "idset", nullable = false)
    private Integer idset;

    @Column(name = "multiplier")
    private Integer multiplier = 1;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public ItemHasSet() {
    }

    public Integer getIditemHasSet() {
        return iditemHasSet;
    }

    public void setIditemHasSet(Integer iditemHasSet) {
        this.iditemHasSet = iditemHasSet;
    }

    public Integer getIditem() {
        return iditem;
    }

    public void setIditem(Integer iditem) {
        this.iditem = iditem;
    }

    public Integer getIdset() {
        return idset;
    }

    public void setIdset(Integer idset) {
        this.idset = idset;
    }

    public Integer getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Integer multiplier) {
        this.multiplier = multiplier;
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
