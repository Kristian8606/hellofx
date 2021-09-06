package hellofx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBConector {
    private static Connection connection;
    public static String DBHost;
    public static String DBName;
    public static String DBUser;
    public static String DBPassword;
    public static String DBPort;

    public static Connection getConections() throws SQLException, ClassNotFoundException {
        if (DBHost == null) {
            List<String> dataToConnect = TextFile.ReadTextFile();
            //TODO != null

            DBHost = dataToConnect.get(0);
            DBName = dataToConnect.get(1);
            DBUser = dataToConnect.get(2);
            DBPassword = dataToConnect.get(3);
            DBPort = dataToConnect.get(4);
        }
        //&serverTimezone=UTC
        // ?useSSL=false
        // Class.forName("org.mariadb.jdbc.Driver");


       // System.out.println(connection.isClosed());

        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:mariadb://" + DBHost + ":" + DBPort + "/" + DBName + "?useSSL=false", DBUser, DBPassword);
            System.out.println("Connect DB");
        }else if ((!connection.isValid(1)) || connection.isClosed()){
            connection = DriverManager.getConnection("jdbc:mariadb://" + DBHost + ":" + DBPort + "/" + DBName + "?useSSL=false", DBUser, DBPassword);
            System.out.println("Connect is Closed or Connect is not Valid");
            System.out.println("Reconnect DB");
        }




        return connection;

    }

    public static void closeConnection() {
        /*
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }

         */

    }

}
