package hellofx;


import java.io.*;

import java.util.ArrayList;
import java.util.List;

public class TextFile {

    public static void WriteTextFile(String host, String databaseName, String user, String pass, String port) {
        File file = new File(System.getProperty("user.dir"), "/hellofxFile.txt");
        if (!file.exists()) {
            try {
                FileWriter writer = new FileWriter(file, true);
                PrintWriter printWriter = new PrintWriter(writer);
                printWriter.printf("%s%n%s%n%s%n%s%n%s", host, databaseName, user, pass, port);
                printWriter.close();
                ExeptionDialog.alertDialog("Create file!");
            } catch (Exception ex) {
                ExeptionDialog.alertDialog(ex.toString());
                System.out.println(ex);
            }
        } else {

            try {
                List<String> listReadFiles = new ArrayList<>();
                List<String> listGeting = new ArrayList<>();
                listGeting.add(host);
                listGeting.add(databaseName);
                listGeting.add(user);
                listGeting.add(pass);
                listGeting.add(port);
                //  listReadFiles = Files.readAllLines(Paths.get(inputFile.toString()), StandardCharsets.UTF_8);
                //   listReadFiles.addAll(listGeting);
                BufferedReader b = new BufferedReader(new FileReader(file));

                String readLine = "";

                while ((readLine = b.readLine()) != null) {
                    System.out.println(readLine);
                    listReadFiles.add(readLine);
                }


                int count = Math.min(listReadFiles.size(), listGeting.size());
                for (int i = 0; i < count; i++) {
                    if (!listReadFiles.get(i).equals(listGeting.get(i))) {
                        if (file.exists()) {
                            file.delete();
                        }
                        FileWriter writer = new FileWriter(file, true);
                        PrintWriter printWriter = new PrintWriter(writer);
                        printWriter.printf(String.join("%n", listGeting));
                        printWriter.close();
                        break;

                    }
                }
            } catch (Exception e) {
                ExeptionDialog.alertDialog(e.toString());
                System.out.println(e);
            }
        }
    }

    public static List<String> ReadTextFile() {

        File f = new File(System.getProperty("user.dir"), "/hellofxFile.txt");
        List<String> list = new ArrayList<>();
        try {

            BufferedReader b = new BufferedReader(new FileReader(f));

            String readLine = "";

            while ((readLine = b.readLine()) != null) {
                System.out.println(readLine);
                list.add(readLine);
            }
            return (list.size() < 4) ? null : list;

        } catch (Exception e) {
            System.out.println(e);
            ExeptionDialog.alertDialog(e.toString());
        }
        return null;
    }
}
