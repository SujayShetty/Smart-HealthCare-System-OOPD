import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.mysql.cj.protocol.Resultset;

import net.proteanit.sql.DbUtils;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class PatSearchDoc {

    private JFrame frame;
    private JTextField textField;
    private JTable table;
    private JTable table_1;
    static Connection con;
    static Statement stmt;
    JRadioButton rdbtnAutomatically;
    JRadioButton rdbtnManually;
    JLabel lblAssignDoctor;
    JButton btnAssign;
    private JTextField textAssign;
    JLabel lblEnterCorrectDocid;
    String dep;
    String isCritical;
    JLabel lblTiming;
    ResultSet rs;
    String checkIfAlreadyAssigned;
    boolean foundAssignedDoc = false;
    private JLabel lblDoctorAssigned;
    private JLabel lblDocid;


    /**
     * Launch the application.
     */

    public void runSearchDoc(String patID) {
        try {
            PatSearchDoc window = new PatSearchDoc(patID);
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
    public PatSearchDoc(String patID) {
        initialize(patID);
    }

    public PatSearchDoc() {
        // TODO Auto-generated constructor stub
    }


    /**
     * Initialize the contents of the frame.
     */
    private void initialize(String patID) {
    	LoggerClass lc = new LoggerClass();
    	Logger logger = lc.loggerMethod();
        DataBase dbConnection = new DataBase();
        con = dbConnection.getConnection();

        frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.inactiveCaption);
        frame.setBounds(100, 100, 750, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        JRadioButton rdbtnDocid = new JRadioButton("DocID");
        rdbtnDocid.setBounds(43, 23, 109, 23);
        frame.getContentPane().add(rdbtnDocid);

        JRadioButton rdbtnAddress = new JRadioButton("Address");
        rdbtnAddress.setBounds(43, 49, 109, 23);
        frame.getContentPane().add(rdbtnAddress);

        JRadioButton rdbtnName = new JRadioButton("Name");
        rdbtnName.setBounds(43, 75, 109, 23);
        frame.getContentPane().add(rdbtnName);

        JRadioButton rdbtnSpecialization = new JRadioButton("Specialization");
        rdbtnSpecialization.setBounds(43, 102, 109, 23);
        frame.getContentPane().add(rdbtnSpecialization);

        JRadioButton rdbtnSearchAll = new JRadioButton("Search All");
        rdbtnSearchAll.setBounds(43, 131, 109, 23);
        frame.getContentPane().add(rdbtnSearchAll);

        textField = new JTextField();
        textField.setBounds(53, 159, 86, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean set = true;
                try {
                    //stmt = con.createStatement();
                    String optionSearch = null;
                    if (rdbtnAddress.isSelected())
                        optionSearch = "address";
                    else if (rdbtnDocid.isSelected())
                        optionSearch = "docID";
                    else if (rdbtnName.isSelected())
                        optionSearch = "name";
                    else if (rdbtnSpecialization.isSelected())
                        optionSearch = "department";
                    else if (rdbtnSearchAll.isSelected())
                        optionSearch = "all";
                    else {
                        JOptionPane.showMessageDialog(null, "Please select an option");
                        logger.info("search selection hasn't entered");
                        set = false;
                    }
                    
                    if (set) {
                    	//if foundAssignedDoc is true don't show assigned buttons as patient can only assign once
                    	if(!foundAssignedDoc) {
                        rdbtnAutomatically.setVisible(true);
                        rdbtnManually.setVisible(true);
                        lblAssignDoctor.setVisible(true);
                        btnAssign.setVisible(true);
                        textAssign.setVisible(true);
                    	}
                        String search = textField.getText();
                        String searchQuery;
                        ResultSet rs;
                        String getDep = "SELECT department from patient where patID='" + patID + "'";
                        String isCriticalQuery = "SELECT critical from patient where patID='" + patID + "'";
                        rs = stmt.executeQuery(getDep);
                        rs.next();
                        dep = rs.getString(1);
                        rs = stmt.executeQuery(isCriticalQuery);
                        rs.next();
                        isCritical = rs.getString(1);
                        System.out.println(dep);
                        if (optionSearch.equals("all"))
                            searchQuery = "SELECT * from doctor where department='" + dep + "'";
                        else
                            searchQuery = "SELECT * from doctor where " + optionSearch + " =" + "'" + search + "' and department='" + dep + "'";

                        rs = stmt.executeQuery(searchQuery);

                        table_1.setModel(DbUtils.resultSetToTableModel(rs));
                        rs = stmt.executeQuery(searchQuery); //Executing twice to reset rs to initial pos
                        if (!rs.next()) {
                            rdbtnAutomatically.setVisible(false);
                            rdbtnManually.setVisible(false);
                            lblAssignDoctor.setVisible(false);
                            btnAssign.setVisible(false);
                            textAssign.setVisible(false);
                            JOptionPane.showMessageDialog(null, "No results found");
                            logger.info("No SELECT * from doctor found during search");
                        }
                        //con.close();
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, "No results found");
                    logger.info(e1.getMessage());
                };
            }
        });
        btnSearch.setBounds(50, 190, 89, 23);
        frame.getContentPane().add(btnSearch);


        ButtonGroup group = new ButtonGroup();
        group.add(rdbtnAddress);
        group.add(rdbtnDocid);
        group.add(rdbtnSpecialization);
        group.add(rdbtnName);
        group.add(rdbtnSearchAll);

        JLabel lblSelectAFiled = new JLabel("Select a field and search for a doctor");
        lblSelectAFiled.setBounds(43, 2, 230, 14);
        frame.getContentPane().add(lblSelectAFiled);

        table_1 = new JTable();
        //table_1.setBounds(73, 207, 353, 203);       


        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(231, 26, 351, 134);
        frame.getContentPane().add(scrollPane_1);
        scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane_1.setViewportView(table_1);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                PatLogged pForm = new PatLogged();
                pForm.runPatLogged(patID);
                try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					logger.info(e.getMessage());
					e.printStackTrace();
				}
                frame.dispose();
            }
        });
        btnBack.setBounds(516, 282, 89, 23);
        frame.getContentPane().add(btnBack);

        btnAssign = new JButton("Assign Doctor ");
        btnAssign.setVisible(false);
        btnAssign.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textAssign.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter timing/docID in the textbox");
                } else {

                    if (foundAssignedDoc) {
                        //JOptionPane.showMessageDialog(null,"Already "+rs.getString(1)+" is assigned for you :)");
                        rdbtnAutomatically.setVisible(false);
                        rdbtnManually.setVisible(false);
                        lblAssignDoctor.setVisible(false);
                        btnAssign.setVisible(false);
                        textAssign.setVisible(false);
                    } else {
                        if (rdbtnManually.isSelected()) {
                            //Pat enters docID and assigns himself
                            if (textAssign.getText().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Please enter doctor id");
                                logger.info("Please enter doctor id");
                            }
                            else {
                                String docIDtoAssign = textAssign.getText();
                                String checkDocID = "Select * from doctor where docId='" + docIDtoAssign + "' and department ='"+dep+"'";
                                try {
                                    rs = stmt.executeQuery(checkDocID);

                                    if (!rs.next()) {
                                        lblEnterCorrectDocid.setVisible(true);
                                        JOptionPane.showMessageDialog(null, "No doctor found with the entered docid");
                                        logger.info("No doctor found with the entered docid");
                                    } else {
                                        //Set status to active and newly_assigned to yes
                                        String sql = "INSERT INTO `history`(`docID`, `patID`,`status`,`newly_assigned`) VALUES ('" + textAssign.getText() + "','" + patID + "','active','yes')";
                                        //String sql2 = "INSERT INTO `newly_assigned`(`docID`, `patID`) VALUES ('"+textAssign.getText()+"','"+patID+"')";
                                        stmt.executeUpdate(sql);
                                        //stmt.executeUpdate(sql2);
                                        lblEnterCorrectDocid.setForeground(Color.GREEN);
                                        JOptionPane.showMessageDialog(null, "Successfully assigned :)");
                                    }
                                } catch (SQLException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                    logger.info(e1.getMessage());
                                }
                            }
                        } else if (rdbtnAutomatically.isSelected()) {
                            //Automatic assignment
                            //Based on the timing entered by the user and
                            //the department he is in our system selects the doctor
                            //Surgeon is assigned if type of pat is critical
                            //dep and  type is used
                            lblTiming.setVisible(true);
                            String timing = textAssign.getText();
                            java.util.Date date = null;
                            //date.setHours(date.getHours() + 8);
                            DateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.UK); //24 Hour format
                            try {
                                date = sdf.parse(timing);
                            } catch (ParseException e1) {
                                // TODO Auto-generated catch block
                                //e1.printStackTrace();
                            	logger.info(e1.getMessage());
                            }

                            Boolean gotSurgeon = false;
                            if (isCritical.equals("Yes")) {
                                //Check for surgeon if available 
                                //String checkSurgeon ="Select * from doctor where category='surgeon' and timeFrom='"+sdf.format(date)+"'";
                                //Search surgeon in the available time range
                                String checkSurgeon = "Select * from doctor where category='surgeon' and department = '"+dep+"' and timeFrom<= '" + sdf.format(date) + "'and timeTo>='" + sdf.format(date) + "'";
                                try {
                                    rs = stmt.executeQuery(checkSurgeon);

                                    if (rs.next()) {
                                        gotSurgeon = true;
                                        System.out.println("Got surgeon");
                                        //System.out.println("Here 2");
                                        rs = stmt.executeQuery(checkSurgeon);
                                        rs.next();
                                        String docID = rs.getString(2);
                                        //Here docId is second index and set newly_visited to yes
                                        String sql = "INSERT INTO `history`(`docID`, `patID`,`status`,`newly_assigned`) VALUES ('" + docID + "','" + patID + "','active','yes')";
                                        //String sql2 = "INSERT INTO `newly_assigned`(`docID`, `patID`) VALUES ('"+docID+"','"+patID+"')";
                                        stmt.executeUpdate(sql);
                                        //stmt.executeUpdate(sql2);
                                        lblTiming.setVisible(false);
                                        lblEnterCorrectDocid.setVisible(true);
                                        lblEnterCorrectDocid.setText("Successfully assigned :)" + docID);
                                        lblEnterCorrectDocid.setForeground(Color.GREEN);
                                        JOptionPane.showMessageDialog(null, "Successfully assigned :)" + docID);
                                        PatSearchDoc nForm = new PatSearchDoc();
                                        nForm.runSearchDoc(patID);
                                        frame.dispose();
                                    } else
                                        System.out.println("Dint get surgeon");
                                } catch (SQLException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                    logger.info(e1.getMessage());
                                }
                            }
                            if (!gotSurgeon) {
                                System.out.println("Here 1");
                                System.out.println(sdf.format(date));
                                String checkAnyDoc = "Select * from doctor where department = '"+dep+"' and timeFrom<='" + sdf.format(date) + "' and timeTo>='" + sdf.format(date) + "'";
                                //String checkAnyDoc ="Select * from doctor where timeFrom <= '"+sdf.format(date)+"'";

                                try {
                                    rs = stmt.executeQuery(checkAnyDoc);


                                    if (!rs.next()) {
                                        JOptionPane.showMessageDialog(null, "No available docs during this time ");
                                        logger.info("No available docs during this time ");
                                    }else {
                                        System.out.println("Here 2");
                                        rs = stmt.executeQuery(checkAnyDoc);
                                        rs.next();
                                        String docID = rs.getString(2);
                                        //Here docId is second index and set newly_visited to yes
                                        String sql = "INSERT INTO `history`(`docID`, `patID`,`status`,`newly_assigned`) VALUES ('" + docID + "','" + patID + "','active','yes')";
                                        //String sql2 = "INSERT INTO `newly_assigned`(`docID`, `patID`) VALUES ('"+docID+"','"+patID+"')";
                                        stmt.executeUpdate(sql);
                                        //stmt.executeUpdate(sql2);
                                        lblTiming.setVisible(false);
                                        lblEnterCorrectDocid.setVisible(true);
                                        lblEnterCorrectDocid.setText("Successfully assigned :)" + docID);
                                        lblEnterCorrectDocid.setForeground(Color.GREEN);
                                        JOptionPane.showMessageDialog(null, "Successfully assigned :)" + docID);
                                        PatSearchDoc nForm = new PatSearchDoc();
                                        nForm.runSearchDoc(patID);
                                        frame.dispose();
                                    }
                                } catch (SQLException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                    logger.info(e1.getMessage());
                                }
                            }
                            //String c ="Select * from doctor where docId='"+docIDtoAssign+"'";
                        } else
                            JOptionPane.showMessageDialog(null, "Please select either Manual/Automatic assign option");

                    }

                }

            }

        });
        btnAssign.setBounds(84, 347, 213, 23);
        frame.getContentPane().add(btnAssign);
        //btnAssign.

        rdbtnManually = new JRadioButton("Manually");
        rdbtnManually.setBounds(53, 269, 109, 23);
        frame.getContentPane().add(rdbtnManually);
        rdbtnManually.setVisible(false);

        rdbtnAutomatically = new JRadioButton("Automatically");
        rdbtnAutomatically.setBounds(164, 269, 109, 23);
        frame.getContentPane().add(rdbtnAutomatically);
        rdbtnAutomatically.setVisible(false);

        ButtonGroup group1 = new ButtonGroup();
        group1.add(rdbtnManually);
        group1.add(rdbtnAutomatically);

        lblAssignDoctor = new JLabel("Assign Doctor");
        lblAssignDoctor.setBounds(43, 248, 134, 14);
        frame.getContentPane().add(lblAssignDoctor);

        textAssign = new JTextField();
        textAssign.setBounds(180, 315, 86, 20);
        frame.getContentPane().add(textAssign);
        textAssign.setColumns(10);

        lblEnterCorrectDocid = new JLabel("Enter correct docid");
        lblEnterCorrectDocid.setForeground(Color.RED);
        lblEnterCorrectDocid.setBounds(287, 318, 213, 14);
        frame.getContentPane().add(lblEnterCorrectDocid);

        lblTiming = new JLabel("Timing(hh:mm:ss)");
        lblTiming.setForeground(Color.RED);
        lblTiming.setBounds(41, 318, 111, 14);
        frame.getContentPane().add(lblTiming);
        
        lblDoctorAssigned = new JLabel("Doctor Assigned :");
        lblDoctorAssigned.setBounds(287, 2, 134, 14);
        frame.getContentPane().add(lblDoctorAssigned);
        
        lblDocid = new JLabel("docID");
        lblDocid.setForeground(Color.GREEN);
        lblDocid.setBounds(431, 2, 124, 14);
        frame.getContentPane().add(lblDocid);
        lblEnterCorrectDocid.setVisible(false);
        lblTiming.setVisible(false);

        textAssign.setVisible(false);
        lblAssignDoctor.setVisible(false);
        //Check for assigned doctor
        try {
            stmt = con.createStatement();

            checkIfAlreadyAssigned = "Select * from history where patID='" + patID + "'";
            rs = stmt.executeQuery(checkIfAlreadyAssigned);
            while (rs.next()) {
                System.out.println("Result :" + rs.getString("status"));
                try {
                    if (rs.getString("status").equals("active")) {
                    	System.out.println("Am I here?");
                        //If some results found and patID status is active
                        foundAssignedDoc = true;
                        break;
                    }
                } catch (Exception e2) {
                    //foundAssignedDoc= false;
                    //If some exception just ignore that
                	logger.info(e2.getMessage());
                }
            }
            if(foundAssignedDoc) 
            	lblDocid.setText(rs.getString(1));
            else {
            	lblDoctorAssigned.setText("No doctor assigned");
            	lblDocid.setVisible(false);
            }
            
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            //JOptionPane.showMessageDialog(null, Please )
            //lblTiming.setVisible(true);
            e1.printStackTrace();
            logger.info(e1.getMessage());
        }
        //group

    }
}