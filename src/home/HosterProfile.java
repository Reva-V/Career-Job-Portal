package home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class HosterProfile extends JFrame {
	JLabel lbl_title;
	JLabel lbl_companyName;
	JLabel lbl_email;
	JLabel lbl_phone;
	JLabel lbl_address;
	JLabel lbl_password;
	JLabel lbl_confirmPassword;
	
	JTextField txt_companyName;
	JTextField txt_email;
	JTextField txt_phone;
	JTextField txt_address;
	JPasswordField txt_password;
	JPasswordField txt_confirmPassword;
	JButton btn_update;
		
	public HosterProfile(String email) throws ClassNotFoundException, SQLException {
		setTitle("Job Hoster update form");				// frame properties
		setLayout(null);
		setVisible(true);
		
		setLayout(new BorderLayout());
    	setContentPane(new JLabel(new ImageIcon("images\\job.jpg")));
        setLayout(null);
		
		lbl_title = new JLabel("YOUR PROFILE");  			// object creation
		lbl_companyName = new JLabel("Company Name");
		txt_companyName = new JTextField();
		lbl_email = new JLabel("E-mail");
		txt_email = new JTextField();
		lbl_phone = new JLabel("Phone");
		txt_phone = new JTextField();
		lbl_address = new JLabel("Address");
		txt_address = new JTextField();
		lbl_password = new JLabel("Password");
		txt_password = new JPasswordField();
		lbl_confirmPassword = new JLabel("Confirm password");
		txt_confirmPassword = new JPasswordField();
		btn_update = new JButton("Update");
		
		lbl_title.setBounds(380, 50, 300, 60);						// setting bounds
		lbl_companyName.setBounds(250, 150, 140, 25);
		txt_companyName.setBounds(450, 150, 200, 25);
		lbl_email.setBounds(250, 200, 140, 25);
		txt_email.setBounds(450, 200, 200, 25);
		lbl_phone.setBounds(250, 250, 140, 25);
		txt_phone.setBounds(450, 250, 200, 25);
		lbl_address.setBounds(250, 300, 140, 25);
		txt_address.setBounds(450, 300, 200, 25);
		lbl_password.setBounds(250, 350, 140, 25);
		txt_password.setBounds(450, 350, 200, 25);
		lbl_confirmPassword.setBounds(250, 400, 140, 25);
		txt_confirmPassword.setBounds(450, 400, 200, 25);
		btn_update.setBounds(400, 470, 100, 25);
		
		lbl_title.setFont(new Font("Bookman Old Style",Font.BOLD,20));			// label styling
		lbl_companyName.setFont(new Font("", Font.BOLD, 14));
		lbl_email.setFont(new Font("", Font.BOLD, 14));
		lbl_phone.setFont(new Font("", Font.BOLD, 14));
		lbl_address.setFont(new Font("", Font.BOLD, 14));
		lbl_password.setFont(new Font("", Font.BOLD, 14));
		lbl_confirmPassword.setFont(new Font("", Font.BOLD, 14));
		btn_update.setFont(new Font("", Font.BOLD, 14));
		
		txt_companyName.setEditable(false);								// setting constraints
		txt_email.setEditable(false);
		
		txt_password.setToolTipText("Password must have - numbers, alphabtes, symbols and 8-20(len)");
		
		txt_companyName.setToolTipText("Company name");
		txt_email.setToolTipText("e-mail id");
		txt_phone.setToolTipText("Mobile number");
		txt_address.setToolTipText("Address");
		txt_password.setToolTipText("Password");
		txt_confirmPassword.setToolTipText("Re-enter password");
		
		add(lbl_title);								// adding components
		add(lbl_companyName);
		add(txt_companyName);
		add(lbl_email);
		add(txt_email);
		add(lbl_phone);
		add(txt_phone);
		add(lbl_address);
		add(txt_address);
		add(lbl_password);
		add(txt_password);
		add(lbl_confirmPassword);
		add(txt_confirmPassword);
		add(btn_update);
		
		Border b = BorderFactory.createMatteBorder(0, 0, 2, 0,Color.BLACK);
        txt_companyName.setOpaque(false);
        txt_companyName.setBorder(b);
        txt_email.setOpaque(false);
        txt_email.setBorder(b);
        txt_phone.setOpaque(false);
        txt_phone.setBorder(b);
        txt_address.setOpaque(false);
        txt_address.setBorder(b);
        txt_password.setOpaque(false);
        txt_password.setBorder(b);
        txt_confirmPassword.setOpaque(false);
        txt_confirmPassword.setBorder(b);
		
		setSize(1000, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		txt_confirmPassword.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@SuppressWarnings("deprecation")
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(txt_password.getText().equals(txt_confirmPassword.getText())) {
					txt_confirmPassword.setBorder(new LineBorder(Color.GREEN));
				}
				else {
					txt_confirmPassword.setBorder(new LineBorder(Color.RED));
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
		btn_update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if(txt_password.getText().equals(txt_confirmPassword.getText())) {
						UtilityClass.updateHoster(txt_companyName.getText(), txt_email.getText(), txt_phone.getText(), txt_address.getText(), txt_password.getText());
					}
					else {
						JOptionPane.showMessageDialog(new JFrame(), "Confirm your password", "ERROR", JOptionPane.WARNING_MESSAGE);
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		ResultSet rs = UtilityClass.DBToHosterProfile(email);
		while(rs.next()) {
			txt_companyName.setText(rs.getString("companyname"));
			txt_email.setText(rs.getString("email"));
			txt_phone.setText(rs.getString("phone"));
			txt_address.setText(rs.getString("address"));
			txt_password.setText(rs.getString("password"));
		}
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		new HosterProfile("19euit126@skcet.ac.in");
	}

}