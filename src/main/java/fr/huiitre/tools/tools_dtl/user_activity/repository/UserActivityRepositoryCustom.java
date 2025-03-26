package fr.huiitre.tools.tools_dtl.user_activity.repository;

public interface UserActivityRepositoryCustom {
    Integer insertUserActivityNative(
        Integer iduser,
        String token,
        String login,
        String ipAddress,
        Integer idhistoric,
        String details,
        String userAgent
    );
}
