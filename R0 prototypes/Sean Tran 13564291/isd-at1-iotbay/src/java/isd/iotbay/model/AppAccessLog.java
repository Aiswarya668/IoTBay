/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isd.iotbay.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author seant
 */
public class AppAccessLog implements Serializable {
    private String accessLogId;
    private Staff staff;
    private Date date;
    private String description;

    public AppAccessLog() {
    }

    public AppAccessLog(String accessLogId, Staff staff, Date date, String description) {
        this.accessLogId = accessLogId;
        this.staff = staff;
        this.date = date;
        this.description = description;
    }

    public String getAccessLogId() {
        return accessLogId;
    }

    public void setAccessLogId(String accessLogId) {
        this.accessLogId = accessLogId;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
