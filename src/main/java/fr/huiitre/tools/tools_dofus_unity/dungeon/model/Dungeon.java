package fr.huiitre.tools.tools_dofus_unity.dungeon.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dungeon", schema = "tools_dofus_unity")
public class Dungeon {

    @Id
    @Column(name = "iddungeon", nullable = false)
    private Integer iddungeon;

    @Column(name = "name", nullable = false, columnDefinition = "text")
    private String name;

    @Column(name = "slug", nullable = false, columnDefinition = "text")
    private String slug;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "iditem_key")
    private Integer iditemKey;

    @Column(name = "event_galet", nullable = false)
    private Boolean eventGalet;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "url_dofusdb", columnDefinition = "text")
    private String urlDofusdb;

    @Column(name = "stone_iditem")
    private Integer stoneIditem;

    public Dungeon() {
    }

    public Integer getIddungeon() {
        return iddungeon;
    }

    public void setIddungeon(Integer iddungeon) {
        this.iddungeon = iddungeon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getIditemKey() {
        return iditemKey;
    }

    public void setIditemKey(Integer iditemKey) {
        this.iditemKey = iditemKey;
    }

    public Boolean getEventGalet() {
        return eventGalet;
    }

    public void setEventGalet(Boolean eventGalet) {
        this.eventGalet = eventGalet;
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

    public String getUrlDofusdb() {
        return urlDofusdb;
    }

    public void setUrlDofusdb(String urlDofusdb) {
        this.urlDofusdb = urlDofusdb;
    }

    public Integer getStoneIditem() {
        return stoneIditem;
    }

    public void setStoneIditem(Integer stoneIditem) {
        this.stoneIditem = stoneIditem;
    }
}
