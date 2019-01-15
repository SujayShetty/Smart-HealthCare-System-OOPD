import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.UIManager;

public class PatientViewProfile {

	private JFrame frame;
	static Connection con;
    static Statement stmt;

	/**
	 * Launch the application.
	 */
			public void runPatView(String patID) {
				try {
					PatientViewProfile window = new PatientViewProfile(patID);
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
	public PatientViewProfile(String patID) {
		initialize(patID);
	}

	public PatientViewProfile() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String patID) {
		String userIDQuery;
		ResultSet rs;
		try {
			DataBase dbConnection = new DataBase();
	        con = dbConnection.getConnection();
	        stmt = con.createStatement();
	        System.out.println(patID);
	        userIDQuery = "SELECT * from patient where patID =" + "'" + patID + "'";	     
            rs = stmt.executeQuery(userIDQuery);
	        
		rs.next();//Or else wont work
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("WELCOME ");
		lblWelcome.setForeground(Color.BLUE);
		lblWelcome.setBounds(101, 11, 95, 14);
		frame.getContentPane().add(lblWelcome);
		
		JLabel lblPatid = new JLabel("patID");
		lblPatid.setForeground(Color.BLUE);
		lblPatid.setBounds(178, 11, 46, 14);
		frame.getContentPane().add(lblPatid);
		lblPatid.setText(patID);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(29, 45, 95, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(29, 80, 95, 14);
		frame.getContentPane().add(lblAge);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(29, 113, 95, 14);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(29, 149, 95, 14);
		frame.getContentPane().add(lblEmail);
		
		
		JLabel lblPhonenumber = new JLabel("PhoneNumber");
		lblPhonenumber.setBounds(29, 174, 95, 14);
		frame.getContentPane().add(lblPhonenumber);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(29, 204, 95, 14);
		frame.getContentPane().add(lblDepartment);
		
		JLabel lblName1 = new JLabel("New label");
		lblName1.setBounds(163, 45, 119, 14);
		frame.getContentPane().add(lblName1);
		lblName1.setText(rs.getString(3));
		
		JLabel lblAge1 = new JLabel("New label");
		lblAge1.setBounds(163, 80, 119, 14);
		frame.getContentPane().add(lblAge1);
		lblAge1.setText(rs.getString(4));
		
		JLabel lblAddress1 = new JLabel("New label");
		lblAddress1.setBounds(163, 113, 119, 14);
		frame.getContentPane().add(lblAddress1);
		lblAddress1.setText(rs.getString(5));
		
		JLabel lblEmail1 = new JLabel("New label");
		lblEmail1.setBounds(163, 149, 119, 14);
		frame.getContentPane().add(lblEmail1);
		lblEmail1.setText(rs.getString(6));
		
		JLabel lblPhoneNumber1 = new JLabel("New label");
		lblPhoneNumber1.setBounds(163, 174, 119, 14);
		frame.getContentPane().add(lblPhoneNumber1);
		lblPhoneNumber1.setText(rs.getString(7));
		
		JLabel lblDepartment1 = new JLabel("New label");
		lblDepartment1.setBounds(163, 204, 119, 14);
		frame.getContentPane().add(lblDepartment1);
		lblDepartment1.setText(rs.getString(9));
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(UIManager.getColor("Button.background"));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PatLogged hForm = new PatLogged();
                hForm.runPatLogged(patID);
                frame.dispose();
			}
		});
		btnBack.setBounds(311, 28, 89, 23);
		frame.getContentPane().add(btnBack);
		
		JButton btnEditProfile = new JButton("Edit Profile");
		btnEditProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PatientEditProfile form = new PatientEditProfile();
				form.runPatUpdate(patID);
				frame.dispose();
			}
		});
		btnEditProfile.setBounds(311, 80, 113, 23);
		frame.getContentPane().add(btnEditProfile);
		
		
		con.close();
		}
		catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
    }
		
	}
}
