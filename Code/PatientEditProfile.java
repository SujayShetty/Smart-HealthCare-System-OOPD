import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.SystemColor;

public class PatientEditProfile {

	private JFrame frame;
    private JTextField nameField;
    private JPasswordField passwordField;
    static Connection con;
    static Statement stmt;
    private JTextField emailField;
    private JTextField ageField;
    private JTextField phonenumberField;
    JTextArea addresstextArea;
    JComboBox depComboBox;
    ButtonGroup group;
    String patID =null;
    private JButton btnBack;
    private JLabel lblUserid;
    private JLabel lblDepartment;

	/**
	 * Launch the application.
	 */
			public void runPatUpdate(String patID) {
				try {
					PatientEditProfile window = new PatientEditProfile(patID);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	/**
	 * Create the application.
	 */
			  /**
		     * @wbp.parser.entryPoint
		     */
	public PatientEditProfile(String patID) {
		initialize(patID);
	}

	public PatientEditProfile() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String patID) {
		LoggerClass lc = new LoggerClass();
    	Logger logger = lc.loggerMethod();
		ResultSet rs=null;
		String userIDQuery = "SELECT * from patient where patID =" + "'" + patID + "'";	     
        try {
        	DataBase dbConnection = new DataBase();
            con = dbConnection.getConnection();
            stmt = con.createStatement();
			rs = stmt.executeQuery(userIDQuery);	
			rs.next();
			String pass = rs.getString(2);
//			
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaption);
        frame.setBounds(100, 100, 450, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
		nameField = new JTextField();
        nameField.setBounds(167, 36, 86, 20);
        frame.getContentPane().add(nameField);
        nameField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Name");
        lblNewLabel_1.setBounds(30, 39, 68, 14);
        frame.getContentPane().add(lblNewLabel_1);
        
        /*String[] ch1 = {
                "1",
                "2",
                "3",
                "4"
            };*/
        depComboBox = new JComboBox();
        depComboBox.setBounds(167, 234, 129, 20);
        frame.getContentPane().add(depComboBox);
        
        try {
			stmt = con.createStatement();
        String allDep = "SELECT * from department";
    	ResultSet rs1 = stmt.executeQuery(allDep);
    	while(rs1.next()) {
    		System.out.println("inside");
    		System.out.println(rs1.getString(1));
    		depComboBox.addItem(rs1.getString(1));
    	}
    	rs1.close();
        } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        

        JLabel lblNewLabel_2 = new JLabel("Password");
        lblNewLabel_2.setBounds(30, 70, 68, 14);
        frame.getContentPane().add(lblNewLabel_2);
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {        		
                String updateQuery = "UPDATE `patient` SET `name`='"+
                nameField.getText()+"',`age`="+
                		Integer.parseInt(ageField.getText())+",`address`='"+
                addresstextArea.getText()+"',`email`='"+
                		emailField.getText()+"',`phoneNo`='"+
                phonenumberField.getText()+"',department ='"+
                		depComboBox.getSelectedItem()+"' WHERE patID ='"+
                patID+"'";
                int rs = stmt.executeUpdate(updateQuery);
                System.out.println("Updated!!!!");
                PatientViewProfile pForm = new PatientViewProfile();
                pForm.runPatView(patID);
                frame.dispose();
                con.close();
        		} catch (Exception e1) {
                    e1.printStackTrace();
            }
        	}
        });
        btnUpdate.setBounds(233, 293, 89, 23);
        frame.getContentPane().add(btnUpdate);

        JLabel lblPatientProfileUpdate = new JLabel("Patient Profile Update");
        lblPatientProfileUpdate.setBounds(87, 11, 135, 14);
        frame.getContentPane().add(lblPatientProfileUpdate);

        passwordField = new JPasswordField();
        passwordField.setBounds(167, 67, 86, 20);
        frame.getContentPane().add(passwordField);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(30, 132, 68, 14);
        frame.getContentPane().add(lblAddress);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(30, 175, 68, 14);
        frame.getContentPane().add(lblEmail);

        emailField = new JTextField();
        emailField.setBounds(167, 172, 135, 20);
        frame.getContentPane().add(emailField);
        emailField.setColumns(10);

        JLabel lblAge = new JLabel("Age");
        lblAge.setBounds(30, 101, 68, 14);
        frame.getContentPane().add(lblAge);

        ageField = new JTextField();
        ageField.setBounds(167, 98, 86, 20);
        frame.getContentPane().add(ageField);
        ageField.setColumns(10);

        String[] values = {
            "null",
            "Yes",
            "No"
        };

        JLabel lblNewLabel_3 = new JLabel("Phone Number");
        lblNewLabel_3.setBounds(30, 206, 108, 14);
        frame.getContentPane().add(lblNewLabel_3);

        phonenumberField = new JTextField();
        phonenumberField.setBounds(167, 203, 135, 20);
        frame.getContentPane().add(phonenumberField);
        phonenumberField.setColumns(10);

        addresstextArea = new JTextArea();
        addresstextArea.setBounds(167, 127, 192, 34);
        frame.getContentPane().add(addresstextArea);
        
        group = new ButtonGroup();
        
        btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		PatLogged hForm = new PatLogged();
                hForm.runPatLogged(patID);
                frame.dispose();
        	}
        });
        btnBack.setBounds(49, 293, 89, 23);
        frame.getContentPane().add(btnBack);
        
        lblUserid = new JLabel("userID");
        lblUserid.setBounds(274, 11, 85, 14);
        frame.getContentPane().add(lblUserid);
        lblUserid.setText(patID);
        nameField.setText(rs.getString(3));
		passwordField.setText(pass);
		ageField.setText(rs.getString(4));
		addresstextArea.setText(rs.getString(5));
		emailField.setText(rs.getString(6));
		phonenumberField.setText(rs.getString(7));
		System.out.println(rs.getString(10));
		depComboBox.setSelectedItem(rs.getString(9));
		
		lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(30, 237, 79, 14);
		frame.getContentPane().add(lblDepartment);
		
		
    } catch (SQLException e2) {
		// TODO Auto-generated catch block
    	logger.info(e2.getMessage());
		e2.printStackTrace();
	}
	}

}

