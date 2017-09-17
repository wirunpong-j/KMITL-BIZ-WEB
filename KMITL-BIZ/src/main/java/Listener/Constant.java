/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listener;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author BellKunG
 */
public class Constant {
    public static Connection getConnection() throws URISyntaxException, SQLException {
        String url = "";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
//            url ="jdbc:mysql://kmtil-biz-way-db.mysql.database.azure.com:3306/KMITLBIZ?useUnicode=true&characterEncoding=UTF8";
            url ="jdbc:mysql://localhost:3306/kmitlbiz?useUnicode=true&characterEncoding=UTF8";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

//        return DriverManager.getConnection(url, "kmitlbizadmin@kmtil-biz-way-db", "3hX-Mxx-gzq-QTV");
        return DriverManager.getConnection(url, "root", "");
    }
}
