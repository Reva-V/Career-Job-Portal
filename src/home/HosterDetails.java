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

public class HosterDetails extends JFrame {

    JTable table;
    DefaultTableModel model;
    String name = "";

    public HosterDetails() {
        setLayout(null);
        setTitle("VIEW DETAILS");
        
        setLayout(new BorderLayout());
    	setContentPane(new JLabel(new ImageIcon("images\\job.jpg")));
        setLayout(null);
        
        Image icon = Toolkit.getDefaultToolkit().getImage("images\\admin.png");
        setIconImage(icon);

        JTabbedPane pane = new JTabbedPane();
        pane.setBounds(20, 100, 950, 500);

        model = new DefaultTableModel();
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(800, 100));
        table.setFillsViewportHeight(true);

        JScrollPane pane1 = new JScrollPane(table);

        JLabel label1 = new JLabel("Search");
        label1.setBounds(25, 70, 70, 25);
        add(label1, BorderLayout.WEST);
        
        JLabel label2 = new JLabel("*Click on the mail-id which you want to delete");
        label2.setBounds(55, 620, 500, 25);
        add(label2, BorderLayout.SOUTH);

        JTextField filterText = new JTextField("");
        filterText.setBounds(100, 70, 200, 25);
        add(filterText, BorderLayout.CENTER);
        filterText.setToolTipText("Enter keywords to search");
        model.addColumn("companyname");
        model.addColumn("email");
        model.addColumn("position Description");
        model.addColumn("basicRequirement");
        model.addColumn("salary");
        model.addColumn("jobPeriod");
        model.addColumn("lastDate");
        model.addColumn("seats");

        final TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
        table.setRowSorter(sorter);

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "rethu", "rethu");
            con.setAutoCommit(true);
            Statement stmt = con.createStatement();
            //String query = "SELECT * FROM seeker_reg";
            String query2 = "SELECT * FROM jobdetails";
            ResultSet rs;

            rs = stmt.executeQuery(query2);
            if (rs.isBeforeFirst()) {
                while (rs.next()) {

                    String companyname = rs.getString("companyname");
                    String email = rs.getString("email");
                    String position = rs.getString("position");
                    String requirements = rs.getString("requirements");
                    String salary = rs.getString("salary");
                    String jobPeriod = rs.getString("jobperiod");
                    String lastDate = rs.getString("lastdateapply");
                    String seats = rs.getString("vacancy");
                    System.out.println(companyname);
                    System.out.println(email);
                    System.out.println(position);

                    model.addRow(new Object[]{companyname, email, position, requirements, salary, jobPeriod, lastDate, seats});
                }
            } else {
                JOptionPane.showMessageDialog(null, "User Not found!");
            }
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }

        table.setFocusable(false);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 1) {     // to detect doble click events
                    JTable target = (JTable) me.getSource();
                    int row = target.getSelectedRow(); // select a row
                    int column = target.getSelectedColumn(); // select a column
                    JOptionPane.showMessageDialog(null, "Do you want to delete this row?"); // get the value of a row and column.
                    String dummy = table.getValueAt(row, column).toString();
                    new HosterDelete(dummy);
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

        JButton button = new JButton("LOGOUT");
        button.setBounds(850, 70, 100, 25);
        add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Home();
            }
        });
        pane.addTab("Hoster details", pane1);

        getContentPane().add(pane);
        setSize(1000, 700);
        //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("ADMIN PAGE", JLabel.CENTER);
        label.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
        add(label);
        label.setBounds(400, 40, 150, 20);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new HosterDetails();
    }
}