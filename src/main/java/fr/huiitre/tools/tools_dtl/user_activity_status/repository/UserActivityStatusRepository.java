package fr.huiitre.tools.tools_dtl.user_activity_status.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.huiitre.tools.tools_dtl.user_activity_status.dto.UserActivitiesStatusByUserResponse;
import fr.huiitre.tools.tools_dtl.user_activity_status.model.UserActivityStatus;
import jakarta.transaction.Transactional;

@Repository
public interface UserActivityStatusRepository extends JpaRepository<UserActivityStatus, Long> {
    @Query(value = 
        """
            -- la liste des notifications pour un utilisateur XXX
            select
                uau.iduser_activity_status,
                ua.iduser_activity,
                h.description as title,
                ua.details as details,
                uau.is_read,
                ua.login
                -- uau.is_active,
                -- uau.created_at
            from tools_dtl.user_activity_status uau
            inner join tools_dtl.user_activity ua on uau.iduser_activity = ua.iduser_activity
            inner join tools_dtl.historic h on ua.idhistoric = h.idhistoric
            where uau.iduser = :iduser
            and h.is_visible
            order by uau.created_at desc
        """,
        nativeQuery = true
    )
    List<Map<String, Object>> findUserActivitiesStatusByUser(@Param("iduser") Integer iduser);

    @Modifying
    @Transactional
    @Query(value = "delete from tools_dtl.user_activity_status where iduser = :iduser", nativeQuery = true)
    void deleteAllNotificationsFromUser(@Param("iduser") Integer iduser);

    @Modifying
    @Transactional
    @Query(value = "delete from tools_dtl.user_activity_status where iduser = :iduser and iduser_activity_status = :iduser_activity_status", nativeQuery = true)
    void deleteNotificationFromUser(@Param("iduser") Integer iduser, @Param("iduser_activity_status") Integer iduserActivityStatus);

    @Modifying
    @Transactional
    @Query(value =
        """
            update tools_dtl.user_activity_status set is_read = true where iduser = :iduser and iduser_activity_status = :iduser_activity_status
        """, nativeQuery = true)
    void readNotificationFromUser(
        @Param("iduser") Integer iduser,
        @Param("iduser_activity_status") Integer iduserActivityStatus
    );
}