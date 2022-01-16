package hellofx;

import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReadJson {

    List<String> list = new ArrayList<>();
    public Text mailLogLabel;
     String fromMail;
     String password;
     String toMail;
     String user;
    boolean isRead = false;
    int H;
    int m;

  //  public static ReadJson myJson;

    public ReadJson(){
   //     myJson = this;
    }


    public boolean readJson(){
        File file = new File(System.getProperty("user.dir"), "/json.json");
        if(!file.exists()){
            System.out.println("No json file !");
            return false;
        }

        try {

            BufferedReader b = new BufferedReader(new FileReader(file));
            JSONObject obj = new JSONObject(b.readLine());

            this.fromMail = obj.getString("fromEmail");
            this.password = obj.getString("password");
            this.toMail = obj.getString("toEmail");
            this.H = Integer.parseInt(String.valueOf(obj.getInt("hours")));
            this.m = Integer.parseInt(String.valueOf(obj.getInt("minute")));

            System.out.printf("%s%n%s%n%s%n%d:%d%n", fromMail,password,toMail, H,m);
            b.close();

            this.setRead((this.fromMail != null &&
                    this.password != null &&
                    this.toMail != null || !Objects.equals(this.password, "") &&
                    !Objects.equals(this.toMail, "") && !Objects.equals(this.fromMail, "")));

            System.out.println("Read JSON file!");
            return this.isRead();


        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Not read JSON settings!");
            this.setRead(false);
            return false;
        }
    }
    public void writeJson(){
        JSONObject obj = new JSONObject();

        obj.put("fromEmail", this.fromMail);
        obj.put("password", this.password);
        obj.put("toEmail", this.toMail);
        obj.put("hours", Integer.valueOf(this.H));
        obj.put("minute", Integer.valueOf(this.m));
      //  obj.put("user", DBName);
        File file = new File(System.getProperty("user.dir"), "/json.json");
        try {
            FileWriter fileWrite = new FileWriter(file, false);
            fileWrite.write(obj.toString());
            fileWrite.flush();
            fileWrite.close();
            System.out.println("Saved email settings to JSON!");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Text getMailLogLabel() {
        return mailLogLabel;
    }

    public void setMailLogLabel(Text mailLogLabel) {
        this.mailLogLabel = mailLogLabel;
    }
    public String getFromMail() {
        return fromMail;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setFromMail(String fromMail) {
        this.fromMail = fromMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToMail() {
        return toMail;
    }

    public void setToMail(String toMail) {
        this.toMail = toMail;
    }

    ReadJson(String fromMail, String password, String toMail, int h,int m, Text mailLogLabel){
        this.mailLogLabel = mailLogLabel;
        this.fromMail = fromMail;
        this.password = password;
        this.toMail = toMail;
        this.H = h;
        this.m = m;
    }

    public int getH() {
        return H;
    }

    public void setH(int h) {
        H = h;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }


}
