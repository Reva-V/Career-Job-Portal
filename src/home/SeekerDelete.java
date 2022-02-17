package home;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class SeekerDelete {
        public SeekerDelete(String email){
        	int flag=0;
        	
    try{
            System.out.println(email);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","rethu","rethu");
            con.setAutoCommit(true);
            Statement stmt=con.createStatement();
            String query = "DELETE jobseekerlogin WHERE email='"+email+"'";
            stmt.executeQuery(query);
            String query1 ="DELETE jobseekerreg WHERE email='"+email+"'";
            stmt.executeQuery(query1);
            flag=1;
            System.exit(0);
        } 
           catch(Exception ex){
               System.out.println(ex);
               ex.printStackTrace();
            //JOptionPane.showMessageDialog(null, ex.toString());
        }
	    if(flag==1) {
	    	JOptionPane.showMessageDialog(null, "Deleted successfully");
	    }
	    
        }
           public static void main(String args[])
           {
               
           }
}