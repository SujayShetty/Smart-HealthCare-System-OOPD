import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class HistoryUpdate {
	private JFrame frame;
	private JTextField depField;
	private JTextField diseaseField;
	private JTextField TestField;
	private JTextField daofArrivalField;
	private JTextField medField;
	private JButton btnBack;
	static Connection con;
    static Statement stmt;
    private JLabel lblTimeOfArrival;
    private JTextField timeofArrivalField;
    private JLabel lblNewLabel;
    private JTextField DayofRelease;
    private JLabel lblNewLabel_4;
    private JTextField timeofRel;
    /**
     * Launch the application.
     * @wbp.parser.entryPoint
     */
    public void runHistoryUpdate(String patID, String docID) {
        try {
            HistoryUpdate window = new HistoryUpdate(patID, docID);
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
    public HistoryUpdate()
    {
    	
    }
    /**
     * Create the application.
     * @wbp.parser.entryPoint
     */
    public HistoryUpdate(String patID, String docID) {
        initialize(patID, docID);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(String patID, String docID) {
    	LoggerClass lc = new LoggerClass();
    	Logger logger = lc.loggerMethod();
    	
        frame = new JFrame();
        frame.setBounds(100, 100, 550, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        String userIDQuery;
		
		try {
			DataBase dbConnection = new DataBase();
	        con = dbConnection.getConnection();
	        stmt = con.createStatement();
	        System.out.println(patID);
	        
            depField = new JTextField();
            depField.setBounds(167, 36, 86, 20);
            frame.getContentPane().add(depField);
            depField.setColumns(10);

            JLabel lblNewLabel_1 = new JLabel("Department");
            lblNewLabel_1.setBounds(30, 39, 68, 14);
            frame.getContentPane().add(lblNewLabel_1);

            JLabel lblNewLabel_2 = new JLabel("Disease");
            lblNewLabel_2.setBounds(30, 81, 68, 14);
            frame.getContentPane().add(lblNewLabel_2);
            JButton btnUpdate = new JButton("Update");
            btnUpdate.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		try {
            			
            			if(!depField.getText().equals(""))
            			{
            			String updateQuery1 = "UPDATE `history` SET `department`='"+
    	                		depField.getText() +"' WHERE patID ='"+
    	                patID+"'and docID='"+docID+"'";
            			
            			int rs1 = stmt.executeUpdate(updateQuery1);
            			}
            			if(!diseaseField.getText().equals(""))
            			{String updateQuery2 = "UPDATE `history` SET `disease`='"+
            	                		diseaseField.getText() +"' WHERE patID ='"+
            	                patID+"' and docID='"+docID+"'";
            			int rs2 = stmt.executeUpdate(updateQuery2);
            			}
            			if(!medField.getText().equals(""))
            			{   System.out.println(medField.getText());
            				String updateQuery3 = "UPDATE `history` SET `medicine`='"+
                    		medField.getText() +"' WHERE patID ='"+
                    		patID+"'and docID='"+docID+ "'";
            				int rs3 = stmt.executeUpdate(updateQuery3);
            			}
            			if(!TestField.getText().equals(""))
            			{
            				String updateQuery4 = "UPDATE `history` SET `test`='"+
        	                		TestField.getText() +"' WHERE patID ='"+
        	                patID+"'and docID='"+docID+ "'";
            				int rs4 = stmt.executeUpdate(updateQuery4);
            			}
            			if(!daofArrivalField.getText().equals(""))
            			{
            			String updateQuery5 = "UPDATE `history` SET `day_of_a`='"+
                		daofArrivalField.getText() +"' WHERE patID ='"+
                		patID+"'and docID='"+docID+ "'";
            			int rs5 = stmt.executeUpdate(updateQuery5);
            			}
            			if(!timeofArrivalField.getText().equals(""))
            			{
		    			String updateQuery6 = "UPDATE `history` SET `toa`='"+
		                		timeofArrivalField.getText() +"' WHERE patID ='"+
		                patID+"'and docID='"+docID+ "'";
		    			int rs6 = stmt.executeUpdate(updateQuery6);
            			}
            			if(!DayofRelease.getText().equals(""))
            			{
		    			String updateQuery7 = "UPDATE `history` SET `day_of_d`='"+
		                		DayofRelease.getText() +"' WHERE patID ='"+
		                patID+"'and docID='"+docID+ "'";
		    			int rs7 = stmt.executeUpdate(updateQuery7);
            			}
            			if(!timeofRel.getText().equals(""))
            			{
		    			String updateQuery8 = "UPDATE `history` SET `tod`='"+
                		timeofRel.getText() +"' WHERE patID ='"+
		                patID+"'and docID='"+docID+ "'";
		    			int rs8 = stmt.executeUpdate(updateQuery8);
            			}
            			JOptionPane.showMessageDialog(null, "Medical Details updated!!!");
                    System.out.println("Updated!!!!");
                    DocLogged pForm = new DocLogged();
                    pForm.runDocLogged(docID);
                    frame.dispose();
                    con.close();
            		} catch (Exception e1) {
                        e1.printStackTrace();
                }
            	}
            });
            btnUpdate.setBounds(241, 396, 89, 23);
            frame.getContentPane().add(btnUpdate);

            JLabel lblDoctorProfileUpdate = new JLabel("Medicine History Update");
            lblDoctorProfileUpdate.setBounds(87, 11, 135, 14);
            frame.getContentPane().add(lblDoctorProfileUpdate);

            diseaseField = new JTextField();
            diseaseField.setBounds(167, 78, 86, 20);
            frame.getContentPane().add(diseaseField);

            JLabel lblAddress = new JLabel("Medicine");
            lblAddress.setBounds(30, 132, 68, 14);
            frame.getContentPane().add(lblAddress);

            JLabel lblEmail = new JLabel("Test");
            lblEmail.setBounds(30, 175, 68, 14);
            frame.getContentPane().add(lblEmail);

            TestField = new JTextField();
            TestField.setBounds(167, 172, 135, 20);
            frame.getContentPane().add(TestField);
            TestField.setColumns(10);

            JLabel lblNewLabel_3 = new JLabel("Day of Arrival");
            lblNewLabel_3.setBounds(30, 218, 108, 14);
            frame.getContentPane().add(lblNewLabel_3);

            daofArrivalField = new JTextField();
            daofArrivalField.setBounds(167, 215, 135, 20);
            frame.getContentPane().add(daofArrivalField);
            daofArrivalField.setColumns(10);

            medField = new JTextField();
            medField.setBounds(167, 127, 86, 20);
            frame.getContentPane().add(medField);
            
            btnBack = new JButton("Back");
            btnBack.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		DocLogged hForm = new DocLogged();
                    hForm.runDocLogged(docID);
                    frame.dispose();
            	}
            });
            btnBack.setBounds(49, 396, 89, 23);
            frame.getContentPane().add(btnBack);
            
            lblTimeOfArrival = new JLabel("<html>Time of Arrival<br> hh:mm:ss</html>");
            lblTimeOfArrival.setBounds(30, 256, 108, 30);
            frame.getContentPane().add(lblTimeOfArrival);
            
            timeofArrivalField = new JTextField();
            timeofArrivalField.setBounds(167, 254, 114, 19);
            frame.getContentPane().add(timeofArrivalField);
            timeofArrivalField.setColumns(10);
            
            lblNewLabel = new JLabel("Day of Release");
            lblNewLabel.setBounds(30, 297, 90, 15);
            frame.getContentPane().add(lblNewLabel);
            
            DayofRelease = new JTextField();
            DayofRelease.setBounds(167, 294, 114, 19);
            frame.getContentPane().add(DayofRelease);
            DayofRelease.setColumns(10);
            
            lblNewLabel_4 = new JLabel("<html>Time of Release<br> hh:mm:ss</html>");
            lblNewLabel_4.setBounds(30, 335, 173, 30);
            frame.getContentPane().add(lblNewLabel_4);
            
            timeofRel = new JTextField();
            timeofRel.setBounds(167, 346, 114, 19);
            frame.getContentPane().add(timeofRel);
            timeofRel.setColumns(10);
		
		
		}
		catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.info(e.getMessage());
            
    }
    }

}
