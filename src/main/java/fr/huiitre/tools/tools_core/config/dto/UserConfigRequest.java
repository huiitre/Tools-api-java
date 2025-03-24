package fr.huiitre.tools.tools_core.config.dto;

public class UserConfigRequest {
    
    private Integer idconfigCategory;
    private String codeConfigCategory;
    private String nameConfigCategory;
    private Integer idconfigSubcategory;
    private String codeConfigSubcategory;
    private String nameConfigSubcategory;
    private Integer idconfig;
    private String configName;
    private String configCode;
    private String configValue;
    private String configComments;
    private String configType;

    public UserConfigRequest(
        Integer idconfigCategory,
        String codeConfigCategory,
        String nameConfigCategory,
        Integer idconfigSubcategory,
        String codeConfigSubcategory,
        String nameConfigSubcategory,
        Integer idconfig,
        String configName,
        String configCode,
        String configValue,
        String configComments,
        String configType
    ) {
        this.idconfigCategory = idconfigCategory;
        this.codeConfigCategory = codeConfigCategory;
        this.nameConfigCategory = nameConfigCategory;
        this.idconfigSubcategory = idconfigSubcategory;
        this.codeConfigSubcategory = codeConfigSubcategory;
        this.nameConfigSubcategory = nameConfigSubcategory;
        this.idconfig = idconfig;
        this.configName = configName;
        this.configCode = configCode;
        this.configValue = configValue;
        this.configComments = configComments;
        this.configType = configType;
    }

    // Getters et Setters
    public Integer getIdconfigCategory() {
        return idconfigCategory;
    }

    public void setIdconfigCategory(Integer idconfigCategory) {
        this.idconfigCategory = idconfigCategory;
    }

    public String getCodeConfigCategory() {
        return codeConfigCategory;
    }

    public void setCodeConfigCategory(String codeConfigCategory) {
        this.codeConfigCategory = codeConfigCategory;
    }

    public String getNameConfigCategory() {
        return nameConfigCategory;
    }

    public void setNameConfigCategory(String nameConfigCategory) {
        this.nameConfigCategory = nameConfigCategory;
    }

    public Integer getIdconfigSubcategory() {
        return idconfigSubcategory;
    }

    public void setIdconfigSubcategory(Integer idconfigSubcategory) {
        this.idconfigSubcategory = idconfigSubcategory;
    }

    public String getCodeConfigSubcategory() {
        return codeConfigSubcategory;
    }

    public void setCodeConfigSubcategory(String codeConfigSubcategory) {
        this.codeConfigSubcategory = codeConfigSubcategory;
    }

    public String getNameConfigSubcategory() {
        return nameConfigSubcategory;
    }

    public void setNameConfigSubcategory(String nameConfigSubcategory) {
        this.nameConfigSubcategory = nameConfigSubcategory;
    }

    public Integer getIdconfig() {
        return idconfig;
    }

    public void setIdconfig(Integer idconfig) {
        this.idconfig = idconfig;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getConfigComments() {
        return configComments;
    }

    public void setConfigComments(String configComments) {
        this.configComments = configComments;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }
}
