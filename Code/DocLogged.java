import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

//import com.mysql.jdbc.ResultSet;

import java.sql.Connection;
import javax.swing.JTable;
import java.awt.Color;
public class DocLogged {
	
	private JFrame frame;
	private JTextField textField;
	private JTextField searchField;
	static Connection con;
    static Statement stmt;
    private JTable table;
    private JTable table_1;
	/**
	 * Launch the application.
	 */
			public void runDocLogged(String docId) {
				try {
					DocLogged window = new DocLogged(docId);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			/**
			 * @wbp.parser.constructor
			 */


		public DocLogged(String docId) {
		initialize(docId);
	}
	public DocLogged() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String docId) {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Assigned Patients");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(47, 49, 165, 15);
		frame.getContentPane().add(lblNewLabel);
		
		searchField = new JTextField();
		searchField.setBounds(117, 285, 182, 25);
		frame.getContentPane().add(searchField);
		searchField.setColumns(10);
		
		JButton btnNewButton = new JButton("View");
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	if(searchField.getText().isEmpty()) {
            		JOptionPane.showMessageDialog(null, "Please enter patient ID");
            	}
            	else {
            		try {
            		DataBase dbConnection = new DataBase();
        	        con = dbConnection.getConnection();
        	        stmt = con.createStatement();
        	        String searchQuery =  "SELECT * from history where docID='"+docId+"' and patID =" + "'" + searchField.getText() + "'";
        	        ResultSet rs = (ResultSet) stmt.executeQuery(searchQuery);
                    if(! rs.next())
                    {
                    	JOptionPane.showMessageDialog(null, "Patient doesn't exist");
                    }
        	        
            	
        	        else
        	        {
        	    History medForm = new History();
                medForm.runHisForm(searchField.getText().toString(), docId);
                frame.dispose();
        	        }
                    con.close();
            }
            		catch(Exception ep)
            		{
            			ep.printStackTrace();
        		    
            		}
            		}
            
            }
        });
		btnNewButton.setBounds(47, 322, 117, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(searchField.getText().isEmpty()) {
            		JOptionPane.showMessageDialog(null, "Please enter patient ID");
            	}
            	else {
            		try {
            		DataBase dbConnection = new DataBase();
        	        con = dbConnection.getConnection();
        	        stmt = con.createStatement();
        	        String searchQuery =  "SELECT * from history where docID='"+docId+"' and patID =" + "'" + searchField.getText() + "'";
        	        ResultSet rs = (ResultSet) stmt.executeQuery(searchQuery);
                    if(! rs.next())
                    {
                    	JOptionPane.showMessageDialog(null, "Patient doesn't exist");
                    }
        	        
            	
        	        else
        	        {
        	    HistoryUpdate medForm = new HistoryUpdate();
                medForm.runHistoryUpdate(searchField.getText().toString(), docId);
                frame.dispose();
        	        }
                    con.close();
            }
            		catch(Exception ep)
            		{
            			ep.printStackTrace();
        		    
            		}
            		}
            }
        });
		btnNewButton_1.setBounds(246, 322, 117, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Logout");
		btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		DataBase dbConnection = new DataBase();
		        con = dbConnection.getConnection();
		        try {
					stmt = con.createStatement();
				
		        String updateNewly_assigned="Update history SET newly_assigned ='no' where docID='"+docId+"'";
		        stmt.executeUpdate(updateNewly_assigned);
		        con.close();
		        } catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        HomePage hForm = new HomePage();
        		hForm.runHomePage();
        		frame.dispose();
        	}
        });
		btnNewButton_2.setBounds(421, 110, 117, 25);
		frame.getContentPane().add(btnNewButton_2);
		JButton btnViewProfile = new JButton("View Profile");
		btnViewProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("DocId" + docId);
                DoctorViewProfile viewProfile = new DoctorViewProfile();
                viewProfile.runDocView(docId);
                frame.dispose();
            }
        });
		btnViewProfile.setBounds(421, 147, 117, 25);
		frame.getContentPane().add(btnViewProfile);
		
		JButton button = new JButton("Refer");
		button.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
	               ReferDoc refDoc = new ReferDoc();
	                refDoc.runReferDoc(docId);
	                frame.dispose();
	            }
			
		});
		button.setBounds(421, 184, 117, 25);
		frame.getContentPane().add(button);
		
		JButton btnAdminRights = new JButton("Admin Rights");
		btnAdminRights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DataBase dbConnection = new DataBase();
			        con = dbConnection.getConnection();
			        stmt = con.createStatement();
			        String query10 = "Select position from doctor where docID ="+"'" +docId + "'";
			        ResultSet rs = (ResultSet)stmt.executeQuery(query10);
			        rs.next();
			        if(! rs.getString(1).equals("HOD"))
			        {
			        	JOptionPane.showMessageDialog(null, "Sorry you are not an Admin!!!");
			        }
			        else
			        {
			        	DocAdmin da = new DocAdmin();
			        	da.runDocAdmin(docId);
			        	frame.dispose();
			        }
			        
			        
				}
				catch (SQLException ex) {
		            // TODO Auto-generated catch block
		            ex.printStackTrace();
		    }
			}
		});
		btnAdminRights.setBounds(421, 221, 117, 25);
		frame.getContentPane().add(btnAdminRights);
		
		JButton btnViewHistory = new JButton("View History");
		btnViewHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DocViewHistory dvh = new DocViewHistory();
				dvh.runDocViewHistory(docId);
			}
		});
		btnViewHistory.setBounds(421, 73, 117, 25);
		frame.getContentPane().add(btnViewHistory);
		
		
		
		table_1 = new JTable();
		JScrollPane scrollPane_1 = new JScrollPane();
		//table_1.setBounds(27, 87, 197, 180);
		scrollPane_1.setBounds(33, 75, 314, 134);
		frame.getContentPane().add(scrollPane_1);
		
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane_1.setViewportView(table_1);
        
        JButton btnNewButton_3 = new JButton("Sort");
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
                	DataBase dbConnection = new DataBase();
        	        con = dbConnection.getConnection();
        	        stmt = con.createStatement();
        	        String searchQuery = "SELECT * from history where status ='active' and docID='" + docId + "'" +"order by patID";
        	        ResultSet rs = (ResultSet) stmt.executeQuery(searchQuery);
                    table_1.setModel(DbUtils.resultSetToTableModel(rs));
        	        con.close();
                }
                catch (Exception ex) {
                    // TODO Auto-generated catch block
                    ex.printStackTrace();
            }
        	}
        });
        
        btnNewButton_3.setBounds(421, 322, 117, 25);
        frame.getContentPane().add(btnNewButton_3);
        try {
        	DataBase dbConnection = new DataBase();
	        con = dbConnection.getConnection();
	        stmt = con.createStatement();
	        String searchQuery = "SELECT * from history where docID='" + docId + "'" +"and newly_assigned = " + "'yes'" ;
	        ResultSet rs = (ResultSet) stmt.executeQuery(searchQuery);
            table_1.setModel(DbUtils.resultSetToTableModel(rs));
	        con.close();

        }
        catch (Exception ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
    }
        
		
	}	
	}
