package hellofx;

import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class ReadJson {
    File file = new File(System.getProperty("user.dir"), "/json.json");
    List<String> list;
    public Text mailLogLabel;
    String fromMail;
    String password;
    String toMail;
    int H;
    int m;

    public static ReadJson myJson;

    public ReadJson(){
        myJson = this;
    }


    public boolean readJson(){

        if(!file.exists()){
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
            System.out.println("Read email JSON settings!");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Not read email JSON settings!");
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

        try {
            this.file = new File(System.getProperty("user.dir"), "/json.json");
            FileWriter fileWrite = new FileWriter(this.file, false);
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
