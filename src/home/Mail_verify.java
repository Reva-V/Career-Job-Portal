package home;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;

public class Mail_verify {
	public Mail_verify(String email){
        int min = 200;  
        int max = 400;  
        int b = (int)(Math.random()*(max-min+1)+min);
        String code = "SKI"+Integer.toString(b);
        
        final String mail_id = "skijobportal@gmail.com";
        final String password = "ski_job_port";

        final String from = "skijobportal@gmail.com";
        final String to = email;

        Properties property = new Properties();
                
        property.put("mail.smtp.host", "smtp.gmail.com");
        property.put("mail.smtp.socketFactory.port", "465");
        property.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        property.put("mail.smtp.auth", "true");
        property.put("mail.smtp.port", "465");

        Authenticator a = new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(mail_id, password);

            }

        };

        Session session = Session.getInstance(property, a);

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("ACCOUNT VERIFICATION");
            message.setText("Use this code for your account confirmation:"+code+"\n\n\t\t\t"+"THANK YOU!");

            Transport.send(message);

            System.out.println("Done");
            new Mail_UI(code,email);

        } catch (MessagingException e) {
            System.out.println(e);
        }
    }
    
    public static void main(String args[]){
    	//new Mail_verify("");
        //s1.Mail();
    }
}
