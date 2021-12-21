package hellofx;

import java.util.Calendar;
import java.util.Date;

public class Time {
   public static Date getTime(){
       Calendar now = Calendar.getInstance();
       return now.getTime();
   }
}
