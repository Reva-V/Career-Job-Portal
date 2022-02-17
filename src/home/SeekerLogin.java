package home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/*----------------------------------------------------------------------
create table seeker_login(
    email varchar2(25),
    password varchar2(25),
    foreign key(email) references seeker_details(email)
);
-----------------------------------------------------------------------
*/
public class SeekerLogin extends JFrame 
{
    JLabel jl_Email,jl_Password,label;
    JTextField txt_email;
    JPasswordField txt_pass;
    JButton login,log;
    Container co;
    String jobId;
    JCheckBox pass;
    static String company_name;
    public SeekerLogin(String jobId)
    {
        this.jobId = jobId;
        
        setLayout(new BorderLayout());
    	setContentPane(new JLabel(new ImageIcon("images\\login.jpg")));
        setLayout(null);
        
        label=new JLabel("LOGIN PAGE");
        label.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
        jl_Email=new JLabel("E-Mail");
        jl_Email.setFont(new Font("",Font.BOLD,14));
        jl_Password=new JLabel("Password");
        jl_Password.setFont(new Font("",Font.BOLD,14));
        
        pass=new JCheckBox();
		pass.setIcon(new ImageIcon("images\\eyeclose.png"));
        pass.setSelectedIcon(new ImageIcon("images\\eyeopen.png"));
		 
        label.setBounds(420,170,200,25);
        jl_Email.setBounds(370, 230, 100, 30);
        jl_Password.setBounds(370,300,100,30);
        pass.setBounds(600, 300, 50, 45);
        pass.setOpaque(false);
        
        add(jl_Email);
        add(jl_Password);
        add(label);
        add(pass);
        
        txt_email=new JTextField();
        txt_pass=new JPasswordField();
        
        txt_email.setBounds(460,230, 120, 25);
        txt_pass.setBounds(460,300, 120, 25);
        
        add(txt_email);
        add(txt_pass);
        
        login=new JButton("Login");
        login.setFont(new Font("",Font.BOLD,14));
        add(login);
        log=new JButton("Create Account");
        log.setFont(new Font("",Font.BOLD,14));
        add(log);
        login.setBounds(530,370,100,30);
        log.setBounds(360,370,150,30);
        
        //login.addActionListener(this);
        log.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e) {
        Object obj= e.getSource();
        if(obj==log)
        {
        	System.out.println("company "+company_name);
            new SeekerReg(jobId);
        }
        if(obj==login)
        {
            try
            {
                String str_email= txt_email.getText();
                String str_pass= txt_pass.getText();
                
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","reny","reny");
                con.setAutoCommit(true);
                Statement stmt=con.createStatement();
                String query="SELECT * FROM seeker_reg WHERE  email='"+str_email+"'";
               // String query1="insert into seekerlogin values('"+str_email+"','"+str_pass+"')";
               // stmt.executeUpdate(query);
                
                ResultSet rs;
                rs = stmt.executeQuery(query);
                if(rs.isBeforeFirst())
                {
                    while (rs.next())
                    {
                     String pass = rs.getString("password");
                     if(pass.equals(str_pass))
                     {
                         JOptionPane.showMessageDialog(null, "Login Success!");
                         new SeekerProfile(str_email);
                     }
                     else 
                   {
                        JOptionPane.showMessageDialog(null, "Invalid username password!"); 
                   }
                    }
                }
                  else 
                   {
                        JOptionPane.showMessageDialog(null, "Invalid username password!"); 
                   }
                   
            }
            catch(Exception ex)
            {
                 JOptionPane.showMessageDialog(null, ex.toString());
            }
        }
        	}
        });
        
        pass.addActionListener(new ActionListener(){
        	@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox check = (JCheckBox) e.getSource();
	            txt_pass.setEchoChar(check.isSelected() ? '\u0000' : (Character) UIManager.get("PasswordField.echoChar"));
			}
		});
        
        Border b = BorderFactory.createMatteBorder(0, 0, 2, 0,Color.BLACK);
        txt_email.setOpaque(false);
        txt_email.setBorder(b);
        txt_pass.setOpaque(false);
        txt_pass.setBorder(b);
        
        txt_email.setToolTipText("Enter Admin ID");
        txt_pass.setToolTipText("Enter Pass Code");
        setVisible(true);
        setSize(1000, 800);
        setLocationRelativeTo(null);
    }
    public static void main(String args[])
    {
    	//String company_name="";
       new SeekerLogin("");
        
    }  
}
//create table jobseekerlogin(email varchar2(25),    password varchar2(25),    foreign key(email) references jobseekerreg(email));