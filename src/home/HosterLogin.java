package home;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class HosterLogin extends JFrame{
	JLabel lbl_title;
	JLabel lbl_email;
	JLabel lbl_password;
	JTextField txt_email;
	JPasswordField txt_password;
	JButton btn_submit;
	JButton btn_createAccount;
	JCheckBox pass;
	
	public HosterLogin(){
		setTitle("Job Hoster Login page");						// frame properties
		setLayout(null);
		setVisible(true);
		
		setLayout(new BorderLayout());
    	setContentPane(new JLabel(new ImageIcon("images\\login.jpg")));
        setLayout(null);
		
		lbl_title = new JLabel("LOGIN PAGE");  					// object creation
		lbl_email = new JLabel("E-mail ID");
		txt_email = new JTextField();
		lbl_password = new JLabel("Password");
		txt_password = new JPasswordField();
		btn_submit = new JButton("Submit");
		btn_createAccount = new JButton("Create Account");
		
		pass=new JCheckBox();
		pass.setIcon(new ImageIcon("images\\eyeclose.png"));
        pass.setSelectedIcon(new ImageIcon("images\\eyeopen.png"));
        pass.setOpaque(false);

        lbl_title.setBounds(400, 150, 250, 60);					// setting bounds
		lbl_email.setBounds(330, 250, 120, 25);
		txt_email.setBounds(420, 250, 200, 25);
		lbl_password.setBounds(330, 300, 120, 25);
		txt_password.setBounds(420, 300, 200, 25);
		btn_createAccount.setBounds(320, 370, 130, 25);
		btn_submit.setBounds(530, 370, 100, 25);
		
		lbl_title.setFont(new Font("Bookman Old Style",Font.BOLD,20)); // label styling
		lbl_email.setFont(new Font("", Font.BOLD, 14));
		lbl_password.setFont(new Font("", Font.BOLD, 14));
		
		
		
		btn_createAccount.addActionListener(new ActionListener() {				// event handling
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					new HosterReg();
				}
				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btn_submit.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if(UtilityClass.isValidHosterLogin(txt_email.getText(), txt_password.getText()))
					{
						JOptionPane.showMessageDialog(new JFrame(), "Login Successfull!", "Success", JOptionPane.WARNING_MESSAGE);
						String mail=txt_email.getText();
						System.out.println("1 "+mail);
						new JobDetails(mail);
					}
					else {
						JOptionPane.showMessageDialog(new JFrame(), "Invalid E-mail or Password", "ERROR", JOptionPane.WARNING_MESSAGE);
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		pass.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox check = (JCheckBox) e.getSource();
	            txt_password.setEchoChar(check.isSelected() ? '\u0000' : (Character) UIManager.get("PasswordField.echoChar"));
			}
		});
		txt_email.setToolTipText("Enter Admin ID");
		txt_password.setToolTipText("Enter Pass Code");

		add(lbl_title);								// adding components
		add(lbl_email);
		add(txt_email);
		add(lbl_password);
		add(txt_password);
		add(btn_createAccount);
		add(btn_submit);
		add(pass);
		
		Border b = BorderFactory.createMatteBorder(0, 0, 2, 0,Color.BLACK);
        txt_email.setOpaque(false);
        txt_email.setBorder(b);
        txt_password.setOpaque(false);
        txt_password.setBorder(b);
        
		setSize(1000, 800);
		setLocationRelativeTo(null);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new HosterLogin();
	}

}
//create table jobhosterlogin(email varchar2(25),    password varchar2(25),    foreign key(email) references jobhosterreg(email));