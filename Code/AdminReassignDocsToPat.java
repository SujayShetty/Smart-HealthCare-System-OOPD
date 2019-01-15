import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class AdminReassignDocsToPat {

	private JFrame frame;
	private JTextField textPat;
	private JTextField textDoc;
	private JButton btnBack;
	static Connection con;
    static Statement stmt;

	/**
	 * Launch the application.
	 */
			public void runAdminReassignDocsToPat() {
				try {
					AdminReassignDocsToPat window = new AdminReassignDocsToPat();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the application.
	 */
	public AdminReassignDocsToPat() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		LoggerClass lc = new LoggerClass();
    	Logger logger = lc.loggerMethod();
    	DataBase dbConnection = new DataBase();
        con = dbConnection.getConnection();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPatid = new JLabel("PatID");
		lblPatid.setBounds(35, 53, 46, 14);
		frame.getContentPane().add(lblPatid);
		
		JLabel lblDocid = new JLabel("DocID");
		lblDocid.setBounds(35, 99, 46, 14);
		frame.getContentPane().add(lblDocid);
		
		textPat = new JTextField();
		textPat.setBounds(138, 50, 86, 20);
		frame.getContentPane().add(textPat);
		textPat.setColumns(10);
		
		textDoc = new JTextField();
		textDoc.setBounds(138, 96, 86, 20);
		frame.getContentPane().add(textDoc);
		textDoc.setColumns(10);
		
		JButton btnReassign = new JButton("Reassign");
		btnReassign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textPat.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Enter patID");
					logger.info("Enter patID");
				}
				else if(textDoc.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Enter DocID");
					logger.info("Enter DocID");
				}
				else {
					
	                String patID = null;
	                try {
	                	stmt = con.createStatement();
	                	ResultSet rs;
	                	boolean found=true;
	                	boolean foundAssignedDoc =false;
	                	
	                	String checkDocID ="Select * from doctor where docID='"+textDoc.getText()+"'";
	                	String checkPatID ="Select * from patient where patID='"+textPat.getText()+"'";
	                	rs =stmt.executeQuery(checkPatID);
	                	if(!rs.next()) {	               
	    					JOptionPane.showMessageDialog(null, "No pat found with the entered patID");	
	    					logger.info("No pat found with the entered patID");
	    					found = false;
	    				}
	                	rs =stmt.executeQuery(checkDocID);
	                	if(!rs.next()) {	               
	    					JOptionPane.showMessageDialog(null, "No doctor found with the entered docID");
	    					logger.info("No pat found with the entered DocID");
	    					found = false;
	    				}
	                	if(found) {
	                		String checkIfAlreadyAssigned ="Select * from history where patID='"+textPat.getText()+"'";
		    				rs = stmt.executeQuery(checkIfAlreadyAssigned);
		    				while(rs.next()) {
		    					System.out.println("Result :"+rs.getString("status"));
		    					try {
		    					if(rs.getString("status").equals("active")) {
		    						//If some results found and patID status is active
		    						//Get the patID and set it to inactive
		    						foundAssignedDoc = true;
		    						patID = rs.getString("patID");
		    						break;
		    					}
		    					}
		    					catch(Exception e2) {
		    						//foundAssignedDoc= false;
		    						//If some exception just ignore that
		    					}
		    				}
		    				if(foundAssignedDoc) {
		    					//if already entry is there then set status to inactive and newly_assigned to no
		    					JOptionPane.showMessageDialog(null,"Already "+rs.getString(1)+" is assigned for you and reseting its status");
		    					String updateStatus="UPDATE `history` SET `status`='inactive', newly_assigned='no' where patID='"+patID+"'";
		    					stmt.executeUpdate(updateStatus);
		    				}
		    				//Set status to active and newly_assigned to yes
						String sql = "INSERT INTO `history`(`docID`, `patID`,`status`,`newly_assigned`) VALUES ('"+textDoc.getText()+"','"+textPat.getText()+"','active','yes')";
        				//String sql2 = "INSERT INTO `newly_assigned`(`docID`, `patID`) VALUES ('"+textDoc.getText()+"','"+textPat.getText()+"')";
        				stmt.executeUpdate(sql);
        				//stmt.executeUpdate(sql2);
        				JOptionPane.showMessageDialog(null, "Successfully reassigned doctor "+textDoc.getText()+" to patient "+textPat.getText());
        				AdminLogged form = new AdminLogged();
        				form.runAdminLogged();
        				con.close();
        				frame.dispose();
	             
	                	}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						logger.info(e1.getMessage());
					}
				}
			}
		});
		btnReassign.setBounds(204, 160, 89, 23);
		frame.getContentPane().add(btnReassign);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminLogged form = new AdminLogged();
				form.runAdminLogged();
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.dispose();
				
			}
		});
		btnBack.setBounds(59, 160, 89, 23);
		frame.getContentPane().add(btnBack);
	}

}
