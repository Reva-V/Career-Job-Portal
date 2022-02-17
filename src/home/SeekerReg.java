package home;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class SeekerReg extends JFrame {
	JLabel jl_Position, jl_Position_value,jl_label, jl_name, jl_age, jl_gender, jl_qualify, jl_qualify1, jl_qualify2, jl_gpa, jl_add, jl_id, jl_mbl, jl_pass, jl_confirmPassword;
	JTextField txt_name, txt_age, txt_percent_10, txt_percent12, txt_degree, txt_cgpa, txt_address, txt_email, txt_phone;
	JPasswordField txt_password;
	JPasswordField txt_confirmPassword;
	Container co;
	JButton sign_up;
	ButtonGroup btngroup;
	JRadioButton jrb_male, jrb_female;
    String position1 = "";
    String company_name;
    
	SeekerReg(String jobId) {
		
		setLayout(new BorderLayout());
    	setContentPane(new JLabel(new ImageIcon("images\\reg.jpg")));
        setLayout(null);
        
                try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","rethu","rethu");
                con.setAutoCommit(true);
                Statement stmt=con.createStatement();
                String query="SELECT position,companyname FROM jobdetails WHERE id='" + jobId + "'";
                ResultSet rs;

                rs = stmt.executeQuery(query); 
                
                if(rs.isBeforeFirst() && rs.next()){
                    position1 = rs.getString("position");
                    company_name = rs.getString("companyname");
                }
            }
                catch (Exception e){
                System.out.println(e);
            }
		co = getContentPane();
		co.setLayout(null);

		jl_label = new JLabel("REGISTERATION FORM", JLabel.CENTER);
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
                jl_Position = new JLabel("Position");
                jl_Position.setFont(new Font("",Font.BOLD,14));
                jl_Position_value = new JLabel(position1);
                //System.out.println(position1);
                jl_Position_value.setFont(new Font("",Font.BOLD,14));

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
        jl_Position.setBounds(500,450,120,25);

		co.add(jl_label);
		co.add(jl_name);
		co.add(jl_age);
		co.add(jl_gender);
		co.add(jl_qualify);
		co.add(jl_qualify1);
		co.add(jl_qualify2);
		co.add(jl_gpa);
		co.add(jl_add);
		co.add(jl_id);
		co.add(jl_mbl);
		co.add(jl_pass);
		co.add(jl_confirmPassword);
        co.add(jl_Position);

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

		txt_name.setBounds(220, 150, 200, 25);
		txt_age.setBounds(220, 200, 200, 25);
		jrb_male.setBounds(220, 250, 60, 25);
		jrb_female.setBounds(300, 250, 70, 25);
		txt_percent_10.setBounds(220, 300, 200, 25);
		txt_percent12.setBounds(220, 350, 200, 25);
		txt_degree.setBounds(220, 400, 200, 25);
		txt_cgpa.setBounds(650, 150, 200, 25);
		txt_address.setBounds(650, 200, 200, 25);
		txt_email.setBounds(650, 250, 200, 25);
		txt_phone.setBounds(650, 300, 200, 25);
		txt_password.setBounds(650, 350, 200, 25);
		txt_confirmPassword.setBounds(650, 400, 200, 25);
        jl_Position_value.setBounds(650,450,120,25);

		co.add(txt_name);
		co.add(txt_age);
		co.add(jrb_male);
		co.add(jrb_female);
		co.add(txt_percent_10);
		co.add(txt_percent12);
		co.add(txt_degree);
		co.add(txt_cgpa);
		co.add(txt_address);
		co.add(txt_email);
		co.add(txt_phone);
		co.add(txt_password);
		co.add(txt_confirmPassword);
        co.add(jl_Position_value);
		
		sign_up = new JButton("REGISTER");
		sign_up.setFont(new Font("", Font.BOLD, 14));
		sign_up.setBounds(400, 500, 120, 30);
		co.add(sign_up);

		sign_up.addActionListener(new ActionListener(){
        	@Override
			public void actionPerformed(ActionEvent e) {
        		Object obj_source = e.getSource();
        		if (e.getSource() == sign_up) {
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
        				String companyname=company_name;
        				String position=position1;
        				//System.out.println("reg company-"+companyname);
        				//System.out.println("pos company-"+position);
        				if (jrb_male.isSelected()) {
        					gender = "male";
        				} else if (jrb_female.isSelected()) {
        					gender = "female";
        				} else {
        					JOptionPane.showMessageDialog(co, "please select the gender");
        				}
        				
        				UtilityClass.update(jobId);
        				UtilityClass.seekerRegToDB(name, age, gender, percent_10, percent_12, degree, cgpa, address, email, phone, password,companyname,position);
        				JOptionPane.showMessageDialog(new JFrame(), "Registered Successfully!", "Success", JOptionPane.WARNING_MESSAGE);
        				new Mail_verify(email);
        			} catch (Exception ex) {
        				JOptionPane.showMessageDialog(sign_up, "Invalid");
        				//System.out.println("Seekrreg-"+ex);
        			}
        			
        		}
        	}
		});
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
	}


	public static void main(String args[]) {
		new SeekerReg("");
	}
}
//create table jobseekerreg(name varchar2(25),    age varchar2(25),    gender varchar2(25),percent_10 varchar2(25),percent_12 varchar2(25),    degree varchar2(25),    cgpa varchar2(25),    address varchar2(25),    email varchar2(25),    phone varchar2(25),    password varchar2(25), companyname varchar(25) , position varchar(25) primary key(email));

