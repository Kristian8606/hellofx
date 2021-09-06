package hellofx;

import java.io.IOException;
import java.net.URISyntaxException;

import java.sql.SQLException;

import static hellofx.DBConector.DBName;


public class BackupDB {

    public static boolean Backupdbtosql() throws URISyntaxException, SQLException, IOException, ClassNotFoundException {

        //  String path = System.getProperty("user.dir");
        Process p = null;
        //TODO bakup version
        try {
            Runtime runtime = Runtime.getRuntime();
            p = runtime.exec("\"C:\\Program Files\\MariaDB 10.4\\bin\\mysqldump\" -uroot -padmin -B "+DBName+" -r Backup.sql");
            int processComplete = p.waitFor();

            if (processComplete == 0) {

                System.out.println("Backup created successfully!");
                return true;
            } else {
                System.out.println("Backup failed");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        return false;
    }

    public static void RestoreDB() throws IOException, SQLException, ClassNotFoundException {
        //mysql -u root -padmin < Backup.sql

        // String[] restoreCmd = new String[]{"mysql"," -uroot", "-padmin", "DB"," <"," /Users/kristiandimitrov/IdeaProjects/hellofx/target/backup/Backup.sql"};
        String[] executeCmd = new String[]{
                "/usr/local/Cellar/mariadb/10.3.14/bin/mysql -uroot -padmin -e source /Users/kristiandimitrov/IdeaProjects/hellofx/target/backup/Backup.sql"};

        Process runtimeProcess;
        try {

            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                System.out.println("Restored successfully!");
            } else {
                System.out.println("Could not restore the backup!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
