package fr.huiitre.tools.tools_core.module.repository;

import fr.huiitre.tools.tools_core.module.model.Module;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ModuleRepository extends JpaRepository<Module, Integer> {
    
    @Query(value = 
        """
            select
                m.idmodule,
                m.name,
                m.code,
                m.picture,
                uhm.role as module_role
            from tools_core.module m 
            inner join tools_core.user_has_module uhm on uhm.idmodule = m.idmodule 
            inner join tools_core.user u on u.iduser = uhm.iduser
            where u.iduser = :iduser
            and m.is_active  = 1
        """,
        nativeQuery = true
    )
    List<Map<String, Object>> findUserModule(@Param("iduser") Integer iduser);
}