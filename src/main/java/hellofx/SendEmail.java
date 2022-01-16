package hellofx;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static hellofx.DBConector.DBName;


public class SendEmail {

    public static void send(){
        try {
         String username =  Controller.myController.json.getFromMail();
         String password =  Controller.myController.json.getPassword();//"uhgpqkahokhoxrnq"
         String toUsername =  Controller.myController.json.getToMail();
        String user = DBName;
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

            String strMessage = new String("List: "+ String.join(", ", Controller.myController.json.getList()));


            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toUsername)
            );
            message.setSubject(user);
            // Send the actual HTML message.
            message.setContent(
                    ""+strMessage+"",
                    "text/html");

            Transport.send(message);

            System.out.println("Send Email Done "+Time.getTime()+"");
            Controller.myController.json.getMailLogLabel().setText("email sent "+Time.getTime()+" done");
            System.out.println(strMessage);
           // ExeptionDialog.alertDialog("Еmail sent successfully!");
        } catch (MessagingException e) {
            ExeptionDialog.alertDialog( "Not send");
            e.printStackTrace();
        }
    }




}
