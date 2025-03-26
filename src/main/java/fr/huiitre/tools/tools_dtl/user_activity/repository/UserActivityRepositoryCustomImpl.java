package fr.huiitre.tools.tools_dtl.user_activity.repository;

import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import fr.huiitre.tools.common.repository.BaseRepositoryCustomImpl;

@Repository
@Transactional
public class UserActivityRepositoryCustomImpl extends BaseRepositoryCustomImpl implements UserActivityRepositoryCustom {

    @Override
    public Integer insertUserActivityNative(
        Integer iduser,
        String token,
        String login,
        String ipAddress,
        Integer idhistoric,
        String details,
        String userAgent
    ) {
        String sql =
            """
                INSERT INTO tools_dtl.user_activity (
                    iduser, token, login, idhistoric, details, ip_address, user_agent, created_at
                ) VALUES (
                    :iduser, :token, :login, :idhistoric, :details, CAST(:ipAddress AS inet), :userAgent, CURRENT_TIMESTAMP
                )
                RETURNING iduser_activity
            """
        ;
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("iduser", iduser);
        query.setParameter("token", token);
        query.setParameter("login", login);
        query.setParameter("idhistoric", idhistoric);
        query.setParameter("details", details);
        query.setParameter("ipAddress", ipAddress);
        query.setParameter("userAgent", userAgent);
        return ((Number) query.getSingleResult()).intValue();
    }
}
