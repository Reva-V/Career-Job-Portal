package home;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ApplyCode extends JFrame {
	JLabel lbl_etrCode,lbl_head,lbl_desc;
	JTextField txt_etrCode;
	JButton btn_apply;
	
	public ApplyCode() {
		setLayout(null);
		setTitle("Company Code");
		
		setLayout(new BorderLayout());
    	setContentPane(new JLabel(new ImageIcon("images\\help.jpg")));
        setLayout(null);
		
		lbl_head = new JLabel("COMPANY CODE VERIFICATION", JLabel.CENTER);
        lbl_head.setFont(new Font("Bookman Old Style", 3, 20));
        lbl_head.setForeground(Color.BLACK);
        
        lbl_desc = new JLabel("Please enter the noted company code to apply for the job", JLabel.CENTER);
        lbl_desc.setFont(new Font("Bookman Old Style", 3, 15));
        lbl_desc.setForeground(Color.BLACK);
		lbl_etrCode = new JLabel("Enter Job code");
		txt_etrCode = new JTextField();
		btn_apply = new JButton("Apply");
		
		lbl_head.setBounds(160, 100, 600, 50);
		lbl_desc.setBounds(150, 190, 600, 50);
		lbl_etrCode.setBounds(250, 300, 150, 25);
		lbl_etrCode.setFont(new Font("", Font.BOLD, 14));
		txt_etrCode.setBounds(400, 300, 200, 25);
		btn_apply.setBounds(400, 400, 100, 25);
		
		Border b = BorderFactory.createMatteBorder(0, 0, 2, 0,Color.BLACK);
        txt_etrCode.setOpaque(false);
        txt_etrCode.setBorder(b);
		
		btn_apply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				String mail=UtilityClass.isValidCode (txt_etrCode.getText());
				if(mail!="") {
					JOptionPane.showMessageDialog(new JFrame(), "You have applied for this job!");
					UtilityClass.mailToHoster(mail);
					new SeekerLogin(txt_etrCode.getText());
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Enter the correct company code!!");
				}
				}catch(Exception ex) {
					System.out.println(ex);
				}
			}
		});
		
		add(lbl_etrCode);
		add(txt_etrCode);
		add(btn_apply);
		add(lbl_head);
		add(lbl_desc);

        setSize(1000, 800);
        setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ApplyCode();
	}

}