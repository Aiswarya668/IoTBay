/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.iotbay.dao;
import java.sql.Connection;

/**
 *
 * @author Anastasia
 */
public abstract class DB {
    // setup connection variables
    protected String URL = "jdbc:derby://localhost:1527/";
    protected String db = "iotdb";
    protected String dbuser = "iotbayUser";
    protected String dbpass = "Group27";
    protected String driver = "org.apache.derby.jdbc.ClientDriver";
    protected Connection conn;
}
