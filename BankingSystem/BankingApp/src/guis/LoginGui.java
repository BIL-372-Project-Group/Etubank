package guis;

import javax.swing.*;

import dataAccess.DataAccessLayer;
import dataAccess.customer;

import java.awt.*;
import java.awt.event.*;

public class LoginGui extends BaseFrame{

    public LoginGui() {
        super("Banking Application");
    }

    @Override
    protected void addGuiComponents() {
        //banking app label
        JLabel bankingAppLabel = new JLabel("Banking Application");

        //setting the labels location
        bankingAppLabel.setBounds(0,20,super.getWidth(),40);

        //font changes
        bankingAppLabel.setFont(new Font("Dialog", Font.BOLD, 32));

        //center text
        bankingAppLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(bankingAppLabel);


        //email label
        JLabel emailLabel = new JLabel("Email:");

        emailLabel.setBounds(20,120,getWidth()-30,24);

        emailLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(emailLabel);


        //create email field

        JTextField emailField = new JTextField();
        emailField.setBounds(20,160,getWidth()-50,40);
        emailField.setFont(new Font("Dialog", Font.PLAIN, 28));
        add(emailField);

        //create password label
        JLabel passwordLabel = new JLabel("Password:");

        passwordLabel.setBounds(20,230,getWidth()-50,24);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(passwordLabel);

        //create password Field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(20,270,getWidth()-50,40);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 28));
        add(passwordField);

        //create login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(20,460,getWidth()-50,40);
        passwordField.setFont(new Font("Dialog", Font.BOLD, 20));
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();

                String password = String.valueOf(passwordField.getPassword());

                customer user = DataAccessLayer.createSession(email, password);

                if(user != null) {
                    LoginGui.this.dispose();

                    BankingAppGui bankingAppGui = new BankingAppGui(user);
                    bankingAppGui.setVisible(true);

                    JOptionPane.showMessageDialog(bankingAppGui, "Login Successful");
                } else {
                    JOptionPane.showMessageDialog(LoginGui.this, "Invalid email or Password");
                }
            }
        });
        add(loginButton);

        //create register Label
        JLabel registerLabel = new JLabel("<html><a href=\"#\"> Don't have an account? Register Here</a></html>");
        registerLabel.setBounds(0,510,getWidth()-10,30);
        registerLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginGui.this.dispose();

                //new RegisterGui().setVisible(true);
            }
        });
        add(registerLabel);




    }
}
