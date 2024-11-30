package guis;

import dataAccess.account;
import dataAccess.customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountSelectionGui extends JFrame implements ActionListener {
    private customer user;
    private JComboBox<account> accountComboBox;
    private JButton selectButton;

    public AccountSelectionGui(customer user) {
        this.user = user;
        setTitle("Select Account");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel selectAccountLabel = new JLabel("Select an Account:");
        selectAccountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(selectAccountLabel, BorderLayout.NORTH);

        accountComboBox = new JComboBox<>(user.getAccounts().toArray(new account[0]));
        add(accountComboBox, BorderLayout.CENTER);

        selectButton = new JButton("Select");
        selectButton.addActionListener(this);
        add(selectButton, BorderLayout.SOUTH);
    }

    @Override
public void actionPerformed(ActionEvent e) {
    account selectedAccount = (account) accountComboBox.getSelectedItem();
    if (selectedAccount != null) {
        new BankingAppGui(user, selectedAccount).setVisible(true);
        this.dispose();
    } else {
        JOptionPane.showMessageDialog(this, "Please select an account.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
}