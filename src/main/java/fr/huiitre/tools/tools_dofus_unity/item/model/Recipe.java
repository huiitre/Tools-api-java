package fr.huiitre.tools.tools_dofus_unity.item.model;

import jakarta.persistence.*;

@Entity
@Table(name = "recipe", schema = "tools_dofus_unity")
public class Recipe {

    @Id
    @Column(name = "idrecipe", nullable = false)
    private Integer idrecipe;

    @Column(name = "idparent", nullable = false)
    private Integer idparent;

    @Column(name = "idenfant", nullable = false)
    private Integer idenfant;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public Recipe() {
    }

    public Integer getIdrecipe() {
        return idrecipe;
    }

    public void setIdrecipe(Integer idrecipe) {
        this.idrecipe = idrecipe;
    }

    public Integer getIdparent() {
        return idparent;
    }

    public void setIdparent(Integer idparent) {
        this.idparent = idparent;
    }

    public Integer getIdenfant() {
        return idenfant;
    }

    public void setIdenfant(Integer idenfant) {
        this.idenfant = idenfant;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
