package fr.huiitre.tools.tools_dtl.user_activity_status.dto;

public class UserActivitiesStatusByUserResponse {

    private Integer iduserActivityStatus;
    private Integer iduserActivity;
    private String title;
    private String details;
    private Boolean isRead;
    private String login;

    public UserActivitiesStatusByUserResponse() {
    }

    public UserActivitiesStatusByUserResponse(Integer iduserActivityStatus, Integer iduserActivity, String title, String details, Boolean isRead, String login) {
        this.iduserActivityStatus = iduserActivityStatus;
        this.iduserActivity = iduserActivity;
        this.title = title;
        this.details = details;
        this.isRead = isRead;
        this.login = login;
    }

    public Integer getIduserActivityStatus() {
        return iduserActivityStatus;
    }

    public void setIduserActivityStatus(Integer iduserActivityStatus) {
        this.iduserActivityStatus = iduserActivityStatus;
    }

    public Integer getIduserActivity() {
        return iduserActivity;
    }

    public void setIduserActivity(Integer iduserActivity) {
        this.iduserActivity = iduserActivity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
