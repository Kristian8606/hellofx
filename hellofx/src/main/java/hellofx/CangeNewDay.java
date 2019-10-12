package hellofx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static hellofx.DBConector.DBName;
import static hellofx.DBConector.getConections;

public class CangeNewDay {

    public static void changeDay() throws SQLException, ClassNotFoundException, ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
        Date dateToday = dateFormat.parse(DateToday.returnDateToday());
        Date dateOfDB = dateFormat.parse(getDatefromDB());

        if (dateToday.after(dateOfDB)){
            setNewDay();
        }
    }

    private static void setNewDay() throws SQLException, ClassNotFoundException, ParseException {
        Connection con = getConections();
      //  SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");

        String dateToday = DateToday.returnDateToday();
        try {
            PreparedStatement postedNewDateFromDateTable = con.prepareStatement("INSERT INTO "+DBName+".dateTable (dateToday) VALUES('"+dateToday+"');");
            postedNewDateFromDateTable.executeLargeUpdate();
            System.out.println("Set New Day");
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public static String getDatefromDB() throws SQLException, ClassNotFoundException {
        Connection con = getConections();
        ResultSet rslastDate = con.createStatement().executeQuery("SELECT dateToday FROM "+DBName+".dateTable WHERE ID = (SELECT MAX(ID) FROM "+DBName+".dateTable);");
        String lastDate = null;
        while (rslastDate.next()) {
            lastDate = rslastDate.getString(1);
            return lastDate;
        }
        return null;
    }
}
