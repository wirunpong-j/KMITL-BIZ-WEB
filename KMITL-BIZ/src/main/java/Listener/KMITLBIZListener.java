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
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Constant.dataSource = (DataSource) getKMITLBIZ();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }

    private DataSource getKMITLBIZ() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/KMITLBIZ");
    }
    
    
}
