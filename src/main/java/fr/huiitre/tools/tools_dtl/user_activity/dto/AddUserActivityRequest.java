package fr.huiitre.tools.tools_dtl.user_activity.dto;

import java.util.List;

public class AddUserActivityRequest {
    private String codeHistoric;
    private String details;
    private List<Integer> iduserList;

    public AddUserActivityRequest() {
    }

    public AddUserActivityRequest(String codeHistoric, String details, List<Integer> iduserList) {
        this.codeHistoric = codeHistoric;
        this.details = details;
        this.iduserList = iduserList;
    }

    public String getCodeHistoric() {
        return codeHistoric;
    }

    public void setCodeHistoric(String codeHistoric) {
        this.codeHistoric = codeHistoric;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<Integer> getIduserList() {
        return iduserList;
    }

    public void setIduserList(List<Integer> iduserList) {
        this.iduserList = iduserList;
    }
}
