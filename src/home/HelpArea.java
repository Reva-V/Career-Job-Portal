package home;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
public class HelpArea extends JFrame{
    JLabel head,email;
    JTextArea area; 
    JTextField id;
    JButton submit;  
    String mailid;
    String complain;
    
    public HelpArea(){
        setLayout(null);

        setTitle("HELP");
        Image icon = Toolkit.getDefaultToolkit().getImage("images\\help.png");
        setIconImage(icon);
        
        setLayout(new BorderLayout());
    	setContentPane(new JLabel(new ImageIcon("images\\help.jpg")));
        setLayout(null);
        
        head = new JLabel("Write to us!..");
        head.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
        head.setBounds(385, 50, 300, 30);
        
        email = new JLabel("Enter Your mail-id:");
        email.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
        email.setBounds(270, 150, 300, 30);
        
        id = new JTextField();
        id.setBounds(470, 150, 250, 30);
        
        
        area=new JTextArea();
        area.setBounds(250, 200, 500, 400);
        
        submit = new JButton("Submit!");
        submit.setFont(new Font("", Font.BOLD, 14));
        submit.setBounds( 440, 650, 120, 30);
        
        add(head);
        add(email);
        add(id);
        add(area);
        add(submit);
        
        //Mail(mailid,complain);19euit126
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	final String mail_id = "v.dharunprasad@gmail.com";
                final String password = "ski_job_port";

                final String from = "v.dharunprasad@gmail.com";
                final String to = "skijobportal@gmail.com";

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
                	mailid=id.getText();
                    
                    complain=area.getText();

                    Message message = new MimeMessage(session);

                    message.setFrom(new InternetAddress(from));

                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                    message.setSubject("SKI PORTAL QUERY");
                    System.out.println(mailid+complain);
                    message.setText("E-Mail of user:"+mailid+"\n\n"+complain);

                    Transport.send(message);
                    JOptionPane.showMessageDialog(new JFrame(), "Query Sent Successfully!");
                    System.out.println("Done");

                } catch (MessagingException ex) {
                    System.out.println(ex);
                }
                
            }
        });
        
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setVisible(true);        
    }
    
   /* public void Mail(String mailid,String complain ){
            
        
    }*/
    
    
    public static void main(String[] args) {  
    new HelpArea();  
}  
}
