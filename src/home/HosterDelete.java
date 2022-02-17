package home;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class HosterDelete {
        public HosterDelete(String email){
            System.out.println(email);
    try{
            System.out.println(email);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","rethu","rethu");
            con.setAutoCommit(true);
            Statement stmt=con.createStatement();
            String query = "DELETE * FROM hosterlogin WHERE email='"+email+"'";
            stmt.executeQuery(query);
            String query1 ="DELETE jobdetails WHERE email='"+email+"'";
            stmt.executeQuery(query1);
            String query2 ="DELETE jobhosterreg WHERE email='"+email+"'";
            stmt.executeQuery(query2);
            
            
            JOptionPane.showMessageDialog(null, "Deleted successfully");
            System.exit(0);
        } 
           catch(Exception ex){
               System.out.println(ex);
               ex.printStackTrace();
        }
        }
           public static void main(String args[])
           {
               
           }
}