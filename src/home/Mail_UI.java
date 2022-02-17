package home;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mail_UI extends JFrame {
    JLabel lbl_head,lbl_des;
    JTextField txt_code;
    JButton jbtn_submit;    
    Container cp = getContentPane();
    static String code1;
    
    public Mail_UI(String code,String email){
        code1 = code;
        setLayout(null);
        setTitle("MAIL VERIFICATION");
        
        setLayout(new BorderLayout());
    	setContentPane(new JLabel(new ImageIcon("images\\help.jpg")));
        setLayout(null);
        
        lbl_head = new JLabel("E-MAIL VERIFICATION");
        lbl_head.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
        lbl_des = new JLabel("Please enter the code sent to the mail id you entered to verify your account!!");
        lbl_des.setFont(new Font("", Font.BOLD,14));
        
        txt_code= new JTextField();
        
        jbtn_submit = new JButton("VERIFY");
        jbtn_submit.setFont(new Font("", Font.BOLD,14));
        
        lbl_head.setBounds(360, 100, 400, 50);
        lbl_des.setBounds(200, 170, 550, 50);
        txt_code.setBounds(350, 245, 210, 25);
        jbtn_submit.setBounds(400, 320, 100, 25);
        
        add(lbl_head);
        add(lbl_des);
        add(txt_code);
        add(jbtn_submit);
        
        jbtn_submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try{
                    String str_code = txt_code.getText();
                                    
                if(!str_code.equals(code1))
                {
                    JOptionPane.showMessageDialog(cp,"Please enter proper code!","Error",JOptionPane.WARNING_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(new JFrame(), "Account Verified!");
                    UtilityClass.mailToSeeker(email);
                    new Home();
                    
                } 
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(new JFrame(), ex.toString());
                }
            }
        });
        
        setVisible(true);
        setSize(1000, 800);
        setLocationRelativeTo(null);
    }
        
    public static void main(String args[]) {
    	new Mail_UI("","");
    }
}
