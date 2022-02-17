package home;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class SeekerProfile extends JFrame implements ActionListener {
	JLabel jl_label, jl_name, jl_age, jl_gender, jl_qualify, jl_qualify1, jl_qualify2, jl_gpa, jl_add, jl_id, jl_mbl, jl_pass, jl_confirmPassword;
	JTextField txt_name, txt_age, txt_percent_10, txt_percent12, txt_degree, txt_cgpa, txt_address, txt_email, txt_phone;
	JPasswordField txt_password;
	JPasswordField txt_confirmPassword;
	Container co;
	JButton btn_update;
	ButtonGroup btngroup;
	JRadioButton jrb_male, jrb_female;

	public SeekerProfile(String email) throws ClassNotFoundException, SQLException {
		
		setLayout(new BorderLayout());
    	setContentPane(new JLabel(new ImageIcon("images\\seeker.jpg")));
        setLayout(null);

		jl_label = new JLabel("PROFILE", JLabel.CENTER);
		jl_label.setFont(new Font("Bookman Old Style",Font.BOLD,20));
		jl_name = new JLabel("Name");
		jl_name.setFont(new Font("", Font.BOLD, 14));
		jl_age = new JLabel("Age");
		jl_age.setFont(new Font("", Font.BOLD, 14));
		jl_gender = new JLabel("Gender");
		jl_gender.setFont(new Font("", Font.BOLD, 14));
		jl_qualify = new JLabel("Qualification : 10th %");
		jl_qualify.setFont(new Font("", Font.BOLD, 14));
		jl_qualify1 = new JLabel("                       : 12th %");
		jl_qualify1.setFont(new Font("", Font.BOLD, 14));
		jl_qualify2 = new JLabel("                       : Degree");
		jl_qualify2.setFont(new Font("", Font.BOLD, 14));
		jl_gpa = new JLabel("GPA");
		jl_gpa.setFont(new Font("", Font.BOLD, 14));
		jl_add = new JLabel("Address");
		jl_add.setFont(new Font("", Font.BOLD, 14));
		jl_id = new JLabel("E-Mail");
		jl_id.setFont(new Font("", Font.BOLD, 14));
		jl_mbl = new JLabel("Mobile");
		jl_mbl.setFont(new Font("", Font.BOLD, 14));
		jl_pass = new JLabel("Password");
		jl_pass.setFont(new Font("", Font.BOLD, 14));
		jl_confirmPassword = new JLabel("Confirm Password");
		jl_confirmPassword.setFont(new Font("", Font.BOLD, 14));

		jl_label.setBounds(315, 50, 300, 50);
		jl_name.setBounds(50, 150, 120, 25);
		jl_age.setBounds(50, 200, 120, 25);
		jl_gender.setBounds(50, 250, 120, 25);
		jl_qualify.setBounds(50, 300, 150, 25);
		jl_qualify1.setBounds(50, 350, 150, 25);
		jl_qualify2.setBounds(50, 400, 200, 25);
		jl_gpa.setBounds(500, 150, 120, 25);
		jl_add.setBounds(500, 200, 120, 25);
		jl_id.setBounds(500, 250, 120, 25);
		jl_mbl.setBounds(500, 300, 120, 25);
		jl_pass.setBounds(500, 350, 120, 25);
		jl_confirmPassword.setBounds(500, 400, 200, 25);

		add(jl_label);
		add(jl_name);
		add(jl_age);
		add(jl_gender);
		add(jl_qualify);
		add(jl_qualify1);
		add(jl_qualify2);
		add(jl_gpa);
		add(jl_add);
		add(jl_id);
		add(jl_mbl);
		add(jl_pass);
		add(jl_confirmPassword);

		txt_name = new JTextField();
		txt_age = new JTextField();

		btngroup = new ButtonGroup();
		jrb_male = new JRadioButton("male");
		jrb_female = new JRadioButton("female");

		btngroup.add(jrb_male);
		btngroup.add(jrb_female);
		txt_percent_10 = new JTextField();
		txt_percent12 = new JTextField();
		txt_degree = new JTextField();
		txt_cgpa = new JTextField();
		txt_address = new JTextField();
		txt_email = new JTextField();
		txt_phone = new JTextField();
		txt_password = new JPasswordField();
		txt_confirmPassword = new JPasswordField();

		// txt_Pass.setToolTipText("ex:Shiny@779,minimum 8 char");

		txt_name.setBounds(220, 150, 200, 25);
		txt_name.setEditable(false);
		txt_age.setBounds(220, 200, 200, 25);
		jrb_male.setBounds(220, 250, 60, 25);
		jrb_male.setEnabled(false);
		jrb_female.setBounds(300, 250, 70, 25);
		jrb_female.setEnabled(false);
		txt_percent_10.setBounds(220, 300, 200, 25);
		txt_percent12.setBounds(220, 350, 200, 25);
		txt_degree.setBounds(220, 400, 200, 25);
		txt_cgpa.setBounds(650, 150, 200, 25);
		txt_address.setBounds(650, 200, 200, 25);
		txt_email.setBounds(650, 250, 200, 25);
		txt_email.setEditable(false);
		txt_phone.setBounds(650, 300, 200, 25);
		txt_password.setBounds(650, 350, 200, 25);
		txt_confirmPassword.setBounds(650, 400, 200, 25);

		add(txt_name);
		add(txt_age);
		add(jrb_male);
		add(jrb_female);
		add(txt_percent_10);
		add(txt_percent12);
		add(txt_degree);
		add(txt_cgpa);
		add(txt_address);
		add(txt_email);
		add(txt_phone);
		add(txt_password);
		add(txt_confirmPassword);
		
		btn_update = new JButton("Update");
		btn_update.setFont(new Font("", Font.BOLD, 14));
		btn_update.setBounds(400, 500, 120, 30);
		add(btn_update);

		
		btn_update.addActionListener(this);
		txt_confirmPassword.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
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
		
		ResultSet rs = UtilityClass.DBToSeekerProfile(email);		/////////////////////
		while(rs.next()) {
			txt_name.setText(rs.getString("name"));
			txt_age.setText(rs.getString("age"));
			if(rs.getString("gender").equals("male")) {
				jrb_male.setSelected(true);
			}
			else {
				jrb_female.setSelected(true);
			}
			txt_percent_10.setText(rs.getString("percent_10"));
			txt_percent12.setText(rs.getString("percent_12"));
			txt_degree.setText(rs.getString("degree"));
			txt_cgpa.setText(rs.getString("cgpa"));
			txt_address.setText(rs.getString("address"));
			txt_email.setText(rs.getString("email"));
			txt_phone.setText(rs.getString("phone"));
			txt_password.setText(rs.getString("password"));
		}
		txt_name.setToolTipText("Enter Name");
		txt_age.setToolTipText("Enter age");
		txt_percent_10.setToolTipText("Enter 10th std percentage");
		txt_percent12.setToolTipText("Enter 12th std percentage");
		txt_degree.setToolTipText("Enter Degree in words");
		txt_cgpa.setToolTipText("Enter CGPA in 0.00");
		txt_address.setToolTipText("Enter Address");
		txt_email.setToolTipText("Enter e-mail id");
		txt_password.setToolTipText("Enter password(8char length)");
		txt_phone.setToolTipText("Enter mobile number");
		
		Border b = BorderFactory.createMatteBorder(0, 0, 2, 0,Color.BLACK);
		txt_name.setOpaque(false);
        txt_name.setBorder(b);
        txt_age.setOpaque(false);
        txt_age.setBorder(b);
        txt_percent_10.setOpaque(false);
        txt_percent_10.setBorder(b);
        txt_percent12.setOpaque(false);
        txt_percent12.setBorder(b);
        txt_degree.setOpaque(false);
        txt_degree.setBorder(b);
        txt_cgpa.setOpaque(false);
        txt_cgpa.setBorder(b);
        txt_address.setOpaque(false);
        txt_address.setBorder(b);
        txt_email.setOpaque(false);
        txt_email.setBorder(b);
        txt_password.setOpaque(false);
        txt_password.setBorder(b);
        txt_phone.setOpaque(false);
        txt_phone.setBorder(b);
        txt_confirmPassword.setOpaque(false);
        txt_confirmPassword.setBorder(b);
		
		setSize(1000, 800);
        setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj_source = e.getSource();
		if (e.getSource() == btn_update) {
			try {
				String name = txt_name.getText();
				String age = txt_age.getText();
				String gender = "";
				String percent_10 = txt_percent_10.getText();
				String percent_12 = txt_percent12.getText();
				String degree = txt_degree.getText();
				String cgpa = txt_cgpa.getText();
				String address = txt_address.getText();
				String email = txt_email.getText();
				String phone = txt_phone.getText();
				String password = txt_password.getText();
				
				
				if (jrb_male.isSelected()) {
					gender = "male";
				} else if (jrb_female.isSelected()) {
					gender = "female";
				} else {
					JOptionPane.showMessageDialog(co, "please select the gender");
				}
				
				if(txt_password.getText().equals(txt_confirmPassword.getText())) {
					UtilityClass.updateSeeker(name, age, gender, percent_10, percent_12, degree, cgpa, address, email, phone, password,"","");
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "Confirm your password", "ERROR", JOptionPane.WARNING_MESSAGE);
				}
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(btn_update, "User already exists");
				System.out.println(ex);
			}
			
		}
	}
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		new SeekerProfile("vishnu20020320@gmail.com");
	}
}
