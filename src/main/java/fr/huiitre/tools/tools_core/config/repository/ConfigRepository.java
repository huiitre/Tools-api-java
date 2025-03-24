package fr.huiitre.tools.tools_core.config.repository;

import fr.huiitre.tools.tools_core.config.model.Config;

import java.util.List;
import java.util.Map;

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

    @Query(value = """
        SELECT 
            cc.idconfig_category as idconfig_category,
            cc.code as code_config_category,
            cc.name as name_config_category,
            cs.idconfig_subcategory as idconfig_subcategory,
            cs.code as code_config_subcategory,
            cs.name as name_config_subcategory,
            c.idconfig as idconfig,
            c.name as config_name,
            c.code as config_code,
            COALESCE(uhc.value, uhc_default.value) as config_value,
            c.comments as config_comments,
            c.type as config_type
        FROM tools_core.config c
        INNER JOIN tools_core.config_subcategory cs ON cs.idconfig_subcategory = c.idconfig_subcategory 
        INNER JOIN tools_core.config_category cc ON cc.idconfig_category = cs.idconfig_category
        LEFT JOIN tools_core.user_has_config uhc ON uhc.iduser = :iduser AND uhc.idconfig = c.idconfig 
        LEFT JOIN tools_core.user_has_config uhc_default ON uhc_default.iduser = 0 AND uhc_default.idconfig = c.idconfig
        WHERE c.is_active = 1
        """, nativeQuery = true)
    List<Map<String, Object>> findUserConfig(@Param("iduser") Integer iduser);
}
