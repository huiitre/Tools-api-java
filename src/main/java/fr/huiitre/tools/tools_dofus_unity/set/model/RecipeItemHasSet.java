package fr.huiitre.tools.tools_dofus_unity.set.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recipe_item_has_set", schema = "tools_dofus_unity")
public class RecipeItemHasSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrecipe_item_has_set", nullable = false)
    private Integer idrecipeItemHasSet;

    @Column(name = "iditem_has_set", nullable = false)
    private Integer iditemHasSet;

    @Column(name = "iditem", nullable = false)
    private Integer iditem;

    @Column(name = "quantity_already_obtained")
    private Integer quantityAlreadyObtained = 0;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public RecipeItemHasSet() {
    }

    public Integer getIdrecipeItemHasSet() {
        return idrecipeItemHasSet;
    }

    public void setIdrecipeItemHasSet(Integer idrecipeItemHasSet) {
        this.idrecipeItemHasSet = idrecipeItemHasSet;
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

    public Integer getQuantityAlreadyObtained() {
        return quantityAlreadyObtained;
    }

    public void setQuantityAlreadyObtained(Integer quantityAlreadyObtained) {
        this.quantityAlreadyObtained = quantityAlreadyObtained;
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
