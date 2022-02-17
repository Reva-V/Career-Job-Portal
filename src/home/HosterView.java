package home;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.*;
import javax.swing.*;
import javax.swing.table.*;

public class HosterView extends JFrame {

    JTable table;
    DefaultTableModel model;
    String name = "";
    static String mail;

    public HosterView(String mail1) {
        setLayout(null);

        mail = mail1;
        //System.out.println("3 " + mail);
        setTitle("Hoster view");

        setLayout(new BorderLayout());
    	setContentPane(new JLabel(new ImageIcon("images\\job.jpg")));
        setLayout(null);
        
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

        JTextField filterText = new JTextField("");
        filterText.setBounds(100, 70, 200, 25);
        add(filterText, BorderLayout.CENTER);

        model.addColumn("name");
        model.addColumn("age");
        model.addColumn("gender");
        model.addColumn("qualify");
        model.addColumn("qualify1");
        model.addColumn("qualify2");
        model.addColumn("gpa");
        model.addColumn("add");
        model.addColumn("id");
        model.addColumn("mbl");
        model.addColumn("pass");

        final TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
        table.setRowSorter(sorter);

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "rethu", "rethu");
            con.setAutoCommit(true);
            //Statement stmt=con.createStatement();
            String query = "select s.name,s.age,s.gender,s.percent_10,s.percent_12,s.degree,s.cgpa,s.address,s.email,s.phone,s.password,s.companyname,s.position from jobseekerreg s , jobhosterreg h where h.companyname=s.companyname and h.email=?";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, mail);
            ResultSet rs = pstmt.executeQuery();
            //System.out.println("rs "+rs);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    String str_name = (rs.getString(1));
                    String str_age = (rs.getString(2));
                    String str_gen = (rs.getString(3));
                    String str_qualify = (rs.getString(4));
                    String str_qualify1 = (rs.getString(5));
                    String str_qualify2 = (rs.getString(6));
                    String str_gpa = (rs.getString(7));
                    String str_add = (rs.getString(8));
                    String str_email = (rs.getString(9));
                    String str_mbl = (rs.getString(10));
                    String str_pass = (rs.getString(11));
                    String str_companyname = (rs.getString(12));
                    String str_position = (rs.getString(13));
                    model.addRow(new Object[]{str_name, str_age, str_gen, str_qualify, str_qualify1, str_qualify2, str_gpa, str_add, str_email, str_mbl, str_pass, str_companyname, str_position});
                }
            } else {
                JOptionPane.showMessageDialog(null, "User Not found!");
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        table.setFocusable(false);
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
        JButton button = new JButton("Log-Out");
        button.setBounds(850, 70, 100, 25);
        add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Home();
            }
        });
        pane.addTab("Hoster View", pane1);

        getContentPane().add(pane);
        setSize(1000, 700);
        filterText.setToolTipText("Enter keywords to search");
        JLabel label = new JLabel("HOSTER PAGE", JLabel.CENTER);
        label.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
        add(label);
        label.setBounds(320, 40, 350, 20);
        
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String args[]) {
        new HosterView(mail);
    }

}