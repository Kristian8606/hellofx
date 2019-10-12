package hellofx;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToday {
    public static String returnDateToday(){
         try {
             Date date = new Date();
             DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
             return (df.format(date));
         }catch (Exception ex){
             System.out.println(ex);
             System.out.println("Not Date Today");
             return null;
         }




    }
}
