package home;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AdminPage extends JFrame implements ActionListener{
    JLabel lbl_head;
  
    JButton jbtn_hoster,jbtn_seeker;

    Container cp = getContentPane();
    
    public AdminPage(){
        setLayout(null);
        setTitle("ADMIN HOME");
        Image icon = Toolkit.getDefaultToolkit().getImage("images\\admin.png");
        setIconImage(icon);
        
        setLayout(new BorderLayout());
    	setContentPane(new JLabel(new ImageIcon("images\\help.jpg")));
        setLayout(null);
        
         lbl_head = new JLabel("VIEW DETAILS");
        lbl_head.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
        
        jbtn_hoster = new JButton("HOSTER");
        jbtn_seeker = new JButton("SEEKER");
        
        lbl_head.setBounds(410, 100, 200, 50);
        jbtn_hoster.setBounds(350, 320, 100, 25);
        jbtn_seeker.setBounds(550, 320, 100, 25);
        
        add(lbl_head);
        add(jbtn_hoster);
        add(jbtn_seeker);
        
        jbtn_hoster.addActionListener(this);
        jbtn_seeker.addActionListener(this);
        
        jbtn_hoster.setToolTipText("View Hoster Detail");
        jbtn_seeker.setToolTipText("View Seeker Detail");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == jbtn_hoster) {
            try {
                new HosterDetails();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.toString());
            }
        }else if(src == jbtn_seeker){
                new SeekerDetails();
        }

    }public static void main(String args[]){
        new AdminPage();
    }
}
