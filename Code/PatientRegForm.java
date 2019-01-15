import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import com.mysql.cj.xdevapi.DatabaseObjectDescription;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import java.awt.SystemColor;

public class PatientRegForm {

    private JFrame frame;
    private JTextField nameField;
    private JPasswordField passwordField;
    static Connection con;
    static Statement stmt;
    private JTextField emailField;
    private JTextField ageField;
    private JTextField phonenumberField;
    JTextArea addresstextArea;
    JComboBox criticalcomboBox;
    JRadioButton rdbtnLocal;
    JRadioButton rdbtnOpd;
    JComboBox depComboBox;
    ButtonGroup group;
    String patID =null;
    private JButton btnBack;
    /**
     * Launch the application.
     */
    public void runPatientRegForm(int test) {
        
            PatientRegForm window = new PatientRegForm(test);
            window.frame.setVisible(true);
    }

    /**
     * Create the application.
     */
    /**
	 * @wbp.parser.constructor
	 */
    public PatientRegForm(int test) {
        initialize();
    }

    public PatientRegForm() {
		// TODO Auto-generated constructor stub
	}

	/**
     * Initialize the contents of the frame.
     */
    private void initialize() {
    	//System.out.println("Here");
    	LoggerClass lc = new LoggerClass();
    	Logger logger = lc.loggerMethod();
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

        JLabel lblNewLabel_2 = new JLabel("Password");
        lblNewLabel_2.setBounds(30, 70, 68, 14);
        frame.getContentPane().add(lblNewLabel_2);

        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //When register button is clicked, print name in the dialog box
                //Error checks
            	if(!phonenumberField.getText().isEmpty()){
            		try { 
            	
                    Integer.parseInt(phonenumberField.getText()); 
                } catch(NumberFormatException e) { 
                    phonenumberField.setText("");
                    JOptionPane.showMessageDialog(null, "Phone number cant be strings");
                    logger.info("Phone number :"+e.getMessage());
                }
            	}
            	if(!ageField.getText().isEmpty()){
            		try { 
            	
                    Integer.parseInt(ageField.getText()); 
                } catch(NumberFormatException e) { 
                    ageField.setText("");
                    JOptionPane.showMessageDialog(null, "Age cant be strings");
                    logger.info("Age:"+e.getMessage());
                }
            	}
                if (nameField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter name");
                    logger.info("Name hasnt entered");
                }
                else if (passwordField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter password");
                    logger.info("password hasn't entered");
                }
                else if (ageField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter age");
                    logger.info("age hasn't entered");
                }
                else if (phonenumberField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter phonenumber");
                    logger.info("phonenumber hasn't entered");
                }
                else if (addresstextArea.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter address");
                    logger.info("address hasn't entered");
                }
                else if(!rdbtnLocal.isSelected()&&!rdbtnOpd.isSelected())
                	JOptionPane.showMessageDialog(null, "Please select either OPD or Local");
                else {                 	
                
                //To upload to patient table
                String un = nameField.getText().toString();
                System.out.println(un);
                /*	PATIENT TABLE STRUCTURE
                 * 	1	patID	varchar(20)	
					2	password	varchar(20)
					3	name	varchar(20)	
					4	age	int(11)			
					5	address	varchar(40)	
					6	email	varchar(40)	
					7	phoneNo	varchar(20)			
					8	critical	varchar(20)
					9	department varchar(20)
					10	location varchar(10)*/
                try {
                
                    	DataBase dbConnection = new DataBase();
                        con = dbConnection.getConnection();
                        
                	String selectedLocation = null;
                	if(rdbtnLocal.isSelected())
                		selectedLocation = "Local";
                	else if(rdbtnOpd.isSelected())
                		selectedLocation = "OPD";
                    stmt = con.createStatement();
                    Random rnd = new Random();
                    int n = 100000 + rnd.nextInt(900000);
                    patID = "pat_"+depComboBox.getSelectedItem().toString()+"_"+n;
                    //Check if generated patID already exists
                    //If exists, then generate one more ID
                    while(true) {
                    	String checkPatIDQuery = "SELECT * from patient where patID ="+"'"+patID+"'";
                    	ResultSet rs = stmt.executeQuery(checkPatIDQuery);
                    	if(rs.next()) {
                    		System.out.println("Inside!!!!!!!!!..");
                    		n = 100000 + rnd.nextInt(900000);
                    		patID = "pat_"+depComboBox.getSelectedItem().toString()+"_"+n;
                    	}
                    	else
                    		break;
                    }
                    String sql = "INSERT INTO patient " +
                    		"VALUES ('" + patID + "', " +
                            "'" + passwordField.getText().toString() + "'," +
                            " '" + nameField.getText().toString() + "',"+
                            "'" + Integer.parseInt(ageField.getText()) + "',"+ 
                            "'" + addresstextArea.getText().toString() + "',"+
                            "'" + emailField.getText().toString() + "',"+
                            "'" + Integer.parseInt(phonenumberField.getText().toString()) + "',"+
                            "'" + criticalcomboBox.getSelectedItem().toString() + "',"+
                            "'" + depComboBox.getSelectedItem().toString() + "',"+
                            "'" + selectedLocation + "')";
                    stmt.executeUpdate(sql);
                    con.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    logger.info(e.getMessage());
                	e.printStackTrace();
                } catch(NumberFormatException ne) {
                	logger.info(ne.getMessage());
                }
                JOptionPane.showMessageDialog(null, "Success! Created patientID is " + patID);
                //Clear all the fields
                System.out.println("PatID "+ patID );
                System.out.println("Inserted records into the table...");
                LogInForm form = new LogInForm();
                form.runLogin();
                frame.dispose();
            }
            }
        });
        btnRegister.setBounds(235, 397, 89, 23);
        frame.getContentPane().add(btnRegister);

        JLabel lblPatientRegistration = new JLabel("Patient Registration");
        lblPatientRegistration.setBounds(87, 11, 135, 14);
        frame.getContentPane().add(lblPatientRegistration);

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

        JLabel lblNewLabel = new JLabel("Critical");
        lblNewLabel.setBounds(30, 242, 46, 14);
        frame.getContentPane().add(lblNewLabel);

        String[] values = {
            "null",
            "Yes",
            "No"
        };
        criticalcomboBox = new JComboBox(values);
        criticalcomboBox.setBounds(167, 239, 129, 20);
        frame.getContentPane().add(criticalcomboBox);

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
        
        JLabel lblDepartment = new JLabel("Department");
        lblDepartment.setBounds(30, 292, 86, 14);
        frame.getContentPane().add(lblDepartment);
        
        try {
        	DataBase dbConnection = new DataBase();
            con = dbConnection.getConnection();
            stmt = con.createStatement();
        	} catch (Exception e) {
                e.printStackTrace();
        }
        
        /*String[] ch1 = {
                "1",
                "2",
                "3",
                "4"
            };*/
        depComboBox = new JComboBox();
        depComboBox.setBounds(167, 289, 129, 20);
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
        
        rdbtnLocal = new JRadioButton("Local");
        rdbtnLocal.setBounds(63, 343, 109, 23);
        frame.getContentPane().add(rdbtnLocal);
        
        rdbtnOpd = new JRadioButton("OPD");
        rdbtnOpd.setBounds(249, 343, 109, 23);
        frame.getContentPane().add(rdbtnOpd);
        
        group = new ButtonGroup();
        group.add(rdbtnLocal);
        group.add(rdbtnOpd);
        
        btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		HomePage hForm = new HomePage();
                hForm.runHomePage();
                frame.dispose();
        	}
        });
        btnBack.setBounds(83, 397, 89, 23);
        frame.getContentPane().add(btnBack);
        try {
			con.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    }
}