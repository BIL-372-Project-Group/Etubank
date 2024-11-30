package guis;

import dataAccess.DataAccessLayer;
import dataAccess.account;
import dataAccess.customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class BankingAppDialog extends JDialog implements ActionListener {
    private customer user;
    private account selectedAccount;
    private BankingAppGui bankingAppGui;
    private JLabel balanceLabel, enterAmountLabel, enterUserLabel;
    private JTextField enterAmountField, enterUserField;
    private JButton actionButton;
    private JPanel pastTransactionPanel;

    public BankingAppDialog(BankingAppGui bankingAppGui, customer user, account selectedAccount) {
        this.bankingAppGui = bankingAppGui;
        this.user = user;
        this.selectedAccount = selectedAccount;
        setSize(400,400);

        //addingFocus  to the dialog. Cannot interact with anything until the dialog is closed.
        setModal(true);
        //loads in the center of bankingapp gui
        setLocationRelativeTo(bankingAppGui);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setResizable(false);

        setLayout(null);
    }

    public void addCurrentBalanceAndAmount() {
        balanceLabel = new JLabel("Balance: $" + selectedAccount.getBalance());
        balanceLabel.setBounds(0,10,getWidth()-20,20);
        balanceLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(balanceLabel);


        enterAmountLabel=  new JLabel("Enter Amount:");
        enterAmountLabel.setBounds(0,50,getWidth()-20,20);
        enterAmountLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        enterAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterAmountLabel);

        enterAmountField =  new JTextField();
        enterAmountField.setBounds(15,80,getWidth() - 50, 40);
        enterAmountField.setFont(new Font("Dialog", Font.PLAIN, 40));
        enterAmountField.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterAmountField);

    }

    public void addActionButton(String actionButtonType){
        actionButton = new JButton(actionButtonType);
        actionButton.setBounds(15,300,getWidth()-50,40);
        actionButton.setFont(new Font("Dialog", Font.BOLD, 20));
        actionButton.setHorizontalAlignment(SwingConstants.CENTER);
        actionButton.addActionListener(this);
        add(actionButton);
    }

    public void addUserField() {
        //enter user label
        enterUserLabel =  new JLabel("Enter User:");
        enterUserLabel.setBounds(0,130,getWidth()-20,20);
        enterUserLabel.setFont(new Font("Dialog", Font.ROMAN_BASELINE, 20));
        enterUserLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterUserLabel);

        //enter user field
        enterUserField =  new JTextField();
        enterUserField.setBounds(15,160,getWidth()-50, 40);
        enterUserField.setFont(new Font("Dialog", Font.PLAIN, 40));
        enterUserField.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterUserField);
    }

    public void addPastTransactionComponents() {
        //container that will store the transaction
        pastTransactionPanel = new JPanel();

        pastTransactionPanel.setLayout(new BoxLayout(pastTransactionPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(pastTransactionPanel);
        //display vertical scroll panel when needed
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        scrollPane.setBounds(0,20,getWidth()-15,getHeight()-20);

        add(scrollPane);
    }

    private void handleTransaction(String transactionType, float amountVal) {
        BigDecimal amount = BigDecimal.valueOf(amountVal);
        selectedAccount.handleTransaction(transactionType, amount);
        resetFieldsAndUpdateCurrentBalance();
    }

    private void handleTransfer(String transferredUserIBAN, float amount) {
        account recipientAccount = DataAccessLayer.getAccountByIBAN(transferredUserIBAN);
        if (recipientAccount == null) {
            JOptionPane.showMessageDialog(this, "Transfer Error: User not found.");
            return;
        }
        BigDecimal amountBD = BigDecimal.valueOf(amount);
        try {
            selectedAccount.handleTransfer(recipientAccount, amountBD);
            resetFieldsAndUpdateCurrentBalance();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void resetFieldsAndUpdateCurrentBalance() {
        enterAmountField.setText("");
        if (enterUserField != null) {
            enterUserField.setText("");
        }
        balanceLabel.setText("Balance: $" + selectedAccount.getBalance());
        bankingAppGui.getCurrentBalanceField().setText("$" + selectedAccount.getBalance());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand();
        float amountVal = Float.parseFloat(enterAmountField.getText());
        if (buttonPressed.equalsIgnoreCase("Deposit") || buttonPressed.equalsIgnoreCase("Withdraw")) {
            handleTransaction(buttonPressed, amountVal);
        } else if (buttonPressed.equalsIgnoreCase("Transfer")) {
            String transferredUser = enterUserField.getText();
            handleTransfer(transferredUser, amountVal);
        }
    }
}
