package home;

import java.awt.*;
import java.sql.*;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class UtilityClass {
	private static Connection connection;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521", "rethu", "rethu");
		return connection;
	}
	
	public static boolean isValidHosterReg(String companyName, String email, String phone, String address, String password) {
		if(companyName == "") {
			JOptionPane.showMessageDialog(new Frame(), "Please fill the Company Name", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else if(email == null) {
			JOptionPane.showMessageDialog(new Frame(), "Please fill the Email", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else if(!Pattern.matches("^[a-z0-9_.-]+@[a-z0-9.-]+$", email)) {  //^[a-z0-9+_.-]+@[a-z0-9.-]?$
			JOptionPane.showMessageDialog(new Frame(), "Please enter valid Email", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else if(phone == null) {
			JOptionPane.showMessageDialog(new Frame(), "Please fill the Phone number", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else if(!Pattern.matches("^[0-9]{10}$", phone)) {
			JOptionPane.showMessageDialog(new Frame(), "Please enter valid Phone number", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		//*
		else if(address == null) {
			JOptionPane.showMessageDialog(new Frame(), "Please fill the Address", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}	
		else if(!Pattern.matches("[a-zA-Z0-9]*{10}", password)) {
			JOptionPane.showMessageDialog(new Frame(), "Please enter valid Password", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else if(password == null) {
			JOptionPane.showMessageDialog(new Frame(), "Please enter the Password", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}		
		else {
			return true;
		}
	}
	
	public static boolean isValidSeekerReg(String name, String age, String gender, String percent_10, String percent_12, String degree, String cgpa, String address, String email, String phone, String password,String companyname,String position) {
		if (Pattern.matches("^[a-zA-Z ]*$", name) == false) {
			JOptionPane.showMessageDialog(new Frame(), "Invalid name");
		}
		else if (Pattern.matches("^(60|[1-9][0-9]?)$", age) == false) {
			JOptionPane.showMessageDialog(new Frame(), "Invalid age");
		}
		
		else if (Pattern.matches("[0-9]{1,3}(\\.[0-9]{1,2})?%?", percent_10) == false) {
			JOptionPane.showMessageDialog(new Frame(), "Invalid 10th value");
		}
		
		else if (Pattern.matches("[0-9]{1,3}(\\.[0-9]{1,2})?%?", percent_12) == false) {
			JOptionPane.showMessageDialog(new Frame(), "Invalid 12th value");
		}
		
		else if (Pattern.matches("^[0-9][.][0-9][0-9]$", cgpa) == false) {	//^[<=0-<=9][.][<=0-<=9][<=0-<=9]$
			JOptionPane.showMessageDialog(new Frame(), "Invalid gpa value");
		}
		
		else if (Pattern.matches("^[\\w-\\.+]*[\\w-\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$", email) == false) {
			JOptionPane.showMessageDialog(new Frame(), "Invalid email");
		}
		
		else if (Pattern.matches("(0|91)?[0-9]{10}", phone) == false) {
			JOptionPane.showMessageDialog(new Frame(), "Enter valid mbl number");
		}
		
		else if (Pattern.matches("[a-zA-Z0-9]*{10}",password) == false) {
			JOptionPane.showMessageDialog(new Frame(), "Enter valid password");
		}
		
		return true;
	}
	
	
	public static boolean isValidJobDetails(String posDescription, String basicRequirement, String salary, String jobPeriod, String lastDate, String seats) {
		if(posDescription == null) {
			JOptionPane.showMessageDialog(new Frame(), "Please fill all the Fields", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}		
		else if(basicRequirement == null) {
			JOptionPane.showMessageDialog(new Frame(), "Please fill all the Fields", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}		
		else if(!Pattern.matches("^[0-9,]+$", salary)) {
			JOptionPane.showMessageDialog(new Frame(), "Please enter valid Salary", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else if(salary == null) {
			JOptionPane.showMessageDialog(new Frame(), "Please fill all the Fields", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}		
		else if(!Pattern.matches("^[0-9]+$", jobPeriod)) {
			JOptionPane.showMessageDialog(new Frame(), "Please enter valid Job period in months", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else if(jobPeriod == null) {
			JOptionPane.showMessageDialog(new Frame(), "Please fill all the Fields", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}		
		else if(!Pattern.matches("^[0-9]{2}(/)[0-9]{2}(/)[0-9]{4}$", lastDate)) {
			JOptionPane.showMessageDialog(new Frame(), "Please enter valid Date formate (dd/mm/yyyy)", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else if(lastDate == null) {
			JOptionPane.showMessageDialog(new Frame(), "Please fill all the Fields", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}		
		else if(!Pattern.matches("^[0-9]+$", seats)) {
			JOptionPane.showMessageDialog(new Frame(), "Please enter valid No. of Seats", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else if(seats == null) {
			JOptionPane.showMessageDialog(new Frame(), "Please fill all the Fields", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}		
		else {
			return true;
		}
	}
	
	public static void hosterRegToDB(String companyName, String email, String phone, String address, String password) throws ClassNotFoundException, SQLException {
		if(UtilityClass.isValidHosterReg(companyName, email, phone, address, password)) {
			Connection connection = UtilityClass.getConnection();
			
			String query1 = "insert into jobhosterreg values(?, ?, ?, ?, ?)";
			PreparedStatement pst1 = connection.prepareStatement(query1);
			pst1.setString(1, companyName);
			pst1.setString(2, email);
			pst1.setString(3, phone);
			pst1.setString(4, address);
			pst1.setString(5, password);
			pst1.execute();
			
			String query2 = "insert into jobhosterlogin values(?, ?)";
			PreparedStatement pst2 = connection.prepareStatement(query2);
			pst2.setString(1, email);
			pst2.setString(2, password);
			pst2.execute();
			
			connection.setAutoCommit(true);
			JOptionPane.showMessageDialog(new JFrame(), "Registered Successfully!", "Success", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static void seekerRegToDB(String name, String age, String gender, String percent_10, String percent_12, String degree, String cgpa, String address, String email, String phone, String password,String companyname,String position) throws ClassNotFoundException, SQLException {
		if(UtilityClass.isValidSeekerReg(name, age, gender, percent_10, percent_12, degree, cgpa, address, email, phone, password,companyname,position)) {
			Connection connection = UtilityClass.getConnection();
			String query1 = "insert into jobseekerreg values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
			PreparedStatement pst1 = connection.prepareStatement(query1);
			pst1.setString(1, name);
			pst1.setString(2, age);
			pst1.setString(3, gender);
			pst1.setString(4, percent_10);
			pst1.setString(5, percent_12);
			pst1.setString(6, degree);
			pst1.setString(7, cgpa);
			pst1.setString(8, address);
			pst1.setString(9, email);
			pst1.setString(10, phone);
			pst1.setString(11, password);
			pst1.setString(12, companyname);
			pst1.setString(13, position);
			pst1.execute();
			
			String query2 = "insert into jobseekerlogin values(?, ?)";
			PreparedStatement pst2 = connection.prepareStatement(query2);
			pst2.setString(1, email);
			pst2.setString(2, password);
			pst2.execute();
			
			connection.setAutoCommit(true);
		}else {
			JOptionPane.showMessageDialog(null,"Invalid registeration");
		}
	}
	
	public static void jobDetailsToDB(String companyname, String email, String posDescription, String basicRequirement, String salary, String jobPeriod, String lastDate, String seats) throws SQLException, ClassNotFoundException {
		if(isValidJobDetails(posDescription, basicRequirement, salary, jobPeriod, lastDate, seats)) {
			Connection connection = UtilityClass.getConnection();
			String query = "insert into jobDetails values(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, companyname);
			pst.setString(2, email);
			pst.setString(3, posDescription);
			pst.setString(4, basicRequirement);
			pst.setString(5, salary);
			pst.setString(6, jobPeriod);
			pst.setString(7, lastDate);
			pst.setString(8, seats);
			pst.execute();
			connection.setAutoCommit(true);
			JOptionPane.showMessageDialog(new JFrame(), "Posted Successfully!", "Success", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static boolean isValidHosterLogin(String email, String password) throws ClassNotFoundException, SQLException {
		Connection connection = UtilityClass.getConnection();
		String query = "select password from jobhosterlogin where email = ?";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setString(1, email);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			if(rs.getString("password").equals(password)) {
				return true;
			}
			
		}
		return false;
	}
	
	public static boolean isValidSeekerLogin(String email, String password) throws ClassNotFoundException, SQLException {
		//System.out.println(email+" "+password);
		Connection connection = UtilityClass.getConnection();
		String query = "select password from jobseekerlogin where email = ?";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setString(1, email);
		
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getString("password"));
			if(rs.getString("password").equals(password)) {
				return true;
			}
			
		}
		return false;
	}
	
	public static ResultSet DBToSeekerProfile(String email) throws ClassNotFoundException, SQLException {
		Connection connection = UtilityClass.getConnection();
		String query = "select * from jobseekerreg where email = ?";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setString(1, email);
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
	
	
	
	public static void updateSeeker(String name, String age, String gender, String percent_10, String percent_12, String degree, String cgpa, String address, String email, String phone, String password,String company,String position) throws ClassNotFoundException, SQLException {
		if(UtilityClass.isValidSeekerReg(name, age, gender, percent_10, percent_12, degree, cgpa, address, email, phone, password,company,position)) {
			Connection connection = UtilityClass.getConnection();
			String query1 = "update jobseekerlogin set password = ? where email = ?";
			PreparedStatement pst1 = connection.prepareStatement(query1);
			pst1.setString(1, password);
			pst1.setString(2, email);
			pst1.executeQuery();
			
			String query2 = "update jobseekerreg set age = ?, percent_10 = ?, percent_12 = ?, degree = ?, cgpa = ?, address = ?, phone = ?, password = ? where email = ?";
			PreparedStatement pst2 = connection.prepareStatement(query2);
			pst2.setString(1, age);
			pst2.setString(2, percent_10);
			pst2.setString(3, percent_12);
			pst2.setString(4, degree);
			pst2.setString(5, cgpa);
			pst2.setString(6, address);
			pst2.setString(7, phone);
			pst2.setString(8,  password);
			pst2.setString(9, email);
			pst2.executeQuery();
			connection.setAutoCommit(true);
			JOptionPane.showMessageDialog(new JFrame(), "Updated Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
		}
	
	}
	public static ResultSet DBToHosterProfile(String email) throws ClassNotFoundException, SQLException {
		Connection connection = UtilityClass.getConnection();
		String query = "select * from jobhosterreg where email = ?";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setString(1, email);
		ResultSet rs = pst.executeQuery();
		return rs;
	}
	
	
	
	public static void updateHoster(String companyname, String email, String phone, String address, String password) throws ClassNotFoundException, SQLException {
		if(UtilityClass.isValidHosterReg(companyname , email, phone, address, password)) {
			Connection connection = UtilityClass.getConnection();
			String query1 = "update jobhosterlogin set password = ? where email = ?";
			PreparedStatement pst1 = connection.prepareStatement(query1);
			pst1.setString(1, password);
			pst1.setString(2, email);
			pst1.executeQuery();
			
			//String query2 = "update jobdetails set"
			
			String query2 = "update jobhosterreg set phone = ?, address = ?, password = ? where email = ?";
			PreparedStatement pst2 = connection.prepareStatement(query2);
			pst2.setString(1, phone);
			pst2.setString(2, address);
			pst2.setString(3, password);
			pst2.setString(4, email);
			pst2.executeQuery();
			connection.setAutoCommit(true);
			JOptionPane.showMessageDialog(new JFrame(), "Updated Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public static void mailToSeeker(String email) throws ClassNotFoundException, SQLException {
		String name = "*";
		boolean flag = false;
		
		Connection connection = UtilityClass.getConnection();
		String query = "select name from jobseekerreg where email = ?";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setString(1,  email);
		
		ResultSet rs = pst.executeQuery();
	
		while(rs.next()) {
			name = rs.getString("name");
			flag = true;
		}
		
		if(flag == false) {
			JOptionPane.showMessageDialog(new Frame(), "Please, update your Email and try again", "ERROR", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
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
            message.setSubject("Job application succesful!");
            message.setText("Dear " + name + "," + "\n	Your application for the job has been processed successfully!" + "\n\n- Sri Krishna - Job Portal" + "\nThank You");

            Transport.send(message);
            System.out.println("Mail sent!");

        } catch (MessagingException e) {
            System.out.println(e);
        }   
	}
	
	public static void mailToHoster(String email) throws ClassNotFoundException, SQLException {
		String companyName = "*";
		boolean flag = false;
		
		Connection connection = UtilityClass.getConnection();
		String query = "select companyname from jobhosterreg where email = ?";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setString(1,  email);
		
		ResultSet rs = pst.executeQuery();
	
		while(rs.next()) {
			companyName = rs.getString("companyname");
			flag = true;
		}
		
		if(flag == false) {
			JOptionPane.showMessageDialog(new Frame(), "Please, update your Email and try again", "ERROR", JOptionPane.WARNING_MESSAGE);
			//return;
		}
				
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
            message.setSubject("A person applied for your job!");
            message.setText("Dear " + companyName + "," + "\n	A person applied for your job" + "\n\n- Sri Krishna - Job Portal" + "\nThank You");

            Transport.send(message);
            System.out.println("Mail sent!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }   
	}
	
	public static String isValidCode(String code) {
		String mail = "";
		try {
			UtilityClass.getConnection();
		
			String query = "select email from jobdetails where id = ?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, code);
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				mail = rs.getString("email");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), "Invalid code", "ERROR", JOptionPane.WARNING_MESSAGE);
		}
		return mail;
	}
	
	public static void update(String id) {
		try {
			UtilityClass.getConnection();
		
			String query = "select vacancy from jobdetails where id = ?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Integer seat =Integer.parseInt(rs.getString("vacancy"));
				seat=seat-1;
				String query1 = "UPDATE jobdetails SET vacancy = ? WHERE id = ?";
				PreparedStatement pst1 = connection.prepareStatement(query1);
				pst1.setString(1, seat.toString());
				pst1.setString(2, id);
				pst1.executeQuery();
			}
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
}

