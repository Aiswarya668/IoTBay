/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author aiswaryarajeev
 */
public class ApplicationAccessLogs implements Serializable {
    private String accessLogID;
    private String customerEmail;
    private String staffEmail;
    private Timestamp timestamp;
    private String logDescription;

    public ApplicationAccessLogs(String accessLogID, String customerEmail, String staffEmail, 
    Timestamp timestamp, String logDescription) {
        this.accessLogID = accessLogID;
        this.customerEmail = customerEmail;
        this.staffEmail = staffEmail;
        this.timestamp = timestamp;
        this.logDescription = logDescription;
    }

    public String getAccessLogID() {
        return accessLogID;
    }

    public void setAccessLogID(String accessLogID) {
        this.accessLogID = accessLogID;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setUser(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timeStamp) {
        this.timestamp = timeStamp;
    }

    public String getLogDescription() {
        return logDescription;
    }

    public void setLogDescription(String logDescription) {
        this.logDescription = logDescription;
    }
}
