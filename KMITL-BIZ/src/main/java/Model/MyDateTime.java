/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author BellKunG
 */
public class MyDateTime {
    
    private static MyDateTime instance = null;
    
    public static MyDateTime getInstance() {
        instance = new MyDateTime();
        return instance;
    }
    
    private HashMap<String, String> allRentType;
    private HashMap<String, Object> allRentDate;
    private ZonedDateTime dateIncrementor;
    private Month thisMonth;
    private Month nextMonth;
    private ZonedDateTime zonedTime;
    
    protected MyDateTime() {
        LocalDateTime currentTime = LocalDateTime.now();
   
        ZoneId id = ZoneId.of("Asia/Bangkok");
        this.zonedTime = currentTime.atZone(id);
        this.thisMonth = zonedTime.getMonth();
        this.nextMonth = this.thisMonth.plus(1);

        this.dateIncrementor = this.zonedTime;
    }

    public HashMap<String, String> getAllRentType() {
        this.allRentType = new HashMap<>();
        this.allRentType.put("R1", "จองวันพฤหัสบดีที่ " + getThisThursday().getDayOfMonth() + " " + Formating.monthFormat(getThisThursday().getMonth().getValue()) + " " + getThisThursday().getYear());
        this.allRentType.put("R2", "จองล่วงหน้าวันพฤหัสบดีที่ " + getNextThursday().getDayOfMonth() + " " + Formating.monthFormat(getNextThursday().getMonth().getValue()) + " " + getNextThursday().getYear());
        this.allRentType.put("R3", "จองทั้งเดือน " + Formating.monthFormat(this.thisMonth.getValue()));
        this.allRentType.put("R4", "จองล่วงหน้าทั้งเดือน " + Formating.monthFormat(this.nextMonth.getValue()));
        
        return this.allRentType;
    }

    public HashMap<String, Object> getAllRentDate() {
        this.allRentDate = new HashMap<>();
        this.allRentDate.put("R1", getThisThursday());
        this.allRentDate.put("R2", getNextThursday());
        this.allRentDate.put("R3", getThisMonthDays());
        this.allRentDate.put("R4", getNextMonthDays());
        
        System.out.print("R1 : " + this.allRentDate.get("R1"));
        System.out.print("R2 : " + this.allRentDate.get("R2"));
        System.out.print("R3 : " + this.allRentDate.get("R3"));
        System.out.print("R4 : " + this.allRentDate.get("R4"));
        
        return this.allRentDate;
    }
    
    
    private ArrayList<ZonedDateTime> getThisMonthDays() {
        ArrayList<ZonedDateTime> thisMonthDays = new ArrayList<>();
        while (this.dateIncrementor.getMonth() == this.thisMonth) {
            if(this.dateIncrementor.getDayOfWeek() == DayOfWeek.THURSDAY) {
                thisMonthDays.add(this.dateIncrementor);
            }
            this.dateIncrementor = this.dateIncrementor.plusDays(1);
        }
        
        return thisMonthDays;
    }
    
    private ArrayList<ZonedDateTime> getNextMonthDays() {
        ArrayList<ZonedDateTime> nextMonthDays = new ArrayList<>();
        while(this.dateIncrementor.getMonth() == this.nextMonth) {
            if(this.dateIncrementor.getDayOfWeek() == DayOfWeek.THURSDAY) {
                nextMonthDays.add(this.dateIncrementor);
            }
            this.dateIncrementor = this.dateIncrementor.plusDays(1);
        }
        
        return nextMonthDays;
    }
    
    private ZonedDateTime getThisThursday() {
        ZonedDateTime thisThursday = this.zonedTime;
        while (thisThursday.getDayOfWeek() != DayOfWeek.THURSDAY) {
            thisThursday = thisThursday.plusDays(1);
        }
        
        return thisThursday;
    }
    
    private ZonedDateTime getNextThursday() {
        ZonedDateTime nextThursday = getThisThursday().plusDays(1);
        while (nextThursday.getDayOfWeek() != DayOfWeek.THURSDAY) {
            nextThursday = nextThursday.plusDays(1);
        }
        
        return nextThursday;
    }
        
}
