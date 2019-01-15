import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class AdminDeleteUsers {

	private JFrame frame;
	private JTextField textField;
	static Connection con;
    static Statement stmt;

	/**
	 * Launch the application.
	 */
			public void runAdminDeleteUsers() {
				try {
					AdminDeleteUsers window = new AdminDeleteUsers();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the application.
	 */
	public AdminDeleteUsers() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		LoggerClass lc = new LoggerClass();
    	Logger logger = lc.loggerMethod();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JRadioButton rdbtnDoctor = new JRadioButton("Doctor");
		rdbtnDoctor.setBounds(55, 29, 109, 23);
		frame.getContentPane().add(rdbtnDoctor);
		
		JRadioButton rdbtnPatient = new JRadioButton("Patient");
		rdbtnPatient.setBounds(199, 29, 109, 23);
		frame.getContentPane().add(rdbtnPatient);
		
		JRadioButton rdbtnDepartment = new JRadioButton("Department");
		rdbtnDepartment.setBounds(310, 29, 109, 23);
		frame.getContentPane().add(rdbtnDepartment);
		
		ButtonGroup btnGrp = new ButtonGroup();
		btnGrp.add(rdbtnDepartment);
		btnGrp.add(rdbtnDoctor);
		btnGrp.add(rdbtnPatient);
		
		textField = new JTextField();
		textField.setBounds(198, 105, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterId = new JLabel("Enter ID");
		lblEnterId.setBounds(85, 108, 79, 14);
		frame.getContentPane().add(lblEnterId);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean deleted=false;
				if(textField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter ID to delete");
					logger.info("Please enter ID to delete");
				}else {
					String ID= textField.getText();
					DataBase dbConnection = new DataBase();
	                con = dbConnection.getConnection();
	                try {
						stmt = con.createStatement();
					
				if(rdbtnDepartment.isSelected()) {
					String deleteQuery ="DELETE FROM `department` WHERE depID='"+ID+"'";
					int found= stmt.executeUpdate(deleteQuery);
					//If depid is found, executeUpdate returns zero
					if(found==0) {
						JOptionPane.showMessageDialog(null, "No entered depID found in the system");
						logger.info("No entered depID found in the system");
					}
					else {
					JOptionPane.showMessageDialog(null, "Successfully Deleted");
					deleted = true;
					}
				}
				else if(rdbtnDoctor.isSelected()) {
					String deleteQuery ="DELETE FROM `doctor` WHERE docID='"+ID+"'";
					int found= stmt.executeUpdate(deleteQuery);
					//If docid is found, executeUpdate returns zero
					if(found==0) {
						JOptionPane.showMessageDialog(null, "No entered docID found in the system");
						logger.info("No entered depID found in the system");
					}
					else {
					JOptionPane.showMessageDialog(null, "Successfully Deleted");
					deleted=true;
					}
				}
				else if(rdbtnPatient.isSelected()) {
					String deleteQuery ="DELETE FROM `patient` WHERE patID='"+ID+"'";
					int found= stmt.executeUpdate(deleteQuery);
					if(found==0)
						JOptionPane.showMessageDialog(null, "No entered patID found in the system");
					else {
					JOptionPane.showMessageDialog(null, "Successfully Deleted");
						deleted =true;
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Please select Doc/Pat/Dep to delete");
				}
				if(deleted) {
					AdminLogged form = new AdminLogged();
					form.runAdminLogged();
					frame.dispose();
				}
				con.close();
	                } catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						logger.info(e1.getMessage());
					}
				}
			}
		});
		btnDelete.setBounds(195, 153, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminLogged form = new AdminLogged();
				form.runAdminLogged();
				frame.dispose();
				
			}
		});
		btnBack.setBounds(85, 153, 89, 23);
		frame.getContentPane().add(btnBack);
	}
}
