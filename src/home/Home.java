package home;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;  
import java.awt.*;
import java.awt.Font;
import java.awt.Color;


public class Home extends JFrame {

    JLabel lbl_head, lbl_des, lbl_cop, lbl_contact,lbl_1,lbl_2,lbl_3,img;
    JButton jbtn_admin, jbtn_hoster, jbtn_seeker, jbtn_help;

    public Home() {
        
        setTitle("HOME");
        Image icon = Toolkit.getDefaultToolkit().getImage("images\\home.png");  
        setIconImage(icon);
        
        
        setLayout(new BorderLayout());
    	setContentPane(new JLabel(new ImageIcon("images\\cmp1.jpg")));
    	setLayout(null);

        lbl_head = new JLabel("SRI KRISHNA - JOB PORTAL", JLabel.CENTER);
        lbl_head.setFont(new Font("Bookman Old Style", 3, 40));
        lbl_head.setForeground(Color.BLACK);
        //lbl_head.setBorder(javax.swing.BorderFactory.createEtchedBorder(150, Color.lightGray, Color.black));
        
        lbl_des = new JLabel();
        lbl_des.setText("<html><p style=\"border-style:groove; border-width: 7px; border-color: #a8ccd7; text-align: center;\"><br>&emsp Finding the right candidate with desired qualifications to fill their current job openings is an important task for the recruiters of any organization.<br><br>&emsp We provide an easy and convenient search application for the job seekers to find their desired jobs and for the recruiters to find the right candidate.<br> &emsp</p></html>");
        lbl_des.setFont(new Font("Bookman Old Style", Font.TYPE1_FONT, 20));
        
        lbl_cop = new JLabel("� Copyright 2021.All rights reserved.");
        lbl_cop.setFont(new Font("Arial Narrow", Font.ITALIC, 16));
        lbl_contact = new JLabel("Contact: 8220652529");
        lbl_contact.setFont(new Font("Bookman Old Style", Font.BOLD, 16));

        jbtn_admin = new JButton("Admin Login");
        jbtn_admin.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
        jbtn_hoster = new JButton("Job Hoster");
        jbtn_hoster.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
        jbtn_seeker = new JButton("Job Seeker");
        jbtn_seeker.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
        jbtn_help = new JButton("Help?");
        jbtn_help.setFont(new Font("Bookman Old Style", Font.BOLD, 14));

        lbl_head.setBounds(180, 100, 600, 50);
        jbtn_admin.setBounds(825, 10, 150, 35);
      
        lbl_des.setBounds(100, 170, 800, 300);
        jbtn_hoster.setBounds(100, 650, 150, 40);
        jbtn_seeker.setBounds(715, 650, 150, 40);
        lbl_cop.setBounds(380, 720, 500, 50);
        lbl_contact.setBounds(10, 0, 300, 50);
        jbtn_help.setBounds(450, 690, 80, 30);

        jbtn_admin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdminLogin();
            }
        });
        jbtn_help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HelpArea();
            }
        });
        jbtn_hoster.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HosterLogin();
            }
        });
        jbtn_seeker.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new SeekerView();
                
            }
        });
        
        add(lbl_head);
        add(lbl_des);
        add(lbl_contact);
        add(lbl_cop);
        add(jbtn_admin);
        add(jbtn_hoster);
        add(jbtn_seeker);
        add(jbtn_help);
        
        //adding images
        JLabel img1=new JLabel();
        JLabel img2=new JLabel();
        
        img1.setIcon(new ImageIcon("C:\\Users\\rethe\\eclipse-workspace\\CAREER_JOB_PORTAL\\images\\job.jpg"));
        img1.setBounds(95, 490, 170, 150);
        add(img1);
        img2.setIcon(new ImageIcon("C:\\Users\\rethe\\eclipse-workspace\\CAREER_JOB_PORTAL\\images\\jobsearch.jpg"));
        img2.setBounds(705, 490, 170, 150);
        add(img2);
        
        
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Home();
    }
}
//Job seekers from any background can search for the current job openings. Job seekers can register with the application and update their details and skill set.