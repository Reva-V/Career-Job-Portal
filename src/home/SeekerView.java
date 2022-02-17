package home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SeekerView extends JFrame{
	JLabel comp,pos,req,jobperiod,salary,seats,date,check,lbl_des;
	JTextField compt,post,reqt,jobperiodt,salaryt,seatst,datet,code;
	JButton btn;
	String refid="",id;
	SeekerView(){
		
		JFrame frame = new JFrame();

        final int FRAME_WIDTH  = 1000;
        final int FRAME_HEIGHT = 1000;

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("JOBS AVAILABLE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAutoscrolls(true);
        frame.add(panel,BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 30, 900, 800);

        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(1000, 900));
        contentPane.add(scrollPane);
        
        JPanel sp1[]= new JPanel[5];
        int i=0;
        //for(int i = 0; i < 5; i++) {
        	
        try
        {
       Class.forName("oracle.jdbc.driver.OracleDriver");
       Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","rethu","rethu");
       con.setAutoCommit(true);
       Statement stmt=con.createStatement();
       String query="SELECT * FROM jobdetails";
    //String query1="SELECT position FROM jobdetails";
       ResultSet rs;

       rs = stmt.executeQuery(query); 

       if(rs.isBeforeFirst()){
           while (rs.next()){
        	   
            sp1[i] = new JPanel();
            sp1[i].setLayout(new FlowLayout());
            sp1[i].setBackground(new Color(246, 255, 150));
            sp1[i].setPreferredSize(new Dimension(800, 180));

            JPanel ssp1 = new JPanel();
            ssp1.setLayout(new FlowLayout());
            ssp1.setBackground(new Color(177, 252, 242));
            ssp1.setPreferredSize(new Dimension(500, 170));

            JPanel ssp2 = new JPanel();
            ssp2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));
            ssp2.setBackground(new Color(186, 247, 189));
            ssp2.setPreferredSize(new Dimension(350, 170));
                        

            comp = new JLabel("Company-Name:");
            comp.setForeground(Color.BLACK);
            comp.setPreferredSize(new Dimension(120, 20));
            compt = new JTextField(rs.getString("companyname"));
            compt.setPreferredSize(new Dimension(320, 20));
            compt.setEditable(false);

            pos = new JLabel("Position: ");
            pos.setForeground(Color.BLACK);
            pos.setPreferredSize(new Dimension(120, 20));
            post = new JTextField(rs.getString("position"));
            post.setPreferredSize(new Dimension(320, 20));
            post.setEditable(false);

            req = new JLabel("Basic-requirements: ");
            req.setForeground(Color.BLACK);
            req.setPreferredSize(new Dimension(120, 20));
            reqt = new JTextField(rs.getString("requirements"));
            reqt.setPreferredSize(new Dimension(320, 20));
            reqt.setEditable(false);

            jobperiod = new JLabel("Job period: ");
            jobperiod.setForeground(Color.BLACK);
            jobperiod.setPreferredSize(new Dimension(120, 20));
            jobperiodt = new JTextField(rs.getString("jobPeriod"));
            jobperiodt.setPreferredSize(new Dimension(320, 20));
            jobperiodt.setEditable(false);

            salary = new JLabel("Salary: ");
            salary.setForeground(Color.BLACK);
            salary.setPreferredSize(new Dimension(120, 20));
            salaryt = new JTextField(rs.getString("salary"));
            salaryt.setPreferredSize(new Dimension(320, 20));
            salaryt.setEditable(false);

            seats = new JLabel("No of seats: ");
            seats.setForeground(Color.BLACK);
            seats.setPreferredSize(new Dimension(120, 20));
            seatst = new JTextField(rs.getString("vacancy"));
            seatst.setPreferredSize(new Dimension(320, 20));
            seatst.setEditable(false);
            
            
            date = new JLabel("Last date to apply for this: ");
            date.setForeground(Color.BLACK);
            date.setPreferredSize(new Dimension(150, 20));
            datet = new JTextField(rs.getString("lastdateapply"));
            datet.setPreferredSize(new Dimension(150, 20));
            datet.setEditable(false);
            
            lbl_des = new JLabel();
            lbl_des.setText("<html><p style=\" border:1px dashed red; text-align: center;\">&emsp To Register for this job,&emsp <br> &emsp Note the below id or copy for next process&emsp</p></html>");
            
            
            code = new JTextField(rs.getString("id"));  
            code.setEditable(false);
            code.setBounds(150,100, 50,50);  
            
            btn = new JButton("Apply");
            btn.setPreferredSize(new Dimension(150, 20));
            
            ssp1.add(comp);
            ssp1.add(compt);
            ssp1.add(pos);
            ssp1.add(post);
            ssp1.add(req);
            ssp1.add(reqt);
            ssp1.add(jobperiod);
            ssp1.add(jobperiodt);
            ssp1.add(salary);
            ssp1.add(salaryt);
            ssp1.add(seats);
            ssp1.add(seatst);
            
            ssp2.add(date);
            ssp2.add(datet);            
            //ssp2.add(check);
            ssp2.add(lbl_des);
            ssp2.add(code);
            ssp2.add(btn);

            sp1[i].add(ssp1);
            sp1[i].add(ssp2);
            panel.add(sp1[i]); 
            i++;
           }
           
       } 
       else 
       {
          
       }
        
        }
        catch(Exception ex)
        {
            
        }
        
        btn.addActionListener(new ActionListener() {				// event handling
			@Override
			public void actionPerformed(ActionEvent e) {
				new ApplyCode();
				//new SeekerLogin("","");
			}
		});

        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);
        frame.add(contentPane, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        
        
	}

    public static void main(String[] args)
    {
        new SeekerView();
    }
}
