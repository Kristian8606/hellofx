package hellofx;

import java.sql.Connection;
import java.sql.ResultSet;

import static hellofx.DBConector.DBName;

public class GetSum {
    public static String getInterest(String dateToday) {
        try {
            Connection con = DBConector.getConections();
            ResultSet returnInterest = con.createStatement().executeQuery("SELECT coalesce(sum(interest),0) FROM "+DBName+".table1 WHERE date = '"+dateToday+"' AND mark = '';");
            if (returnInterest.next()) {
                return returnInterest.getString(1);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
        return null;
    }
    public static String getBet(String data) {
        try {
            Connection con = DBConector.getConections();
            ResultSet getCashboxTotal = con.createStatement().executeQuery("SELECT (coalesce(sum(sumOfBet),0)) FROM "+DBName+".table3 WHERE date = '"+data+"';");
            if (getCashboxTotal.next()) {
                return getCashboxTotal.getString(1);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
        return null;
    }
    public static String getOverload(String data) {
        try {
            Connection con = DBConector.getConections();
            ResultSet getCashboxTotal = con.createStatement().executeQuery("SELECT (coalesce(sum(principal),0)) FROM "+DBName+".table1 WHERE date = '"+data+"' AND mark = '';");
            if (getCashboxTotal.next()) {
                return getCashboxTotal.getString(1);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
        return null;
    }
    public static String getWin(String data) {
        try {
            Connection con = DBConector.getConections();
            ResultSet getCashboxTotal = con.createStatement().executeQuery("SELECT (coalesce(sum(interest),0)) FROM "+DBName+".table1 WHERE date = '"+data+"';");
            if (getCashboxTotal.next()) {
                return getCashboxTotal.getString(1);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
        return null;
    }

    public static String getCashBoxOnDate(String date) {
        try {
            Connection con = DBConector.getConections();
            ResultSet getCashbox = con.createStatement().executeQuery("SELECT(SELECT (SELECT ((SELECT coalesce(sum(interest),0) + coalesce(sum(principal),0) FROM "+DBName+".table1 WHERE date <= '"+date+"')" +
                    " + (SELECT coalesce(sum(income),0)  FROM "+DBName+".table2 WHERE date <= '"+date+"'))" +
                    " - (SELECT((SELECT coalesce(sum(cost),0) FROM "+DBName+".table2 WHERE date <= '"+date+"')" +
                    " + (SELECT coalesce(sum(sumOfBet),0) FROM "+DBName+".table3 WHERE date <= '"+date+"')" +
                    " + (SELECT coalesce(sum(goldPrice),0) FROM "+DBName+".table4 WHERE date <= '"+date+"')" +
                    " + (SELECT coalesce(sum(silverPrice),0) FROM "+DBName+".table4 WHERE date <= '"+date+"')))) AS 'cash')" +
                    " + (SELECT * FROM (SELECT coalesce(startSum,0) " +
                    "FROM "+DBName+".updateAllTable WHERE ID = (SELECT MAX(ID) FROM "+DBName+".updateAllTable)) as t);");
            if (getCashbox.next()) {
                return getCashbox.getString(1);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
        return null;
    }
/*
    public static String getCurrentCashbox() {
        try {
            Connection con = getConections();
            ResultSet getCashboxTotal = con.createStatement().executeQuery("SELECT cashierOfPrevioursRevision + startSum FROM "+DBName+".updateAllTable WHERE ID = (SELECT MAX(ID) FROM "+DBName+".updateAllTable);");
            if (getCashboxTotal.next()) {
                return getCashboxTotal.getString(1);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
        return null;
    }
*/

}
