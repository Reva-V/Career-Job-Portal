package home;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLogin extends JFrame implements ActionListener{
    JLabel lbl_head,lbl_uname,lbl_pass;
    JTextField txt_uname;
    JPasswordField txt_pass;
    JCheckBox pass;
    JButton jbtn_submit;
    Container cp = getContentPane();
    
    public AdminLogin(){
    	
        setLayout(null);
        setTitle("ADMIN LOGIN");
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\rethe\\eclipse-workspace\\CAREER_JOB_PORTAL\\images\\admin.png");
        setIconImage(icon);
        
        setLayout(new BorderLayout());
    	setContentPane(new JLabel(new ImageIcon("C:\\Users\\rethe\\eclipse-workspace\\CAREER_JOB_PORTAL\\images\\login.jpg")));
        setLayout(null);
        
        lbl_head=new JLabel("ADMIN LOGIN");
        lbl_head.setFont(new Font("Bookman Old Style",Font.BOLD,20));
        lbl_uname=new JLabel("Admin ID");
        lbl_uname.setFont(new Font("", Font.BOLD,14));
        lbl_pass=new JLabel("Password");
        lbl_pass.setFont(new Font("", Font.BOLD,14));
        
        txt_uname=new JTextField("krish28ski");
        txt_pass=new JPasswordField("Ks82por!");
        //txt_uname.setBackground(new Color(223, 247, 245,210));
        Border b = BorderFactory.createMatteBorder(0, 0, 2, 0,Color.BLACK);
        txt_uname.setOpaque(false);
        txt_uname.setBorder(b);
        txt_pass.setOpaque(false);
        txt_pass.setBorder(b);
        
        jbtn_submit=new JButton("LOGIN");
        jbtn_submit.setFont(new Font("", Font.BOLD,14));
        
        pass=new JCheckBox();
		pass.setIcon(new ImageIcon("images\\eyeclose.png"));
        pass.setSelectedIcon(new ImageIcon("images\\eyeopen.png"));
        pass.setOpaque(false);
		 
        txt_uname.setToolTipText("Enter Admin ID");
        txt_pass.setToolTipText("Enter Admin password");
        txt_uname.setToolTipText("Enter Admin ID:");
        txt_pass.setToolTipText("Enter Pass Code");
        
        lbl_head.setBounds(410, 140, 200, 50);
        lbl_uname.setBounds(370, 230, 120, 50);
        lbl_pass.setBounds(370, 280, 120, 50);
        
        txt_uname.setBounds(500, 240, 120, 25);
        txt_pass.setBounds(500, 290, 120, 25);
        pass.setBounds(650, 290, 40, 40);
        jbtn_submit.setBounds(440, 370, 80, 25);
        
        txt_uname.setToolTipText("Enter Admin ID:");
        txt_pass.setToolTipText("Enter Password");
        
        add(lbl_head);
        add(lbl_uname);
        add(lbl_pass);
        add(txt_uname);
        add(txt_pass);
        add(jbtn_submit);
        add(pass);

        pass.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox check = (JCheckBox) e.getSource();
	            txt_pass.setEchoChar(check.isSelected() ? '\u0000' : (Character) UIManager.get("PasswordField.echoChar"));
			}
		});
        
        jbtn_submit.addActionListener(this);
        
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        Object src = e.getSource();
        if(src == jbtn_submit){
            try{
                String str_uname = txt_uname.getText();
                String str_pass = txt_pass.getText();
                
            if(!str_uname.equals("krish28ski"))
            {
                JOptionPane.showMessageDialog(cp,"Please enter proper Admin ID","Error",JOptionPane.WARNING_MESSAGE);
            }
            else if(!str_pass.equals("Ks82por!")){
                JOptionPane.showMessageDialog(cp,"Please enter proper Pass Code","Error",JOptionPane.WARNING_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "Login Success!");
                new AdminPage();
            } 
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, ex.toString());
            }
        }
    }
    
    public static void main(String ss[]){
        new AdminLogin();
    }
}