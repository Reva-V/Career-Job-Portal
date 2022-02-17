package home;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.regex.Pattern;
import java.util.UUID;
//import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.Border;

/*
---------------------------------------------------------------------------
create table jobdetails(
    id varchar2(40),
    companyname varchar2(25),
    email varchar2(25),
    position varchar2(25),
    requirements varchar2(25),
    salary varchar2(25),
    jobperiod varchar2(25),
    lastdateapply varchar2(25),
    vacancy varchar2(25),
    foreign key(email) references hoster_reg(email)
);
----------------------------------------------------------------------------
*/

public class JobDetails extends JFrame implements ActionListener {
	JLabel lbl_title;
	JLabel lbl_companyname;
	JTextField txt_companyname;
	JLabel lbl_email;
	JTextField txt_email;
	JLabel lbl_posDescribtion;
	JLabel lbl_basicRequirement;
	JLabel lbl_salary;
	JLabel lbl_jobPeriod;
	JLabel lbl_lastDate;
	JLabel lbl_seats;
	JTextField txt_posDescription;
	JTextField txt_basicRequirement;
	JTextField txt_salary;
	JTextField txt_jobPeriod;
	JTextField txt_lastDate;
	JTextField txt_seats;
	JButton btn_post,btn_view;
	JButton btn_anotherJob,btn_update;
	static String mail;
	public JobDetails(String mail1) {
		mail=mail1;
		//System.out.println("2 "+mail);
		setTitle("Job details");							// frame properties
		setLayout(null);
		setVisible(true);
		setLayout(new BorderLayout());
    	setContentPane(new JLabel(new ImageIcon("images\\job.jpg")));
        setLayout(null);
		lbl_title = new JLabel("JOB DETAILS");				// creating objects
		lbl_companyname = new JLabel("Company name");
		txt_companyname = new JTextField();
		lbl_email = new JLabel("E - mail");
		txt_email = new JTextField();
		lbl_posDescribtion = new JLabel("Position Describtion");
		txt_posDescription = new JTextField();
		lbl_basicRequirement = new JLabel("Basic requirements");
		txt_basicRequirement = new JTextField();
		lbl_salary = new JLabel("Salary");
		txt_salary = new JTextField();
		lbl_jobPeriod = new JLabel("Job period");
		txt_jobPeriod = new JTextField();
		lbl_lastDate = new JLabel("Last date to apply");
		txt_lastDate = new JTextField();
		lbl_seats = new JLabel("No. of seats available");
		txt_seats = new JTextField();
		btn_post = new JButton("Post");
		btn_anotherJob = new JButton("Another Job");
		btn_view=new JButton("View");
		btn_update=new JButton("Update Profile");
		
		lbl_title.setFont(new Font("Bookman Old Style", Font.BOLD, 20));			// styling components
		lbl_companyname.setFont(new Font("", Font.BOLD, 14));
		lbl_email.setFont(new Font("", Font.BOLD, 14));
		lbl_posDescribtion.setFont(new Font("", Font.BOLD, 14));
		lbl_basicRequirement.setFont(new Font("", Font.BOLD, 14));
		lbl_salary.setFont(new Font("", Font.BOLD, 14));
		lbl_jobPeriod.setFont(new Font("", Font.BOLD, 14));
		lbl_lastDate.setFont(new Font("", Font.BOLD, 14));
		lbl_seats.setFont(new Font("", Font.BOLD, 14));
		
		lbl_title.setBounds(350, 50, 250, 60);							// setting bounds
		lbl_companyname.setBounds(200, 150, 150, 25);
		txt_companyname.setBounds(400, 150, 200, 25);
		lbl_email.setBounds(200, 200, 150, 25);
		txt_email.setBounds(400, 200, 200, 25);
		lbl_posDescribtion.setBounds(200, 250, 150, 25);
		txt_posDescription.setBounds(400, 250, 200, 25);
		lbl_basicRequirement.setBounds(200, 300, 150, 25);
		txt_basicRequirement.setBounds(400, 300, 200, 25);
		lbl_salary.setBounds(200, 350, 150, 25);
		txt_salary.setBounds(400, 350, 200, 25);
		lbl_jobPeriod.setBounds(200, 400, 150, 25);
		txt_jobPeriod.setBounds(400, 400, 200, 25);
		lbl_lastDate.setBounds(200, 450, 150, 25);
		txt_lastDate.setBounds(400, 450, 200, 25);
		lbl_seats.setBounds(200, 500, 150, 25);
		txt_seats.setBounds(400, 500, 200, 25);
		btn_anotherJob.setBounds(100, 570, 150, 25);
		btn_post.setBounds(400, 570, 100, 25);
		btn_view.setBounds(600, 570, 100, 25);
		btn_update.setBounds(600, 70, 200, 25);
		
		txt_posDescription.setToolTipText("Enter job description");
		txt_salary.setToolTipText("Enter salary");
		txt_jobPeriod.setToolTipText("Enter duration of job");
		txt_lastDate.setToolTipText("Enter deadline");
		txt_seats.setToolTipText("Enter No. of openings");
		
        add(lbl_title);
		add(lbl_companyname);
		add(txt_companyname);
		add(lbl_email);
		add(txt_email);
		add(lbl_posDescribtion);
		add(txt_posDescription);
		add(lbl_basicRequirement);
		add(txt_basicRequirement);
		add(lbl_salary);
		add(txt_salary);
		add(lbl_jobPeriod);
		add(txt_jobPeriod);
		add(lbl_lastDate);
		add(txt_lastDate);
		add(lbl_seats);
		add(txt_seats);
		add(btn_anotherJob);
		add(btn_post);
		add(btn_view);
		add(btn_update);
		
		Border b = BorderFactory.createMatteBorder(0, 0, 2, 0,Color.BLACK);
        txt_companyname.setOpaque(false);
        txt_companyname.setBorder(b);
        txt_email.setOpaque(false);
        txt_email.setBorder(b);
        txt_basicRequirement.setOpaque(false);
        txt_basicRequirement.setBorder(b);
        txt_posDescription.setOpaque(false);
        txt_posDescription.setBorder(b);
        txt_salary.setOpaque(false);
        txt_salary.setBorder(b);
        txt_jobPeriod.setOpaque(false);
        txt_jobPeriod.setBorder(b);
        txt_lastDate.setOpaque(false);
        txt_lastDate.setBorder(b);
        txt_seats.setOpaque(false);
        txt_seats.setBorder(b);
        
			btn_anotherJob.addActionListener(this);
	        btn_post.addActionListener(this);
	        btn_view.addActionListener(this);
	        
	        btn_update.addActionListener(new ActionListener() {				// event handling
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						new HosterProfile(mail);
					}
					catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
	        
	        
	        setSize(1000, 800);
			setLocationRelativeTo(null);
		}
                @Override
                public void actionPerformed(ActionEvent e)
                {
                	
                	if(e.getSource()==btn_view) {
                		new HosterView(mail);
                	}
                if(e.getSource()==btn_anotherJob)	
                {
	        txt_companyname.setText("");
		txt_email.setText("");
		txt_posDescription.setText("");
		txt_basicRequirement.setText("");
		txt_salary.setText("");
		txt_jobPeriod.setText("");
		txt_lastDate.setText("");
		txt_seats.setText("");
	        }
                if(e.getSource()==btn_post)
                {
               try
                {
                String companyname=txt_companyname.getText();
                String email=txt_email.getText();
                String posDescription=txt_posDescription.getText();
                String basicRequirement=txt_basicRequirement.getText();
                String salary=txt_salary.getText();
                String jobPeriod=txt_jobPeriod.getText();
                String lastDate=txt_lastDate.getText();
                String seats=txt_seats.getText();       
                		
		if(basicRequirement == null) {
			JOptionPane.showMessageDialog(new Frame(), "Please fill all the Fields");
		}		
		else if(Pattern.matches("^[0-9,]+$", salary)==false) {
			JOptionPane.showMessageDialog(new Frame(), "Please enter valid Salary");
		}
		else if(salary == null) {
			JOptionPane.showMessageDialog(new Frame(), "Please fill all the Fields");
		}		
		else if(!Pattern.matches("^[0-9]+$", jobPeriod)) {
			JOptionPane.showMessageDialog(new Frame(), "Please enter valid Job period in months");
		}
		else if(jobPeriod == null) {
			JOptionPane.showMessageDialog(new Frame(), "Please fill all the Fields");
		}		
		else if(!Pattern.matches("^[0-9]{2}(/)[0-9]{2}(/)[0-9]{4}$", lastDate)) {
			JOptionPane.showMessageDialog(new Frame(), "Please enter valid Date formate (dd/mm/yyyy)");
		}
		else if(lastDate == null) {
			JOptionPane.showMessageDialog(new Frame(), "Please fill all the Fields");
		}		
		else if(!Pattern.matches("^[0-9]+$", seats)) {
			JOptionPane.showMessageDialog(new Frame(), "Please enter valid No. of Seats");
		}
		else if(seats == null) {
			JOptionPane.showMessageDialog(new Frame(), "Please fill all the Fields");

		}
                else
                {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","rethu","rethu");
                Statement stmt=con.createStatement();
                //String id = UUID.randomUUID().toString(); //uuid generation
                int min = 1000;  
                int max = 9999;  
                int b = (int)(Math.random()*(max-min+1)+min);
                String id = "SKI"+Integer.toString(b);
                String query="insert into jobdetails values('"+id+"','"+companyname+"','"+email+"','"+posDescription+"','"+basicRequirement+"','"+ salary+"','"+jobPeriod+"','"+lastDate+"','"+seats+"')";
                stmt.executeUpdate(query);
                con.setAutoCommit(true);
                JOptionPane.showMessageDialog(btn_post, "Job Posted Successful!");
                }
		
	    }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(this, ex.toString());
            }
            }
            }
       

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JobDetails(mail);
	}
}
//create table jobdetails(companyname varchar2(25),email varchar2(25),    position varchar2(25),    requirements varchar2(25),    salary varchar2(25),    jobperiod varchar2(25),    lastdateapply varchar2(25),    vacancy varchar2(25),    foreign key(email) references jobhosterreg(email));