/*
 * Project Name: Smart HealthCare System
 * Team Members:
 * Saswati Hazra MT18015
 * Vishal MT18019
 * Sujay Raj MT18108*/

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class HomePage {

    private JFrame frame;
    HomePage window;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        //EventQueue.invokeLater(new Runnable() {
        HomePage window = new HomePage();
        window.runHomePage();
        //});

    }
    public void runHomePage() {
        try {
            this.frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Create the application.
     */
    public HomePage() {
        initialize();
    }
    /**
     * Initialize the contents of the frame.
     */
    public void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(UIManager.getColor("PopupMenu.background"));
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblWelcome = new JLabel("Welcome to Smart HealthCare System");
        lblWelcome.setForeground(Color.BLUE);
        lblWelcome.setBounds(134, 62, 249, 30);
        frame.getContentPane().add(lblWelcome);




        JButton btnNewButton_1 = new JButton("Login");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LogInForm lForm;
                lForm = new LogInForm();
                lForm.runLogin();
                //window.frame.setVisible(false);
                frame.dispose();
            }
        });
        btnNewButton_1.setBounds(281, 139, 117, 25);
        frame.getContentPane().add(btnNewButton_1);


        JRadioButton rdbtnNewRadioButton = new JRadioButton("Doctor");
        rdbtnNewRadioButton.setBounds(71, 176, 149, 23);
        frame.getContentPane().add(rdbtnNewRadioButton);
        rdbtnNewRadioButton.setVisible(false);




        JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Patient");
        rdbtnNewRadioButton_1.setBounds(71, 229, 149, 23);
        frame.getContentPane().add(rdbtnNewRadioButton_1);
        rdbtnNewRadioButton_1.setVisible(false);

        JButton btnNewButton = new JButton("Sign Up");
        btnNewButton.setBounds(91, 139, 117, 25);
        frame.getContentPane().add(btnNewButton);

        JButton btnProceed = new JButton("Proceed");
        btnProceed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                DoctorRegForm dForm;
                if (rdbtnNewRadioButton.isSelected()) {
                    dForm = new DoctorRegForm();
                    dForm.runDocRegForm();
                    frame.dispose();
                } else if (rdbtnNewRadioButton_1.isSelected()) {
                	PatientRegForm pForm = new PatientRegForm();
                    pForm.runPatientRegForm(1);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an option");
                }

            }
        });
        btnProceed.setBounds(281, 228, 117, 25);
        frame.getContentPane().add(btnProceed);
        btnProceed.setVisible(false);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rdbtnNewRadioButton.setVisible(true);
                rdbtnNewRadioButton_1.setVisible(true);
                btnProceed.setVisible(true);
            }
        });
        ButtonGroup group = new ButtonGroup();
        group.add(rdbtnNewRadioButton);
        group.add(rdbtnNewRadioButton_1);
    }
}