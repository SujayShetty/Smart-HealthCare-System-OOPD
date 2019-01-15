import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.SystemColor;

public class PatLogged {


    private JFrame frame;
    JLabel lblTesting;

    public void runPatLogged(String patID) {
        try {
            PatLogged window = new PatLogged(patID);
            window.frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @wbp.parser.entryPoint
     */
    public PatLogged(String patID) {
        //if(!patID.equals("null"))
        initialize(patID);
    }

    public PatLogged() {
        // TODO Auto-generated constructor stub
    }

    private void initialize(String patID) {
        System.out.println("HERE!!!!");
        frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.inactiveCaption);
        frame.setBounds(100, 100, 538, 469);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnNewButton = new JButton("Search Doctor");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		PatSearchDoc searchForm = new PatSearchDoc();
        		searchForm.runSearchDoc(patID);
        		frame.dispose();
        	}
        });
        btnNewButton.setBounds(185, 71, 117, 25);
        frame.getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Logout");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		HomePage hForm = new HomePage();
        		hForm.runHomePage();
        		frame.dispose();
        	}
        });
        btnNewButton_1.setBounds(185, 286, 117, 25);
        frame.getContentPane().add(btnNewButton_1);

        JButton btnViewProfile = new JButton("View Profile");
        btnViewProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PatientViewProfile viewProfile = new PatientViewProfile();
                viewProfile.runPatView(patID);
                frame.dispose();
            }
        });
        JButton btnEditProfile = new JButton("Edit Profile");
		btnEditProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PatientEditProfile updatePat = new PatientEditProfile();
				updatePat.runPatUpdate(patID);
				frame.dispose();
			}
		});
		btnEditProfile.setBounds(185, 231, 117, 25);
		frame.getContentPane().add(btnEditProfile);
        btnViewProfile.setBounds(185, 135, 117, 25);
        frame.getContentPane().add(btnViewProfile);

        lblTesting = new JLabel("Testing ");
        lblTesting.setBounds(231, 11, 125, 14);
        frame.getContentPane().add(lblTesting);
        lblTesting.setText(patID);

        JLabel lblWelcome = new JLabel("WELCOME");
        lblWelcome.setBounds(163, 11, 94, 14);
        frame.getContentPane().add(lblWelcome);
        
        JButton btnViewHistory = new JButton("View History");
        btnViewHistory.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		PatViewHistory pHistory= new PatViewHistory();
        		pHistory.runPatViewHistory(patID);
        		frame.dispose();
        	}
        });
        btnViewHistory.setBounds(185, 185, 117, 23);
        frame.getContentPane().add(btnViewHistory);
        
        
    }
}