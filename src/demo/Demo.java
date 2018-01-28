/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Andrei
 */
public class Demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Driver d;
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/desters",
                "user1",
                "p1"
        );
        Statement s = c.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        );
        ResultSet r = s.executeQuery("SELECT * FROM planete");
        while (r.next()) {
            System.out.println(r.getString("nume"));
        }

//        int nr = s.executeUpdate("UPDATE planete SET diametru = diametru+1");
//        System.out.println("Ati stricat " + nr + " planete");

        r.absolute(2);
        r.updateInt("diametru", 2000);
        r.updateDouble("distantaSoare", 14326.38);
        
        r.updateRow();
        
        r.moveToInsertRow();
        r.updateString("nume","pamant");
        r.updateInt("diametru", 13000);
        r.updateDouble("distantaSoare", 149600000);
        r.updateInt("nrPlaneta",3);
        r.insertRow();
        
        
    }

}
