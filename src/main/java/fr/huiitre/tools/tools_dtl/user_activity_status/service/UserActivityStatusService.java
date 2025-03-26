package fr.huiitre.tools.tools_dtl.user_activity_status.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.huiitre.tools.tools_dtl.user_activity_status.dto.UserActivitiesStatusByUserResponse;
import fr.huiitre.tools.tools_dtl.user_activity_status.model.UserActivityStatus;
import fr.huiitre.tools.tools_dtl.user_activity_status.repository.UserActivityStatusRepository;
import jakarta.transaction.Transactional;

@Service
public class UserActivityStatusService {

    @Autowired
    private UserActivityStatusRepository userActivityStatusRepository;

    public UserActivityStatusService() {
    }

    public void addUserActivityStatus(
        Integer iduserActivity,
        Integer iduser
    ) throws Exception {
        UserActivityStatus newUserActivityStatus = new UserActivityStatus();
        newUserActivityStatus.setIduserActivity(iduserActivity);
        newUserActivityStatus.setIduser(iduser);
        newUserActivityStatus.setIsRead(false);
        newUserActivityStatus.setIsActive(true);
        newUserActivityStatus.setCreatedAt(LocalDateTime.now());
        userActivityStatusRepository.save(newUserActivityStatus);
    }

    public List<UserActivitiesStatusByUserResponse> getUserActivitiesStatusByUser(Integer iduser) throws Exception {
        List<Map<String, Object>> results = userActivityStatusRepository.findUserActivitiesStatusByUser(iduser);

        List<UserActivitiesStatusByUserResponse> dtoList = new ArrayList<>();

        for (Map<String, Object> row : results) {
            UserActivitiesStatusByUserResponse dto = new UserActivitiesStatusByUserResponse(
                (Integer) row.get("iduser_activity_status"),
                (Integer) row.get("iduser_activity"),
                (String) row.get("title"),
                (String) row.get("details"),
                (Boolean) row.get("is_read"),
                (String) row.get("login")
            );
            dtoList.add(dto);
        }

        return dtoList;
    }

    @Transactional
    public void deleteAllNotificationsFromUser(Integer iduser) {
        userActivityStatusRepository.deleteAllNotificationsFromUser(iduser);
    }

    @Transactional
    public void deleteNotificationFromUser(Integer iduser, Integer iduserActivityStatus) {
        userActivityStatusRepository.deleteNotificationFromUser(iduser, iduserActivityStatus);
    }

    @Transactional
    public void readNotificationFromUser(Integer iduser, Integer iduserActivityStatus) {
        userActivityStatusRepository.readNotificationFromUser(iduser, iduserActivityStatus);
    }
}