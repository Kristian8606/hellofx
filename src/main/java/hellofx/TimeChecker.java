package hellofx;

import com.sun.media.jfxmediaimpl.platform.Platform;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeChecker {
    public static boolean checkTime(){
        try{
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            System.out.println( sdf.format(cal.getTime()) );

            if(sdf.format(cal.getTime()).compareTo(SetTimerController.TimeToComp)>=0){
               // System.exit(0);
                return true;
            }
            else  return false;
        }catch (Exception e){
            System.out.println(e.toString());
            ExeptionDialog.alertDialog(e.toString());
        }
        return false;
    }
}
