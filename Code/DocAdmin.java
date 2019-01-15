import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.SystemColor;

public class DocAdmin {

	private JFrame frame;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
			public void runDocAdmin(String docID) {
				try {
					DocAdmin window = new DocAdmin(docID);
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
	public DocAdmin(String docID)
	{
		initialize(docID);
	}
	public DocAdmin() {
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String docID) {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaption);
		frame.getContentPane().setForeground(SystemColor.textHighlight);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Add doctor");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DocDoctorRegForm form = new DocDoctorRegForm();
				form.runDocRegForm(docID);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(111, 49, 130, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("View doctor");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DocViewDoctors form = new DocViewDoctors();
				form.runDocViewDoc(docID);
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(111, 97, 130, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("View patient");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DocViewPatients form = new DocViewPatients();
				form.runAdminViewPat(docID);
				frame.dispose();
			}
		});
		btnNewButton_2.setBounds(111, 146, 130, 25);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Back");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DocLogged form = new DocLogged();
				form.runDocLogged(docID);
				frame.dispose();
			}
		});
		btnNewButton_3.setBounds(321, 225, 103, 25);
		frame.getContentPane().add(btnNewButton_3);
		
		JLabel lblWelcome = new JLabel("WELCOME TO ADMINISTRATORS PAGE");
		lblWelcome.setForeground(Color.BLUE);
		lblWelcome.setBounds(95, 11, 255, 14);
		frame.getContentPane().add(lblWelcome);
		btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		DocLogged hForm = new DocLogged();
                hForm.runDocLogged(docID);
                frame.dispose();
        	}
        });
		
	}
}

