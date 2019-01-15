import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class PatViewHistory {

	private JFrame frame;
	private JTable table;

	static Connection con;
    static Statement stmt;
	/**
	 * Launch the application.
	 */
			public void runPatViewHistory(String patID) {
				try {
					PatViewHistory window = new PatViewHistory(patID);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the application.
	 */
			/**
			 * @wbp.parser.constructor
			 */
	public PatViewHistory(String patID) {
		initialize(patID);
	}

	public PatViewHistory() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String patID) {
		DataBase dbConnection = new DataBase();
        con = dbConnection.getConnection();
        ResultSet rs;
        
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaption);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHistory = new JLabel("HISTORY :");
		lblHistory.setBounds(113, 11, 81, 14);
		frame.getContentPane().add(lblHistory);
		
		JLabel lblPatid = new JLabel("patID");
		lblPatid.setBounds(204, 11, 91, 14);
		frame.getContentPane().add(lblPatid);
		lblPatid.setText(patID);
		
		table = new JTable();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 59, 325, 127);
		frame.getContentPane().add(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane.setViewportView(table); 
        try {
			stmt = con.createStatement();        
			String getHistory ="SELECT patID,docID,department,disease,medicine,"
					+ " test,day_of_a, toa, day_of_d, day_of_d,tod,newly_assigned from history where patID='"+patID+"'";
			rs = stmt.executeQuery(getHistory);
			//rs.next();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			JButton btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PatLogged form = new PatLogged();
					form.runPatLogged(patID);
					frame.dispose();
				}
			});
			btnBack.setBounds(308, 210, 89, 23);
			frame.getContentPane().add(btnBack);
			con.close();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
