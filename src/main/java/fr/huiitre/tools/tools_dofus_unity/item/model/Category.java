package fr.huiitre.tools.tools_dofus_unity.item.model;

import jakarta.persistence.*;

@Entity
@Table(name = "category", schema = "tools_dofus_unity")
public class Category {

    @Id
    @Column(name = "idcategory", nullable = false)
    private Integer idcategory;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "code", nullable = false, length = 255)
    private String code;

    public Category() {
    }

    public Integer getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(Integer idcategory) {
        this.idcategory = idcategory;
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
}
