package fr.huiitre.tools.tools_dtl.user_activity.service;

import fr.huiitre.tools.tools_dtl.user_activity.model.UserActivity;
import fr.huiitre.tools.tools_dtl.user_activity.repository.UserActivityRepository;
import fr.huiitre.tools.tools_dtl.historic.model.Historic;
import fr.huiitre.tools.tools_dtl.historic.repository.HistoricRepository;
import fr.huiitre.tools.tools_dtl.user_activity.dto.AddUserActivityRequest;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserActivityService {

    @Autowired
    private UserActivityRepository userActivityRepository;

    @Autowired
    private HistoricRepository historicRepository;

    public UserActivityService() {
    }
    
    public UserActivity addUserActivity(
        Integer iduser, 
        String token, 
        String login, 
        String clientIp, 
        AddUserActivityRequest activityRequest, 
        String userAgent
    ) throws Exception {
        // Vérification du codeHistoric
        if (activityRequest.getCodeHistoric() == null) {
            throw new Exception("codeHistoric manquant");
        }

        Historic historic = historicRepository.findByCode(activityRequest.getCodeHistoric());
        if (historic == null)
            throw new Exception("Aucun historique trouvé pour le code " + activityRequest.getCodeHistoric());
                
        Integer insertedId = userActivityRepository.insertUserActivityNative(
            iduser,
            token,
            login,
            clientIp,
            historic.getIdhistoric(),
            activityRequest.getDetails(),
            userAgent
        );

        UserActivity userActivity = new UserActivity();
        userActivity.setIduserActivity(insertedId);
        userActivity.setIduser(iduser);
        userActivity.setToken(token);
        userActivity.setLogin(login);
        userActivity.setIpAddress(clientIp);
        userActivity.setUserAgent(userAgent != null ? userAgent : "Unknown");
        userActivity.setDetails(activityRequest.getDetails());
        userActivity.setIdhistoric(historic.getIdhistoric());
        userActivity.setCreatedAt(LocalDateTime.now());

        return userActivity;
    }
}
