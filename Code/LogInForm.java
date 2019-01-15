import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import javax.naming.ldap.Rdn;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.Color;

public class LogInForm {

    private JFrame frame;
    private JTextField userIDField;
    private JPasswordField passwordField;
    JRadioButton rdbtnPatient;
    JRadioButton rdbtnDoctor;
    static Connection con;
    static Statement stmt;
    JLabel lblLoginfailure;

    /**
     * Launch the application.
     */
    public void runLogin() {
        try {
            LogInForm window = new LogInForm();
            window.frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Create the application.
     */
    public LogInForm() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(0, 0, 0, 300);
        lblNewLabel.setEnabled(false);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("User ID");
        lblNewLabel_1.setBounds(70, 43, 70, 20);
        frame.getContentPane().add(lblNewLabel_1);

        userIDField = new JTextField();
        userIDField.setBounds(200, 41, 122, 20);
        frame.getContentPane().add(userIDField);
        userIDField.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Password");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel_2.setBounds(70, 89, 84, 20);
        frame.getContentPane().add(lblNewLabel_2);

        passwordField = new JPasswordField();
        passwordField.setBounds(200, 86, 122, 20);
        frame.getContentPane().add(passwordField);

        JButton btnNewButton = new JButton("Back");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomePage hForm = new HomePage();
                hForm.runHomePage();
                frame.dispose();
            }
        });
        btnNewButton.setBounds(70, 206, 90, 20);
        frame.getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Login");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (userIDField.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "Please enter username");
                else if (passwordField.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "Please enter password");
                else {
                    //Admin Login via username:admin password : admin
                    if (userIDField.getText().equals("admin") && passwordField.getText().equals("admin")) {
                        AdminLogged aForm = new AdminLogged();
                        aForm.runAdminLogged();
                        frame.dispose();
                    } else {
                        //Open DB connection for doctor/patient login
                        try {
                            DataBase dbConnection = new DataBase();
                            con = dbConnection.getConnection();
                            stmt = con.createStatement();

                            //Doctor login
                            if (rdbtnDoctor.isSelected()) {
                                String userIDQuery = "SELECT * from doctor where docId =" + "'" + userIDField.getText() + "'";
                                String passwordQuery = "SELECT * from doctor where docId =" + "'" + userIDField.getText() + "'" + " and password=" + "'" + passwordField.getText() + "'";
                                ResultSet rs;
                                rs = stmt.executeQuery(userIDQuery);
                                if (!rs.next()) {
                                    lblLoginfailure.setVisible(true);
                                    lblLoginfailure.setText("No user exist");
                                    //continue;
                                    //System.out.println("No user exist");
                                } else {
                                    System.out.println("UserID is " + rs.getString(1));
                                    rs = stmt.executeQuery(passwordQuery);
                                    if (!rs.next()) {
                                        lblLoginfailure.setVisible(true);
                                        lblLoginfailure.setText("Password doesnt match with the user");
                                    } else {

                                        System.out.println("Password is " + rs.getString(1));
                                        DocLogged dForm = new DocLogged();
                                        dForm.runDocLogged(userIDField.getText());
                                        frame.dispose();
                                        con.close();
                                    }
                                }
                            }
                            //Patient Login
                            else if (rdbtnPatient.isSelected()) {

                                String userIDQuery = "SELECT * from patient where patID =" + "'" + userIDField.getText() + "'";
                                String passwordQuery = "SELECT * from patient where patID =" + "'" + userIDField.getText() + "'" + " and password=" + "'" + passwordField.getText() + "'";
                                ResultSet rs;
                                rs = stmt.executeQuery(userIDQuery);
                                if (!rs.next()) {
                                    lblLoginfailure.setVisible(true);
                                    lblLoginfailure.setText("No user exist");
                                    //continue;
                                    //System.out.println("No user exist");
                                } else {
                                    System.out.println("UserID is " + rs.getString(1));
                                    rs = stmt.executeQuery(passwordQuery);
                                    if (!rs.next()) {
                                        lblLoginfailure.setVisible(true);
                                        lblLoginfailure.setText("Password doesnt match with the user");
                                    } else {

                                        //System.out.println("Password is " + rs.getString(1));
                                        PatLogged pForm = new PatLogged();
                                        pForm.runPatLogged(userIDField.getText());
                                        frame.dispose();
                                        con.close();
                                    }
                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "Please select either Doctor or Patient");
                            }
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }

                }

            }
        });
        btnNewButton_1.setBounds(214, 206, 90, 20);
        frame.getContentPane().add(btnNewButton_1);

        rdbtnDoctor = new JRadioButton("Doctor");
        rdbtnDoctor.setBounds(70, 13, 109, 23);
        frame.getContentPane().add(rdbtnDoctor);

        rdbtnPatient = new JRadioButton("Patient");
        rdbtnPatient.setBounds(213, 11, 109, 23);
        frame.getContentPane().add(rdbtnPatient);

        ButtonGroup group = new ButtonGroup();
        group.add(rdbtnDoctor);
        group.add(rdbtnPatient);

        lblLoginfailure = new JLabel("loginFailure");
        lblLoginfailure.setForeground(Color.RED);
        lblLoginfailure.setVisible(false);
        lblLoginfailure.setBounds(87, 141, 217, 14);
        frame.getContentPane().add(lblLoginfailure);
    }
}