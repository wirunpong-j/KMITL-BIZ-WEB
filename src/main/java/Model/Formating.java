/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author BellKunG
 */
public class Formating {
    
    public static String monthFormat(int month) {
        String newMonth = "";
        switch (month) {
            case 1: newMonth = "มกราคม"; break;
            case 2: newMonth = "กุมภาพันธ์"; break;
            case 3: newMonth = "มีนาคม"; break;
            case 4: newMonth = "เมษายน"; break;
            case 5: newMonth = "พฤษภาคม"; break;
            case 6: newMonth = "มิถุนายน"; break;
            case 7: newMonth = "กรกฏาคม"; break;
            case 8: newMonth = "สิงหาคม"; break;
            case 9: newMonth = "กันยายน"; break;
            case 10: newMonth = "ตุลาคม"; break;
            case 11: newMonth = "พฤศจิกายน"; break;
            case 12: newMonth = "ธันวาคม"; break;
        }
        return newMonth;
    }
}
