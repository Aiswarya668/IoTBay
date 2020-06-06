package unit.test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import uts.isd.controller.ConnServlet;
import uts.isd.model.iotbay.dao.DBConnector;
import uts.isd.model.iotbay.dao.DBOrderManager;

/**
 *
 * @author sanya dua
 */
public class DBOrderTest {

    // create DB Connection Here
    private DBOrderManager orderManager;

    public DBOrderTest() {
        try {
            DBConnector db = new DBConnector();
            orderManager = new DBOrderManager(db.openConnection());

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void createOrder() throws SQLException {

        orderManager.addOrder("email@email.com", new Timestamp(new Date().getTime()), 0, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), "sup@email.com", 0, "Air", "SUBMITED", "Street Address", "1", "Rockdale", "NSW", "2144", "1111");
        
    }

    @Test
    public void readOrder() throws SQLException{
        orderManager.getOrdersById("1");
    }

    @Test
    public void updateOrder() throws SQLException {
        orderManager.updateCustomerOrder("", "", 0, 0, "");
    }

    @Test
    public void deleteOrder() throws SQLException {
        orderManager.deleteOrder(1);
       
    }

}
