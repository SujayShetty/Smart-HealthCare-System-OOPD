
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
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class ReferDoc {

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
    private JTextField textField;
    private JTextField textField_1;
    private JButton btnSubmit;
    private JButton btnNewButton;
    /**
     * Launch the application.
     * @wbp.parser.entryPoint
     */
    public void runReferDoc(String docId) {
        try {
            ReferDoc window = new ReferDoc(docId);
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
    public ReferDoc()
    {
    	
    }
    /**
     * Create the application.
     * @wbp.parser.entryPoint
     */
    public ReferDoc(String docId) {
        initialize(docId);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(String docId) {
    	LoggerClass lc = new LoggerClass();
    	Logger logger = lc.loggerMethod();
    	
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
                        
        JLabel lblNewLabel = new JLabel("PatId");
        lblNewLabel.setBounds(69, 63, 70, 15);
        frame.getContentPane().add(lblNewLabel);
        
        textField = new JTextField();
        textField.setBounds(197, 63, 114, 19);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel(" New DocId");
        lblNewLabel_1.setBounds(69, 126, 94, 19);
        frame.getContentPane().add(lblNewLabel_1);
        
        textField_1 = new JTextField();
        textField_1.setBounds(197, 126, 114, 19);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);
        
        btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
                	DataBase dbConnection = new DataBase();
                    con = dbConnection.getConnection();
                    
                	} catch (Exception ef) {
                        ef.printStackTrace();
                }
                //To upload to patient table
                String newdoc = textField_1.getText().toString();
                if(newdoc.isEmpty())
                {JOptionPane.showMessageDialog(null, "Doctor Id not Entered");
                logger.info("Doctor Id not Entered");}
                System.out.println(newdoc);
                
                try {
                	System.out.println("Here");
                    stmt = con.createStatement();
                    System.out.println(docId);
                    ResultSet rs1 ;
                    String sql1 = "SELECT department,position from `doctor` where `docID` ="+"'" + docId + "'";
                    rs1 = stmt.executeQuery(sql1);
                    System.out.println("Query Executed");
                    if(!rs1.next())
                	{
                    	System.out.println("Query Executed inside noDoc");
                    JOptionPane.showMessageDialog(null, "Doc not registered");
                	logger.info("No Doc Found");
                	}
                    int f = 0;
                    String dept = rs1.getString(1);
                    String position = rs1.getString(2);
                    String sql2 = "SELECT department from `doctor` where `docID` ="+"'" + newdoc + "'";
                    ResultSet rs2 = stmt.executeQuery(sql2);
                    if(rs2.next() == false)
                	{
                    JOptionPane.showMessageDialog(null, "New Doctor not Registered");
                	logger.info("No Doc Found");
                	f=1;
                	}else {
                		//rs1 = stmt.executeQuery(sql1);
                    if(! position.equals("HOD"))
                    { 
                     System.out.println("Here 3");            
//                    String sql21 = "SELECT department from `doctor` where `docID` ="+"'" + newdoc + "'";
//                    ResultSet rs21 = stmt.executeQuery(sql21);
//                   if(rs21.next() == false)
//                	{
//                    JOptionPane.showMessageDialog(null, "New Doctor not Registered");
//                	logger.info("No Doc Found");
//                	}
//                    System.out.println("Query2 executed");
//                    rs21.next();
                    if(! dept.equals(rs2.getString(1)))
                    {
                    	System.out.println("Comparision performed");
                    	JOptionPane.showMessageDialog(null, "Please refer to someone in same department!!!");
                    	f = 1;
                    }
                    }
                    }
                    if(f == 0)
                    {
                	String patID = textField.getText().toString();
                	if(patID.isEmpty())
                    {JOptionPane.showMessageDialog(null, "Patient Id not Entered");
                    logger.info("Patient Id not Entered");}
                	else {
                		try {
                		DataBase dbConnection = new DataBase();
            	        con = dbConnection.getConnection();
            	        stmt = con.createStatement();
            	        String searchQuery =  "SELECT * from history where docID='"+docId+"' and patID =" + "'" + patID + "'";
            	        ResultSet rs = (ResultSet) stmt.executeQuery(searchQuery);
                        if(! rs.next())
                        {
                        	JOptionPane.showMessageDialog(null, "Patient doesn't exist");
                        }
            	        
                	
            	        else
            	        {
            	        	String stat = "I";
                            String sql3 = "UPDATE `history` SET `status`= '"+
                                    stat+"' WHERE patID ='"+
                            textField.getText()+"'and docID='"+docId+ "'"; 
                            
                            
                            String sql4 = "INSERT INTO `history`(`docID`, `patID`,`status`,`newly_assigned`) VALUES ('"+newdoc+"','"+patID+"','active','yes')";
                            stmt.executeUpdate(sql3);
                            stmt.executeUpdate(sql4);
                            JOptionPane.showMessageDialog(null, "Success! Refered to doc " + newdoc);
            	        }
                        con.close();
                }
                		catch(Exception ep)
                		{
                			ep.printStackTrace();
            		    
                		}
                		}
                    
                    }
                    con.close();
                } catch (SQLException ex) {
                    // TODO Auto-generated catch block
                    ex.printStackTrace();
                    logger.info(ex.getMessage());
                }
        		
        	}
        });
        
        btnSubmit.setBounds(69, 275, 117, 25);
        frame.getContentPane().add(btnSubmit);
        
        btnNewButton = new JButton("Back");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		DocLogged hForm = new DocLogged();
                hForm.runDocLogged(docId);
                frame.dispose();
        	}
        });
        btnNewButton.setBounds(270, 275, 117, 25);
        frame.getContentPane().add(btnNewButton);
        
        

    }
}
