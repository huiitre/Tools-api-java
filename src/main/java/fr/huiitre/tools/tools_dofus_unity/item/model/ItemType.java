package fr.huiitre.tools.tools_dofus_unity.item.model;

import jakarta.persistence.*;

@Entity
@Table(name = "item_type", schema = "tools_dofus_unity")
public class ItemType {

    @Id
    @Column(name = "iditem_type", nullable = false)
    private Integer iditemType;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "code", nullable = false, length = 255)
    private String code;

    @Column(name = "idcategory", nullable = false)
    private Integer idcategory;

    public ItemType() {
    }

    public Integer getIditemType() {
        return iditemType;
    }

    public void setIditemType(Integer iditemType) {
        this.iditemType = iditemType;
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

    public Integer getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(Integer idcategory) {
        this.idcategory = idcategory;
    }
}
