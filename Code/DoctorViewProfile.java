import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DoctorViewProfile {

	private JFrame frame;
	static Connection con;
    static Statement stmt;

	/**
	 * Launch the application.
	 */
			public void runDocView(String docID) {
				try {
					DoctorViewProfile window = new DoctorViewProfile(docID);
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
	public DoctorViewProfile(String docID) {
		initialize(docID);
	}

	public DoctorViewProfile() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String docID) {
		LoggerClass lc = new LoggerClass();
    	Logger logger = lc.loggerMethod();
		String userIDQuery;
		ResultSet rs;
		try {
			DataBase dbConnection = new DataBase();
	        con = dbConnection.getConnection();
	        stmt = con.createStatement();
	        System.out.println(docID);
	        userIDQuery = "SELECT * from doctor where docId =" + "'" + docID + "'";	     
            rs = stmt.executeQuery(userIDQuery);
	        
            if(rs.next() == false)
        	{
            JOptionPane.showMessageDialog(null, "Doc not registered");
        	logger.info("No Doc Found");
        	}//Or else wont work
		
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("WELCOME ");
		lblWelcome.setBounds(101, 11, 95, 14);
		frame.getContentPane().add(lblWelcome);
		
		JLabel lblDocid = new JLabel("docID");
		lblDocid.setBounds(178, 11, 46, 14);
		frame.getContentPane().add(lblDocid);
		lblDocid.setText(docID);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(29, 45, 95, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblAge = new JLabel("Category");
		lblAge.setBounds(29, 75, 95, 14);
		frame.getContentPane().add(lblAge);
		
		JLabel lblAddress = new JLabel("Position");
		lblAddress.setBounds(29, 105, 95, 14);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblEmail = new JLabel("Department");
		lblEmail.setBounds(29, 145, 95, 14);
		frame.getContentPane().add(lblEmail);
		
		
		JLabel lblPhonenumber = new JLabel("Email");
		lblPhonenumber.setBounds(29, 175, 95, 14);
		frame.getContentPane().add(lblPhonenumber);
		
		JLabel lblDepartment = new JLabel("Qualification");
		lblDepartment.setBounds(29, 205, 95, 14);
		frame.getContentPane().add(lblDepartment);
		
		JLabel TimeFrom = new JLabel("TimeFrom");
		TimeFrom.setBounds(29, 235, 95, 14);
		frame.getContentPane().add(TimeFrom);
		
		JLabel TimeTo = new JLabel("TimeTo");
		TimeTo.setBounds(29, 265, 95, 14);
		frame.getContentPane().add(TimeTo);
			
		JLabel lblName1 = new JLabel("New label");
		lblName1.setBounds(163, 45, 119, 14);
		frame.getContentPane().add(lblName1);
		lblName1.setText(rs.getString(1));
		
		JLabel lblAge1 = new JLabel("New label");
		lblAge1.setBounds(163, 75, 119, 14);
		frame.getContentPane().add(lblAge1);
		lblAge1.setText(rs.getString(3));
		
		JLabel lblAddress1 = new JLabel("New label");
		lblAddress1.setBounds(163, 105, 119, 14);
		frame.getContentPane().add(lblAddress1);
		lblAddress1.setText(rs.getString(4));
		
		JLabel lblEmail1 = new JLabel("New label");
		lblEmail1.setBounds(163, 145, 119, 14);
		frame.getContentPane().add(lblEmail1);
		lblEmail1.setText(rs.getString(5));
		
		JLabel lblPhoneNumber1 = new JLabel("New label");
		lblPhoneNumber1.setBounds(163, 175, 119, 14);
		frame.getContentPane().add(lblPhoneNumber1);
		lblPhoneNumber1.setText(rs.getString(6));
		
		JLabel lblDepartment1 = new JLabel("New label");
		lblDepartment1.setBounds(163, 205, 119, 14);
		frame.getContentPane().add(lblDepartment1);
		lblDepartment1.setText(rs.getString(7));
		
		JLabel timefrom1 = new JLabel("New label");
		timefrom1.setBounds(163, 235, 119, 14);
		frame.getContentPane().add(timefrom1);
		timefrom1.setText(rs.getString(9));
		
		JLabel timeto1 = new JLabel("New label");
		timeto1.setBounds(163, 265, 119, 14);
		frame.getContentPane().add(timeto1);
		timeto1.setText(rs.getString(10));
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DocLogged hForm = new DocLogged();
                hForm.runDocLogged(docID);
                frame.dispose();
			}
		});
		btnBack.setBounds(311, 28, 89, 23);
		frame.getContentPane().add(btnBack);
		
		JButton btnEditProfile = new JButton("Edit Profile");
		btnEditProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DoctorProfile updateDoc = new DoctorProfile();
				updateDoc.runDocUpdate(docID);
				frame.dispose();
			}
		});
		btnEditProfile.setBounds(311, 109, 113, 23);
		frame.getContentPane().add(btnEditProfile);
		con.close();
		}
		catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.info(e.getMessage());
    }
		
	}
}
