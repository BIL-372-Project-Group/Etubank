package guis;

import javax.swing.*;
import dataAccess.DataAccessLayer;
import dataAccess.customer;
import java.awt.*;
import java.awt.event.*;

public class LoginGui extends BaseFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginGui() {
        super("Banking Application");
    }

    @Override
    protected void addGuiComponents() {
        setLayout(null); // Use a null layout for custom positioning

        // Title label
        JLabel bankingAppLabel = createLabel("Banking Application", 32, 0, 20, getWidth(), 40, SwingConstants.CENTER);
        add(bankingAppLabel);

        // Email label and field
        JLabel emailLabel = createLabel("Email:", 20, 20, 120, getWidth() - 30, 24, SwingConstants.LEFT);
        add(emailLabel);
        emailField = createTextField(20, 160, getWidth() - 50, 40, 28);
        add(emailField);

        // Password label and field
        JLabel passwordLabel = createLabel("Password:", 20, 20, 230, getWidth() - 50, 24, SwingConstants.LEFT);
        add(passwordLabel);
        passwordField = createPasswordField(20, 270, getWidth() - 50, 40, 28);
        add(passwordField);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(20, 460, getWidth() - 50, 40);
        loginButton.setFont(new Font("Dialog", Font.BOLD, 20));
        loginButton.addActionListener(new LoginActionListener());
        add(loginButton);

        // Register link
        JLabel registerLabel = new JLabel("<html><a href=\"#\">Don't have an account? Register Here</a></html>");
        registerLabel.setBounds(0, 510, getWidth() - 10, 30);
        registerLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.addMouseListener(new RegisterMouseListener());
        add(registerLabel);
    }

    // Helper methods to create UI components with consistent properties
    private JLabel createLabel(String text, int fontSize, int x, int y, int width, int height, int alignment) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Dialog", Font.PLAIN, fontSize));
        label.setHorizontalAlignment(alignment);
        return label;
    }

    private JTextField createTextField(int x, int y, int width, int height, int fontSize) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setFont(new Font("Dialog", Font.PLAIN, fontSize));
        return textField;
    }

    private JPasswordField createPasswordField(int x, int y, int width, int height, int fontSize) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(x, y, width, height);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, fontSize));
        return passwordField;
    }

    // Action listener for the login button
    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            customer user = DataAccessLayer.createSession(email, password);

            if (user != null) {
                dispose();
                new AccountSelectionGui(user).setVisible(true);
                JOptionPane.showMessageDialog(null, "Login Successful");
            } else {
                JOptionPane.showMessageDialog(LoginGui.this, "Invalid email or password");
            }
        }
    }

    // Mouse listener for the register link
    private class RegisterMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            dispose();
            //new RegisterGui().setVisible(true);
        }
    }
}
