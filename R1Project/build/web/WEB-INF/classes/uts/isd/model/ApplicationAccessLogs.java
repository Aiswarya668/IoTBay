/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author aiswaryarajeev
 */
public class ApplicationAccessLogs implements Serializable {
    private String accessLogID;
    private User user;
    private String userID;
    private Date timeStamp;
    private String logDescription;

    public ApplicationAccessLogs(String accessLogID, User user, String userID, Date timeStamp, String logDescription) {
        this.accessLogID = accessLogID;
        this.user = user;
        this.userID = userID;
        this.timeStamp = timeStamp;
        this.logDescription = logDescription;
    }

    public String getAccessLogID() {
        return accessLogID;
    }

    public void setAccessLogID(String accessLogID) {
        this.accessLogID = accessLogID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getLogDescription() {
        return logDescription;
    }

    public void setLogDescription(String logDescription) {
        this.logDescription = logDescription;
    }

    
    
}
