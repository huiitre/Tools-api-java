package fr.huiitre.tools.tools_dtl.user_activity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fr.huiitre.tools.tools_dtl.user_activity.service.UserActivityService;
import fr.huiitre.tools.tools_dtl.user_activity_status.dto.UserActivitiesStatusByUserResponse;
import fr.huiitre.tools.tools_dtl.user_activity_status.service.UserActivityStatusService;
import jakarta.servlet.http.HttpServletRequest;
import fr.huiitre.tools.common.controller.BaseController;
import fr.huiitre.tools.tools_dtl.user_activity.dto.AddUserActivityRequest;
import fr.huiitre.tools.tools_dtl.user_activity.model.UserActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dtl/user-activity")
public class UserActivityController extends BaseController {

    @Autowired
    private UserActivityService userActivityService;

    @Autowired
    private UserActivityStatusService userActivityStatusService;

    @GetMapping("/{iduser}")
    public ResponseEntity<?> getUserActivities(
        @PathVariable("iduser") Integer iduser
    ) {
        try {
            List<UserActivitiesStatusByUserResponse> userActivitiesStatus = userActivityStatusService.getUserActivitiesStatusByUser(iduser);

            return ResponseEntity.ok().body(Map.of("data", userActivitiesStatus));
        } catch(Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of( "msg", e.getMessage()));
        }
    }

    @PostMapping("add")
    public ResponseEntity<?> addUserActivity(
        @RequestParam("iduser") Integer iduser,
        @RequestParam("token") String token,
        @RequestParam("login") String login,
        @RequestParam(value = "clientIp", required = false) String clientIp,
        @RequestBody AddUserActivityRequest activityDTO,
        HttpServletRequest request
    ) {
        try {
            String userAgent = request.getHeader("User-Agent");
            if (userAgent == null)
                userAgent = "Unknown";

            if (clientIp == null || clientIp.isBlank())
                clientIp = request.getRemoteAddr();

            List<Integer> iduserList = activityDTO.getIduserList();
            if (iduserList == null)
                iduserList = new ArrayList<>();

            UserActivity userActivity = userActivityService.addUserActivity(iduser, token, login, clientIp, activityDTO, userAgent);

            for (Integer id : iduserList) {
                userActivityStatusService.addUserActivityStatus(userActivity.getIduserActivity(), id);
            }

            return ResponseEntity.ok().body(Map.of( "msg", "User activity added successfully", "iduserActivity", userActivity.getIduserActivity()));
        } catch(Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of( "msg", e.getMessage()));
        }
    }

    @DeleteMapping("/{iduser}/delete/all")
    public ResponseEntity<?> deleteAllNotifications(@PathVariable("iduser") Integer iduser) {
        try {
            userActivityStatusService.deleteAllNotificationsFromUser(iduser);
            return ResponseEntity.ok(Map.of("msg", "All notifications for user " + iduser + " have been deleted."));
        } catch(Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("msg", e.getMessage()));
        }
    }

    @DeleteMapping("/{iduser}/delete/{iduserActivityStatus}")
    public ResponseEntity<?> deleteNotification(
            @PathVariable("iduser") Integer iduser,
            @PathVariable("iduserActivityStatus") Integer iduserActivityStatus) {
        try {
            userActivityStatusService.deleteNotificationFromUser(iduser, iduserActivityStatus);
            return ResponseEntity.ok(Map.of("msg", "Notification " + iduserActivityStatus + " for user " + iduser + " has been deleted."));
        } catch(Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("msg", e.getMessage()));
        }
    }

    @PutMapping("/{iduser}/read/{iduserActivityStatus}")
    public ResponseEntity<?> readNotification(
        @PathVariable("iduser") Integer iduser,
        @PathVariable("iduserActivityStatus") Integer iduserActivityStatus
    ) {
        try {
            userActivityStatusService.readNotificationFromUser(iduser, iduserActivityStatus);
            return ResponseEntity.ok(Map.of("msg", "Notification " + iduserActivityStatus + " for user " + iduser + " has been read."));
        } catch(Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("msg", e.getMessage()));
        }
    }
}