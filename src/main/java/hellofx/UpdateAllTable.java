package hellofx;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static hellofx.DBConector.DBName;
import static hellofx.DBConector.getConections;

public class UpdateAllTable {


    public static ResultSet getLoginUser(String dateToday) {
       // System.out.println(dateToday + "date test");
        try {
            Connection con = DBConector.getConections();

            return con.createStatement().executeQuery("select user from " + DBName + ".loginUser where date = '" + dateToday + "';");
            // return con.createStatement().executeQuery("SELECT user FROM "+DBName+".loginUser WHERE date = '"+dateToday+"' AND id=(SELECT MAX(id) FROM loginUser);");

        } catch (Exception ex) {
            System.out.println(ex);
            return (ResultSet) ex;
        }
    }


    static void updateCashbox() throws SQLException, ClassNotFoundException {
        try {
            Connection con = DBConector.getConections();

            String date = DateToday.returnDateToday();


            PreparedStatement updateCashBox = con.prepareStatement("UPDATE " + DBName + ".updateAllTable SET date = '" + date + "',period = '1'" +
                    ",interest = (SELECT coalesce(sum(interest),0) FROM " + DBName + ".table1)" +
                    ",goldGr = (SELECT coalesce(sum(goldGr),0) FROM " + DBName + ".table1)" +
                    ",silverGr = (SELECT coalesce(sum(silverGr),0) FROM " + DBName + ".table1)" +
                    ",newSilverGr = (SELECT coalesce(sum(newSilverGr),0) FROM " + DBName + ".table1)" +
                    ",income = (SELECT coalesce(sum(income),0) FROM " + DBName + ".table2)" +
                    ",costs = (SELECT coalesce(sum(cost),0) FROM " + DBName + ".table2)" +
                    ",purchasedGold = (SELECT coalesce(sum(goldPrice),0) FROM " + DBName + ".table4)," +
                    "purchasedSilver = (SELECT coalesce(sum(silverPrice),0) FROM " + DBName + ".table4) WHERE ID =(SELECT MAX(ID) FROM " + DBName + ".updateAllTable);");
            updateCashBox.executeLargeUpdate();


            PreparedStatement updateCash = con.prepareStatement("UPDATE " + DBName + ".updateAllTable SET cashierOfPrevioursRevision = (SELECT ((SELECT coalesce(sum(interest),0) + coalesce(sum(principal),0) FROM table1 ) " +
                    "+ (SELECT coalesce(sum(income),0)  FROM table2 )) " +
                    "- (SELECT((SELECT coalesce(sum(cost),0) FROM table2 ) + (SELECT coalesce(sum(sumOfBet),0) FROM table3 ) " +
                    "+ (SELECT coalesce(sum(goldPrice),0) FROM table4 ) + (SELECT coalesce(sum(silverPrice),0) FROM table4 )))) WHERE ID = (SELECT MAX(ID) FROM " + DBName + ".updateAllTable);");
            updateCash.executeLargeUpdate();
            updateCash.close();
            updateCashBox.close();

            System.out.println("Update  CashBox");
        } catch (Exception e) {
            System.out.println(e);
            ExeptionDialog.exeptionDialog((SQLException) e);
        }
    }

}
