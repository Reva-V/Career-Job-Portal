package home;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.PatternSyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class SeekerDetails extends JFrame {

    JTable table2;
    DefaultTableModel model2;
    String name = "";

    public SeekerDetails() {
    	
        setTitle("VIEW DETAILS");
        Image icon = Toolkit.getDefaultToolkit().getImage("images\\admin.png");
        setIconImage(icon);
        
        setLayout(new BorderLayout());
    	setContentPane(new JLabel(new ImageIcon("images\\seeker.jpg")));
        setLayout(null);

        JTabbedPane pane = new JTabbedPane();
        pane.setBounds(20, 100, 950, 500);

        model2 = new DefaultTableModel();
        table2 = new JTable(model2);
        table2.setPreferredScrollableViewportSize(new Dimension(800, 100));
        table2.setFillsViewportHeight(true);

        JScrollPane pane2 = new JScrollPane(table2);

        JLabel label1 = new JLabel("Search");
        label1.setBounds(25, 70, 70, 25);
        add(label1, BorderLayout.WEST);
        
        JLabel label2 = new JLabel("*Click on the mail-id which you want to delete");
        label2.setBounds(55, 620, 500, 25);
        add(label2, BorderLayout.SOUTH);

        JTextField filterText = new JTextField("");
        filterText.setBounds(100, 70, 200, 25);
        add(filterText, BorderLayout.CENTER);

        model2.addColumn("name");
        model2.addColumn("age");
        model2.addColumn("gender");
        model2.addColumn("percentage_10");
        model2.addColumn("percentage_12");
        model2.addColumn("degree");
        model2.addColumn("cgpa");
        model2.addColumn("address");
        model2.addColumn("E-Mail");
        model2.addColumn("mobile");
        model2.addColumn("password");
        model2.addColumn("company");
        model2.addColumn("position");

        final TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model2);
        table2.setRowSorter(sorter);

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "rethu", "rethu");
            con.setAutoCommit(true);
            Statement stmt = con.createStatement();
            String query = ("SELECT * FROM jobseekerreg");
            ResultSet rs;
            rs = stmt.executeQuery(query);
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    String str_name = (rs.getString("name"));
                    String str_age = (rs.getString("age"));
                    String str_gen = (rs.getString("gender"));
                    String str_percentage_10 = (rs.getString("percent_10"));
                    String str_percentage_12= (rs.getString("percent_12"));
                    String str_degree = (rs.getString("degree"));
                    String str_cgpa = (rs.getString("cgpa"));
                    String str_add = (rs.getString("address"));
                    String str_email = (rs.getString("email"));
                    String str_mbl = (rs.getString("phone"));
                    String str_pass = (rs.getString("password"));
                    String str_company = (rs.getString("companyname"));
                    String str_position = (rs.getString("position"));

                    //System.out.println("name"+name);
                    model2.addRow(new Object[]{str_name, str_age, str_gen, str_percentage_10, str_percentage_12, str_degree, str_cgpa, str_add, str_email, str_mbl, str_pass,str_company,str_position});
                }
            } else {
                JOptionPane.showMessageDialog(null, "User Not found!");
            }
        } catch (Exception ex1) {
            System.out.println(ex1);
        }
        table2.setFocusable(false);
        table2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 1) {     // to detect doble click events
                    JTable target = (JTable) me.getSource();
                    int row = target.getSelectedRow(); // select a row
                    int column = target.getSelectedColumn(); // select a column
                    JOptionPane.showMessageDialog(null, "Do you want to delete this row with value?"+table2.getValueAt(row, column)); // get the value of a row and column.
                    String dummy = table2.getValueAt(row, column).toString();
                    new SeekerDelete(dummy);
                }
                }
        });
        filterText.addKeyListener(new KeyAdapter() {
            // override keyReleased listener on the Email TextField
            @Override
            public void keyReleased(KeyEvent e) {
                name = filterText.getText();
                if (name.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    try {
                        sorter.setRowFilter(RowFilter.regexFilter(name));
                    } catch (PatternSyntaxException pse) {
                        System.out.println("Bad regex pattern");
                    }
                }
            }
        });
        JButton button = new JButton("Filter");
        button.setBounds(850, 70, 100, 25);
        add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                name = filterText.getText();
                if (name.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    try {
                        sorter.setRowFilter(RowFilter.regexFilter(name));
                    } catch (PatternSyntaxException pse) {
                        System.out.println("Bad regex pattern");
                    }
                }
            }
        });

        pane.addTab("Seeker details", pane2);

        getContentPane().add(pane);

        JLabel label = new JLabel("ADMIN PAGE", JLabel.CENTER);
        label.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
        add(label);
        label.setBounds(400, 40, 150, 20);
        
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SeekerDetails();
    }
}