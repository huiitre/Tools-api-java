package fr.huiitre.tools.tools_dofus_unity.item.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item", schema = "tools_dofus_unity")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iditem", nullable = false)
    private Integer iditem;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "code", nullable = false, length = 255)
    private String code;

    @Column(name = "img", nullable = false)
    private String img;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "iditem_type", nullable = false)
    private Integer iditemType;

    @Column(name = "average_price", nullable = false)
    private Double averagePrice;

    @Column(name = "x1_price", nullable = false)
    private Double x1Price;

    @Column(name = "x10_price", nullable = false)
    private Double x10Price;

    @Column(name = "x100_price", nullable = false)
    private Double x100Price;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "price_updated_at")
    private LocalDateTime priceUpdatedAt;

    @Column(name = "url_doflex", nullable = false)
    private String urlDoflex;

    @Column(name = "idpanoply", nullable = false)
    private Integer idpanoply;

    @Column(name = "hasrecipe", nullable = false)
    private Boolean hasrecipe;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    public Item() {
    }

    public Integer getIditem() {
        return iditem;
    }

    public void setIditem(Integer iditem) {
        this.iditem = iditem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getIditemType() {
        return iditemType;
    }

    public void setIditemType(Integer iditemType) {
        this.iditemType = iditemType;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public Double getX1Price() {
        return x1Price;
    }

    public void setX1Price(Double x1Price) {
        this.x1Price = x1Price;
    }

    public Double getX10Price() {
        return x10Price;
    }

    public void setX10Price(Double x10Price) {
        this.x10Price = x10Price;
    }

    public Double getX100Price() {
        return x100Price;
    }

    public void setX100Price(Double x100Price) {
        this.x100Price = x100Price;
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

    public LocalDateTime getPriceUpdatedAt() {
        return priceUpdatedAt;
    }

    public void setPriceUpdatedAt(LocalDateTime priceUpdatedAt) {
        this.priceUpdatedAt = priceUpdatedAt;
    }

    public String getUrlDoflex() {
        return urlDoflex;
    }

    public void setUrlDoflex(String urlDoflex) {
        this.urlDoflex = urlDoflex;
    }

    public Integer getIdpanoply() {
        return idpanoply;
    }

    public void setIdpanoply(Integer idpanoply) {
        this.idpanoply = idpanoply;
    }

    public Boolean getHasrecipe() {
        return hasrecipe;
    }

    public void setHasrecipe(Boolean hasrecipe) {
        this.hasrecipe = hasrecipe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
