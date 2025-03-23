package fr.huiitre.tools.tools_core.user.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserHasConfigId implements Serializable {

    private Integer iduser;
    private Integer idconfig;

    public UserHasConfigId() {
    }

    public UserHasConfigId(Integer iduser, Integer idconfig) {
        this.iduser = iduser;
        this.idconfig = idconfig;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public Integer getIdconfig() {
        return idconfig;
    }

    public void setIdconfig(Integer idconfig) {
        this.idconfig = idconfig;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserHasConfigId that = (UserHasConfigId) o;
        return Objects.equals(iduser, that.iduser) &&
                Objects.equals(idconfig, that.idconfig);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iduser, idconfig);
    }
}
