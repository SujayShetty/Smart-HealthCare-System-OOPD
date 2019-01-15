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
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DoctorProfile {

	private JFrame frame;
    private JTextField nameField;
    private JPasswordField passwordField;
    static Connection con;
    static Statement stmt;
    private JTextField emailField;
    private JTextField catField;
    private JTextField qualificationField;
    JTextField positionField;
    ButtonGroup group;
    String docID =null;
    private JButton btnBack;
    private JLabel lblUserid;

	/**
	 * Launch the application.
	 */
			public void runDocUpdate(String docID) {
				try {
					DoctorProfile window = new DoctorProfile(docID);
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
	public DoctorProfile(String docID) {
		initialize(docID);
	}

	public DoctorProfile() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String docID) {
		LoggerClass lc = new LoggerClass();
    	Logger logger = lc.loggerMethod();
		ResultSet rs=null;
		String userIDQuery = "SELECT * from doctor where docID =" + "'" + docID + "'";	     
        try {
        	DataBase dbConnection = new DataBase();
            con = dbConnection.getConnection();
            stmt = con.createStatement();
			rs = stmt.executeQuery(userIDQuery);	
			if(rs.next() == false)
			{JOptionPane.showMessageDialog(null, "No Profile available");
    		logger.info("No History Found");
    		}
			String pass = rs.getString(2);
//			
		frame = new JFrame();
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

        JLabel lblNewLabel_2 = new JLabel("Password");
        lblNewLabel_2.setBounds(30, 70, 68, 14);
        frame.getContentPane().add(lblNewLabel_2);
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			String updateQuery1 = "UPDATE `doctor` SET `name`='"+
	                		nameField.getText() +"' WHERE docId ='"+
	                docID+"'";
        			int rs1 = stmt.executeUpdate(updateQuery1);
        			String updateQuery2 = "UPDATE `doctor` SET `category`='"+
        	                		catField.getText() +"' WHERE docId ='"+
        	                docID+"'";
                int rs2 = stmt.executeUpdate(updateQuery2);
                String updateQuery3 = "UPDATE `doctor` SET `position`='"+
                		positionField.getText() +"' WHERE docId ='"+
                docID+"'";
    			int rs3 = stmt.executeUpdate(updateQuery3);
    			String updateQuery4 = "UPDATE `doctor` SET `email`='"+
    	                		emailField.getText() +"' WHERE docId ='"+
    	                docID+"'";
            int rs4 = stmt.executeUpdate(updateQuery4);
            String updateQuery5 = "UPDATE `doctor` SET `qualification`='"+
            		qualificationField.getText() +"' WHERE docId ='"+
            docID+"'";
			int rs5 = stmt.executeUpdate(updateQuery5);
			
                System.out.println("Updated!!!!");
                DoctorViewProfile pForm = new DoctorViewProfile();
                pForm.runDocView(docID);
                frame.dispose();
                con.close();
        		} catch (Exception e1) {
                    e1.printStackTrace();
            }
        	}
        });
        btnUpdate.setBounds(236, 347, 89, 23);
        frame.getContentPane().add(btnUpdate);

        JLabel lblDoctorProfileUpdate = new JLabel("Doctor Profile Update");
        lblDoctorProfileUpdate.setBounds(87, 11, 135, 14);
        frame.getContentPane().add(lblDoctorProfileUpdate);

        passwordField = new JPasswordField();
        passwordField.setBounds(167, 67, 86, 20);
        frame.getContentPane().add(passwordField);

        JLabel lblAddress = new JLabel("Position");
        lblAddress.setBounds(30, 132, 68, 14);
        frame.getContentPane().add(lblAddress);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(30, 175, 68, 14);
        frame.getContentPane().add(lblEmail);

        emailField = new JTextField();
        emailField.setBounds(167, 172, 135, 20);
        frame.getContentPane().add(emailField);
        emailField.setColumns(10);

        JLabel lblCat = new JLabel("Category");
        lblCat.setBounds(30, 101, 68, 14);
        frame.getContentPane().add(lblCat);

        catField = new JTextField();
        catField.setBounds(167, 98, 86, 20);
        frame.getContentPane().add(catField);
        catField.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Qualification");
        lblNewLabel_3.setBounds(30, 206, 108, 14);
        frame.getContentPane().add(lblNewLabel_3);

        qualificationField = new JTextField();
        qualificationField.setBounds(167, 203, 135, 20);
        frame.getContentPane().add(qualificationField);
        qualificationField.setColumns(10);

        positionField = new JTextField();
        positionField.setBounds(167, 127, 86, 20);
        frame.getContentPane().add(positionField);
        
        btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		DoctorViewProfile hForm = new DoctorViewProfile();
                hForm.runDocView(docID);
                frame.dispose();
        	}
        });
        btnBack.setBounds(63, 347, 89, 23);
        frame.getContentPane().add(btnBack);
        
        lblUserid = new JLabel("userID");
        lblUserid.setBounds(274, 11, 85, 14);
        frame.getContentPane().add(lblUserid);
        lblUserid.setText(docID);
        nameField.setText(rs.getString(1));
		passwordField.setText(pass);
		catField.setText(rs.getString(3));
		positionField.setText(rs.getString(4));
		emailField.setText(rs.getString(6));
		qualificationField.setText(rs.getString(7));
    } catch (SQLException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	}

}

