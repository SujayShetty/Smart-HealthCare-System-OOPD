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

public class History 
{
	
	static Connection con;
    static Statement stmt;	
	private JFrame frame;
	public void runHisForm(String patID, String docID) 
	{
		try {
			History window = new History(patID,docID);
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

/**
 * @wbp.parser.entryPoint
 */
public History(String patID, String docID)
{
initialize(patID, docID);
}
/**
 * @wbp.parser.constructor
 * @wbp.parser.entryPoint
 */

public History() 
{
// TODO Auto-generated constructor stub
}

private void initialize(String patID, String docID) 
{
	LoggerClass lc = new LoggerClass();
	Logger logger = lc.loggerMethod();
frame = new JFrame();
frame.setBounds(100, 100, 450, 400);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.getContentPane().setLayout(null);
String userIDQuery;
ResultSet rs;
try {
	DataBase dbConnection = new DataBase();
    con = dbConnection.getConnection();
    stmt = con.createStatement();
    System.out.println(patID);
    String stat = "active";
    userIDQuery = "SELECT * from history where docID='"+docID+"' and patID =" + "'" + patID + "'" + "AND status =" + "'" + stat + "'";	     
    //userIDQuery = "SELECT * from history where patID =" + "'" + patID + "'";	     

    rs = stmt.executeQuery(userIDQuery);
    if(rs.next() == false)
	{
    JOptionPane.showMessageDialog(null, "No Profile available");
	logger.info("No History Found");
	}
    JLabel lblName = new JLabel("Department");
	lblName.setBounds(29, 30, 95, 14);
	frame.getContentPane().add(lblName);
	
	JLabel lblAge = new JLabel("DocId");
	lblAge.setBounds(29, 60, 95, 14);
	frame.getContentPane().add(lblAge);
	
	JLabel lblAddress = new JLabel("Disease");
	lblAddress.setBounds(29, 90, 95, 14);
	frame.getContentPane().add(lblAddress);
	
	JLabel lblEmail = new JLabel("Medicine");
	lblEmail.setBounds(29, 120, 95, 14);
	frame.getContentPane().add(lblEmail);
	
	
	JLabel lblPhonenumber = new JLabel("Test");
	lblPhonenumber.setBounds(29, 150, 95, 14);
	frame.getContentPane().add(lblPhonenumber);
	
	JLabel lblDepartment = new JLabel("Admitted");
	lblDepartment.setBounds(29, 180, 95, 14);
	frame.getContentPane().add(lblDepartment);
	
	JLabel lblDepartment5 = new JLabel("Discharge");
	lblDepartment5.setBounds(29, 210, 95, 14);
	frame.getContentPane().add(lblDepartment5);
	
	
	
	
	
	JLabel lblAddress1 = new JLabel("New label");
	lblAddress1.setBounds(163, 30, 119, 14);
	frame.getContentPane().add(lblAddress1);
	lblAddress1.setText(rs.getString(3));
	
	JLabel lblEmail1 = new JLabel("New label");
	lblEmail1.setBounds(163, 60, 119, 14);
	frame.getContentPane().add(lblEmail1);
	lblEmail1.setText(rs.getString(1));
	
	JLabel lblPhoneNumber1 = new JLabel("New label");
	lblPhoneNumber1.setBounds(163, 90, 119, 14);
	frame.getContentPane().add(lblPhoneNumber1);
	lblPhoneNumber1.setText(rs.getString(4));
	
	JLabel lblDepartment1 = new JLabel("New label");
	lblDepartment1.setBounds(163, 120, 119, 14);
	frame.getContentPane().add(lblDepartment1);
	lblDepartment1.setText(rs.getString(5));
	
	JLabel lblDepartment2 = new JLabel("New label");
	lblDepartment2.setBounds(163, 150, 119, 14);
	frame.getContentPane().add(lblDepartment2);
	lblDepartment2.setText(rs.getString(6));
	
	JLabel lblDepartment3 = new JLabel("New label");
	lblDepartment3.setBounds(163, 180, 119, 14);
	frame.getContentPane().add(lblDepartment3);
	lblDepartment3.setText(rs.getString(8));
	
	JLabel lblDepartment4 = new JLabel("New label");
	lblDepartment4.setBounds(163, 210, 119, 14);
	frame.getContentPane().add(lblDepartment4);
	lblDepartment4.setText(rs.getString(10));
	
	JButton btnEdit = new JButton("Edit");
	btnEdit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			HistoryUpdate up = new HistoryUpdate();
			up.runHistoryUpdate(patID, lblPhonenumber.getText().toString());
			
		}
	});
	btnEdit.setBounds(45, 290, 117, 25);
	frame.getContentPane().add(btnEdit);
	
	JButton btnNewButton = new JButton("Back");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			DocLogged dForm = new DocLogged();
			dForm.runDocLogged(docID);
		}
	});
	btnNewButton.setBounds(253, 290, 117, 25);
	frame.getContentPane().add(btnNewButton);
	
}
catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    logger.info(e.getMessage());
}
    
    
//Or else wont work

}
}
