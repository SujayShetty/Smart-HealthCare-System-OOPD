import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AdminAddDep {

	private JFrame frame;
	private JTextField textDepName;
	private JTextField textHOD;
    static Connection con;
    static Statement stmt;
    private JTextField textDepID;

	/**
	 * Launch the application.
	 */

			public void runAdminAddDep() {
				try {
					AdminAddDep window = new AdminAddDep();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the application.
	 */
	public AdminAddDep() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(51, 78, 83, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblHod = new JLabel("HOD");
		lblHod.setBounds(51, 120, 81, 14);
		frame.getContentPane().add(lblHod);
		
		textDepName = new JTextField();
		textDepName.setBounds(184, 75, 99, 20);
		frame.getContentPane().add(textDepName);
		textDepName.setColumns(10);
		
		textHOD = new JTextField();
		textHOD.setBounds(184, 117, 99, 20);
		frame.getContentPane().add(textHOD);
		textHOD.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textDepName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter department name");
				}
				else if(textHOD.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter HOD ID");
				}
				else {
					DataBase dbConnection = new DataBase();
                    con = dbConnection.getConnection();
                    try {
						stmt = con.createStatement();
						String depExists="SELECT * FROM `department` where depID='"+textDepID.getText()+"'";
						String HODexists="SELECT * FROM `doctor` where position='HOD' and name='"+textHOD.getText()+"'";
						ResultSet rs = stmt.executeQuery(depExists);
						if(rs.next()) {
							JOptionPane.showMessageDialog(null, "Department already exists");
						}
						else {
							rs = stmt.executeQuery(HODexists);
							//Check if any HOD name found in doctor table
							if(!rs.next()) {
								JOptionPane.showMessageDialog(null, "Doctor(HOD) doesnt exist in doctor db");
							}
							else {
						String sql = "INSERT INTO `department`(`depID`, `depName`, `HOD`) VALUES ('"+textDepID.getText()+"','"+
						textDepName.getText()+"','"+textHOD.getText()+"')";
	                    stmt.executeUpdate(sql);
	                    //Write query to update doctors position here 
	                    System.out.println("Successfully inserted department to the db");
	                    JOptionPane.showMessageDialog(null, "Success! Added new department " + textDepName.getText());
	                    con.close();
	                    AdminLogged form = new AdminLogged();
	                    form.runAdminLogged();
	                    frame.dispose();
						}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnAdd.setBounds(229, 167, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		JLabel lblAddNewDepartment = new JLabel("Add New Department");
		lblAddNewDepartment.setForeground(Color.BLUE);
		lblAddNewDepartment.setBounds(142, 11, 141, 14);
		frame.getContentPane().add(lblAddNewDepartment);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminLogged form = new AdminLogged();
				form.runAdminLogged();
				frame.dispose();
			}
		});
		btnBack.setBounds(73, 167, 89, 23);
		frame.getContentPane().add(btnBack);
		
		JLabel lblDepId = new JLabel("Dep ID");
		lblDepId.setBounds(50, 42, 46, 14);
		frame.getContentPane().add(lblDepId);
		
		textDepID = new JTextField();
		textDepID.setBounds(184, 36, 99, 20);
		frame.getContentPane().add(textDepID);
		textDepID.setColumns(10);
	}
}
