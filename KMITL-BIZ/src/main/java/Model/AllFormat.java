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
public class AllFormat {
    public static String toPadZero(int num) {
        String numStr = String.format("%06d", num);
        return numStr;
    }
}
