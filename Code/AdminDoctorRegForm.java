import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;
import java.util.logging.Logger;
public class AdminDoctorRegForm {

    private JFrame frame;
    private JTextField nameField;
    private JTextField textField_2;
    private JLabel lblDepartment;
    private JTextField textField_3;
    private JLabel lblSuccessfulOperations;
    private JTextField emailField;
    private JLabel lblQualification;
    private JTextField qualificationField;
    private JLabel lblPassword;
    private JTextField passwordField;
    private JLabel lblPosition;
    private JButton btnBack;
    static Connection con;
    static Statement stmt;
    String docID =null;
    private JTextField timeTo;
    private JTextField textField_1;
    private JLabel lblTimefrom;
    private JLabel lblTimeto;
	private JTextField timeFrom;
    /**
     * Launch the application.
     */
    public void runDocRegForm() {
        try {
            AdminDoctorRegForm window = new AdminDoctorRegForm();
            window.frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the application.
     */
    public AdminDoctorRegForm() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
    	DataBase dbConnection = new DataBase();
        con = dbConnection.getConnection();
    	LoggerClass lc = new LoggerClass();
    	Logger logger = lc.loggerMethod();
        frame = new JFrame();
        frame.setBounds(100, 100, 550, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setBounds(66, 20, 70, 15);
        frame.getContentPane().add(lblNewLabel);

        nameField = new JTextField();
        nameField.setBounds(180, 20, 120, 19);
        frame.getContentPane().add(nameField);
        nameField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Position");
        lblNewLabel_1.setBounds(65, 100, 120, 15);
        frame.getContentPane().add(lblNewLabel_1);

        String[] ch2 = {
            "Nil",
            "Surgeon",
            "Senior Surgeon"
        };
        JComboBox < String > positionCombobox = new JComboBox(ch2);
        positionCombobox.setBounds(180, 100, 120, 24);
        frame.getContentPane().add(positionCombobox);


        lblDepartment = new JLabel("Department");
        lblDepartment.setBounds(65, 140, 120, 15);
        frame.getContentPane().add(lblDepartment);

//        String[] ch1 = {
//            "1",
//            "2",
//            "3",
//            "4"
//        };
        JComboBox < String > depComboBox = new JComboBox();
        depComboBox.setBounds(180, 140, 120, 24);
        frame.getContentPane().add(depComboBox);

        try {
			stmt = con.createStatement();
        String allDep = "SELECT * from department";
    	ResultSet rs = stmt.executeQuery(allDep);
    	while(rs.next()) {
    		System.out.println("inside");
    		System.out.println(rs.getString(1));
    		depComboBox.addItem(rs.getString(1));
    	}
    	rs.close();
        } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        lblSuccessfulOperations = new JLabel("Email");
        lblSuccessfulOperations.setBounds(65, 180, 120, 15);
        frame.getContentPane().add(lblSuccessfulOperations);

        emailField = new JTextField();
        emailField.setBounds(180, 180, 120, 19);
        frame.getContentPane().add(emailField);
        emailField.setColumns(10);

        lblQualification = new JLabel("Qualification");
        lblQualification.setBounds(65, 220, 120, 15);
        frame.getContentPane().add(lblQualification);

        qualificationField = new JTextField();
        qualificationField.setBounds(180, 220, 120, 19);
        frame.getContentPane().add(qualificationField);
        qualificationField.setColumns(10);



        lblPassword = new JLabel("Password");
        lblPassword.setBounds(65, 260, 120, 15);
        frame.getContentPane().add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 260, 120, 19);
        frame.getContentPane().add(passwordField);
        passwordField.setColumns(10);
        
        lblPosition = new JLabel("Category");
        lblPosition.setBounds(66, 60, 70, 15);
        frame.getContentPane().add(lblPosition);

        String[] ch = {
            "Junior Resident",
            "Senior Resident",
            "Specialist",
            "Senior Specialist"
        };
        JComboBox < String > categoryComboBox = new JComboBox(ch);
        categoryComboBox.setBounds(180, 60, 120, 24);
        frame.getContentPane().add(categoryComboBox);
        

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	boolean f= true;
            	if(nameField.getText().isEmpty())
            		{JOptionPane.showMessageDialog(null, "Please enter name");
            		logger.info("Name was't entered");}
            	else if(emailField.getText().isEmpty())
            		{JOptionPane.showMessageDialog(null, "Please enter email");
            		logger.info("Email wasn't entered");}
            	else if(qualificationField.getText().isEmpty())
            		{JOptionPane.showMessageDialog(null, "Please enter qualification");
            		logger.info("Qualification was't entered");
            		}
            	else if(passwordField.getText().isEmpty())
            	{
            		JOptionPane.showMessageDialog(null, "Please enter password");
            		logger.info("Password was't entered");
            	}
            	else { 
                    
                    //To upload to patient table
                    String un = nameField.getText().toString();
                    System.out.println(un);
                    /*	doctor TABLE STRUCTURE
                     * 	-> name varchar(30),
					    -> category varchar(30),
					    -> position varchar(30),
					    -> department varchar(20),
					    -> email varchar(50),
					    -> qualification varchar(20),
					    -> password varchar(20));
                     */
                    try {
                    	
                        stmt = con.createStatement();
                        Random rnd = new Random();
                        int n = 100000 + rnd.nextInt(900000);
                        docID = "doc_"+depComboBox.getSelectedItem().toString()+"_"+n;
                        String sql = "INSERT INTO doctor " +
                        		"VALUES ('" + nameField.getText().toString()+ "','" + docID +" ', '" +categoryComboBox.getSelectedItem()+ "','" +positionCombobox.getSelectedItem()+
                        					 "', '" +depComboBox.getSelectedItem()+ "', '" +emailField.getText()+ "', '" +qualificationField.getText()+ "', '" +passwordField.getText()+"','"+
                        					 timeFrom.getText()+"','"+timeTo.getText()+"')";
                        stmt.executeUpdate(sql);
                        con.close();
                    } catch (SQLException ex) {
                        // TODO Auto-generated catch block
                    	logger.info(ex.getMessage());
                       // ex.printStackTrace();
                        timeFrom.setText("");
                        timeTo.setText("");
                        JOptionPane.showMessageDialog(null, "Please enter time in hh:mm:ss format");
                        f= false;
                    }
                    if(f)
                    	JOptionPane.showMessageDialog(null, "Success! Created docID is " + docID);
                    //Clear all the fields
                    
                    System.out.println("Inserted records into the table...");
                    
                }
            }
        });
        btnSubmit.setBounds(249, 405, 117, 25);
        frame.getContentPane().add(btnSubmit);

        
        btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AdminLogged hForm = new AdminLogged();
                hForm.runAdminLogged();
                frame.dispose();
        	}
        });
        btnBack.setBounds(52, 405, 89, 24);
        frame.getContentPane().add(btnBack);
        
        timeTo = new JTextField();
        timeTo.setBounds(180, 357, 114, 19);
        frame.getContentPane().add(timeTo);
        timeTo.setColumns(10);
        
        timeFrom = new JTextField();
        timeFrom.setBounds(180, 305, 114, 19);
        frame.getContentPane().add(timeFrom);
        timeFrom.setColumns(10);
        
        
        lblTimefrom = new JLabel("<html>Time From<br> hh:mm:ss</html>");
        lblTimefrom.setBounds(44, 310, 126, 24);
        frame.getContentPane().add(lblTimefrom);
        
        lblTimeto = new JLabel("<html>Time To<br> hh:mm:ss</html>");
        lblTimeto.setBounds(44, 357, 126, 34);
        frame.getContentPane().add(lblTimeto);


    }
}