package guis;

import dataAccess.account;
import dataAccess.customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AccountSelectionGui extends JFrame {
    private final customer user;
    private JComboBox<account> accountComboBox;
    private JButton selectButton;
    private JLabel footerLabel;

    public AccountSelectionGui(customer user) {
        this.user = user;
        initialize();
    }

    private void initialize() {
        setTitle("Select Account");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout(10, 10)); // Add padding between components

        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel selectAccountLabel = new JLabel("Select an Account:");
        selectAccountLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        selectAccountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(selectAccountLabel, BorderLayout.CENTER);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(headerPanel, BorderLayout.NORTH);

        // Center panel for combo box
        List<account> accounts = user.getAccounts();
        accountComboBox = new JComboBox<>(accounts.toArray(new account[0]));
        accountComboBox.setFont(new Font("Dialog", Font.PLAIN, 16));
        accountComboBox.setRenderer(new AccountCellRenderer());
        accountComboBox.setMaximumRowCount(5);
        accountComboBox.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        add(accountComboBox, BorderLayout.CENTER);

        // Footer panel for the select button
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        selectButton = new JButton("Select");
        selectButton.setFont(new Font("Dialog", Font.BOLD, 16));
        selectButton.setBackground(new Color(0x4CAF50)); // Green background
        selectButton.setForeground(Color.WHITE);
        selectButton.setFocusPainted(false);
        selectButton.setPreferredSize(new Dimension(150, 40));
        selectButton.addActionListener(new SelectButtonActionListener());
        footerPanel.add(selectButton);

        // Footer label for instructions
        footerLabel = new JLabel("Choose an account and click Select.");
        footerLabel.setFont(new Font("Dialog", Font.ITALIC, 14));
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        footerPanel.add(footerLabel);

        add(footerPanel, BorderLayout.SOUTH);
    }

    private class AccountCellRenderer extends JLabel implements ListCellRenderer<account> {
        @Override
        public Component getListCellRendererComponent(JList<? extends account> list, account value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value != null) {
                setText("Balance: $" + String.format("%.2f", value.getBalance()));
            } else {
                setText("Unknown Account");
            }
            setFont(new Font("Dialog", Font.PLAIN, 16));
            setOpaque(true);
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            return this;
        }
    }

    private class SelectButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            account selectedAccount = (account) accountComboBox.getSelectedItem();
            if (selectedAccount != null) {
                new BankingAppGui(user, selectedAccount).setVisible(true);
                dispose(); // Close this window
            } else {
                // Corrected code: Use the instance of AccountSelectionGui as the parent component
                JOptionPane.showMessageDialog(AccountSelectionGui.this, "Please select an account.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
