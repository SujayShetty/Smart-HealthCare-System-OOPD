import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class DocViewDoctors {

	private JFrame frame;
	private JTable table;
	static Connection con;
    static Statement stmt;
    private JTextField textSearchField;
    ResultSet rs;
	/**
	 * Launch the application.
	 */

			public void runDocViewDoc(String docID) {
				try {
					DocViewDoctors window = new DocViewDoctors(docID);
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
	public DocViewDoctors(String docID) {
		initialize(docID);
	}

	public DocViewDoctors() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String docID) {
		LoggerClass lc = new LoggerClass();
    	Logger logger = lc.loggerMethod();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		DataBase dbConnection = new DataBase();
        con = dbConnection.getConnection();
        
		
		table = new JTable();
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 58, 330, 138);
		frame.getContentPane().add(scrollPane);
		
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane.setViewportView(table);  
		
		JLabel lblListOfAll = new JLabel("LIST OF ALL DOCTORS");
		lblListOfAll.setForeground(Color.BLUE);
		lblListOfAll.setBounds(115, 11, 166, 14);
		frame.getContentPane().add(lblListOfAll);
		try {
			stmt = con.createStatement();
		
      
		String getAllPat ="SELECT * from doctor";
		rs = stmt.executeQuery(getAllPat);
		table.setModel(DbUtils.resultSetToTableModel(rs));
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DocLogged form = new DocLogged();
				form.runDocLogged(docID);
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					logger.info(e1.getMessage());
				}
				frame.dispose();
			}
		});
		btnBack.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnBack);
		
		textSearchField = new JTextField();
		textSearchField.setBounds(91, 211, 86, 20);
		frame.getContentPane().add(textSearchField);
		textSearchField.setColumns(10);
		
		JLabel lblDocid = new JLabel("DocID");
		lblDocid.setBounds(21, 214, 46, 14);
		frame.getContentPane().add(lblDocid);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textSearchField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Enter docID");
				}
				else{
				String getAllDoc ="SELECT * from doctor where docID='"+textSearchField.getText()+"'";
				try {
					rs = stmt.executeQuery(getAllDoc);
					if(!rs.next()) {
						JOptionPane.showMessageDialog(null, "No patient found with the entered patID");	
					}
					else {
						rs = stmt.executeQuery(getAllDoc);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					}
					//con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					logger.info(e1.getMessage());
				}
			}
				}
		});
		btnSearch.setBounds(202, 210, 89, 23);
		frame.getContentPane().add(btnSearch);
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
