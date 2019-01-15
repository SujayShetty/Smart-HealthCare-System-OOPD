import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.SystemColor;

public class AdminLogged {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
			public void runAdminLogged() {
				try {
					AdminLogged window = new AdminLogged();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	

	/**
	 * Create the application.
	 */
	public AdminLogged() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaption);
		frame.getContentPane().setForeground(SystemColor.textHighlight);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Add doctor");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminDoctorRegForm form = new AdminDoctorRegForm();
				form.runDocRegForm();
				frame.dispose();
			}
		});
		btnNewButton.setBounds(26, 51, 130, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("View doctors");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminViewDoctors form = new AdminViewDoctors();
				form.runAdminViewDoc();
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(251, 51, 130, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("View patients");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminViewPatients form = new AdminViewPatients();
				form.runAdminViewPat();
				frame.dispose();
			}
		});
		btnNewButton_2.setBounds(251, 97, 130, 25);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Logout");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePage form = new HomePage();
				form.runHomePage();
				frame.dispose();
			}
		});
		btnNewButton_3.setBounds(321, 225, 103, 25);
		frame.getContentPane().add(btnNewButton_3);
		
		JLabel lblWelcome = new JLabel("WELCOME TO ADMINISTRATORS PAGE");
		lblWelcome.setForeground(Color.BLUE);
		lblWelcome.setBounds(95, 11, 255, 14);
		frame.getContentPane().add(lblWelcome);
		
		JButton btnAddDep = new JButton("Add Department");
		btnAddDep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				AdminAddDep form = new AdminAddDep();
				form.runAdminAddDep();
				frame.dispose();
			}
		});
		btnAddDep.setBounds(26, 98, 130, 23);
		frame.getContentPane().add(btnAddDep);
		
		JButton btnReassignDocsTo = new JButton("Reassign Docs to Patient");
		btnReassignDocsTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminReassignDocsToPat form = new AdminReassignDocsToPat();
				form.runAdminReassignDocsToPat();
				frame.dispose();
			}
		});
		btnReassignDocsTo.setBounds(96, 191, 237, 23);
		frame.getContentPane().add(btnReassignDocsTo);
		
		JButton btnDeleteDocpatdep = new JButton("Delete Doc/Pat/Dep");
		btnDeleteDocpatdep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminDeleteUsers form = new AdminDeleteUsers();
				form.runAdminDeleteUsers();
				frame.dispose();				
			}
		});
		btnDeleteDocpatdep.setBounds(26, 138, 237, 23);
		frame.getContentPane().add(btnDeleteDocpatdep);
		
	}
}
