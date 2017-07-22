/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listener;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.sql.DataSource;

/**
 *
 * @author BellKunG
 */
public class GetDBConnection {
    public Connection getConnection(ServletContextEvent sce) {
        Connection conn = (Connection) sce.getServletContext().getAttribute("connection");
        try {
            if (conn.isClosed()) {
                conn = getKMITLBIZ().getConnection();
                sce.getServletContext().setAttribute("connection", conn);
                return conn;
            }     
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return conn;
    }

    public DataSource getKMITLBIZ() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/KMITLBIZ");
    }
    
    
}
