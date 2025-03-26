package fr.huiitre.tools.tools_dtl.user_activity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.huiitre.tools.tools_dtl.user_activity.model.UserActivity;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Long>, UserActivityRepositoryCustom {
    
}