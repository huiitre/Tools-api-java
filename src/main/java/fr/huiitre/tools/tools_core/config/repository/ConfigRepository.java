package fr.huiitre.tools.tools_core.config.repository;

import fr.huiitre.tools.tools_core.config.model.Config;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConfigRepository extends JpaRepository<Config, Integer> {

    @Query(value =
        """
            SELECT COALESCE(uhc.value, uhc_default.value) as value
            FROM tools_core.config c
            LEFT JOIN tools_core.user_has_config uhc ON uhc.idconfig = c.idconfig AND uhc.iduser = :iduser
            LEFT JOIN tools_core.user_has_config uhc_default ON uhc_default.idconfig = c.idconfig AND uhc_default.iduser = 0
            WHERE c.is_active = 1 AND c.code = :key
        """,
        nativeQuery = true
    )
    String findConfigValue(@Param("key") String key, @Param("iduser") Integer iduser);
}
