package fr.huiitre.tools.tools_core.module.dto;

public class UserModuleRequest {
    private Integer idmodule;
    private String name;
    private String code;
    private Integer moduleRole;

    public UserModuleRequest(
        Integer idmodule,
        String name,
        String code,
        Integer moduleRole
    ) {
        this.idmodule = idmodule;
        this.name = name;
        this.code = code;
        this.moduleRole = moduleRole;
    }

    public Integer getIdmodule() {
        return idmodule;
    }

    public void setIdmodule(Integer idmodule) {
        this.idmodule = idmodule;
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

    public Integer getModuleRole() {
        return moduleRole;
    }

    public void setModuleRole(Integer moduleRole) {
        this.moduleRole = moduleRole;
    }
}
