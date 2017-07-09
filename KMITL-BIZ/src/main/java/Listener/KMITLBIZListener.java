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
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

/**
 * Web application lifecycle listener.
 *
 * @author BellKunG
 */
public class KMITLBIZListener implements ServletContextListener {

    private Connection conn;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            conn = getKMITLBIZ().getConnection();
            sce.getServletContext().setAttribute("connection", conn);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private DataSource getKMITLBIZ() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/KMITLBIZ");
    }
    
    
}
