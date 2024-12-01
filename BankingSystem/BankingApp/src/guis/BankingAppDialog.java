package guis;

import dataAccess.DataAccessLayer;
import dataAccess.account;
import dataAccess.customer;
import dataAccess.transaction;
import dataAccess.transaction_history;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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

        setResizable(true);

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
        enterAmountLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        enterAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterAmountLabel);

        enterAmountField =  new JTextField();
        enterAmountField.setBounds(15,80,getWidth() - 50, 40);
        enterAmountField.setFont(new Font("Dialog", Font.PLAIN, 30));
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
        enterUserLabel.setFont(new Font("Dialog", Font.ROMAN_BASELINE, 16));
        enterUserLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterUserLabel);

        //enter user field
        enterUserField =  new JTextField();
        enterUserField.setBounds(15,160,getWidth()-50, 40);
        enterUserField.setFont(new Font("Dialog", Font.PLAIN, 25));
        enterUserField.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterUserField);
    }


    private void handleTransaction(String transactionType, float amountVal) {
        BigDecimal amount = BigDecimal.valueOf(amountVal);
        try (Connection connection = DriverManager.getConnection(DataAccessLayer.DB_URL, DataAccessLayer.DB_USERNAME, DataAccessLayer.DB_PASSWORD)) {
            int transactionTypeId = transactionType.equalsIgnoreCase("Deposit") ? 1 : 2; // Assuming 1 for Deposit and 2 for Withdraw
            transaction.addTransactionToDatabase(connection, transactionTypeId, selectedAccount.account_id, selectedAccount.account_id, amount);
            selectedAccount.handleTransaction(transactionType, amount);
            resetFieldsAndUpdateCurrentBalance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleTransfer(String transferredUserIBAN, float amount) {
        account recipientAccount = DataAccessLayer.getAccountByIBAN(transferredUserIBAN);
        if (recipientAccount == null) {
            JOptionPane.showMessageDialog(this, "Transfer Error: User not found.");
            return;
        }
        BigDecimal amountBD = BigDecimal.valueOf(amount);
        try (Connection connection = DriverManager.getConnection(DataAccessLayer.DB_URL, DataAccessLayer.DB_USERNAME, DataAccessLayer.DB_PASSWORD)) {
            if(selectedAccount.account_id == recipientAccount.account_id) {
                JOptionPane.showMessageDialog(this, "Transfer Error: Cannot transfer to the same account.");
                return;
            } else if (selectedAccount.getBalance().compareTo(amountBD) < 0) {
                JOptionPane.showMessageDialog(this, "Transfer Error: Insufficient funds.");
            } else{
                transaction.addTransactionToDatabase(connection, 3, selectedAccount.account_id, recipientAccount.account_id, amountBD); // Assuming 3 for Transfer
                selectedAccount.handleTransfer(recipientAccount, amountBD);
                resetFieldsAndUpdateCurrentBalance();
                
            }
            
        } catch (SQLException e) {
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

    public void addPastTransactionComponents() {
    // Container that will store the transactions
    pastTransactionPanel = new JPanel();
    pastTransactionPanel.setLayout(new BoxLayout(pastTransactionPanel, BoxLayout.Y_AXIS));

    JScrollPane scrollPane = new JScrollPane(pastTransactionPanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setBounds(0, 20, getWidth() - 15, getHeight() - 20);
    // Fetch transaction history
    ArrayList<transaction> pastTransactions = fetchTransactionHistory();

    for (transaction pastTransaction : pastTransactions) {
        // Add it to the GUI
        System.out.println(pastTransaction.toString());

        JPanel pastTransactionContainer = new JPanel();
        pastTransactionContainer.setLayout(new BorderLayout());

        JLabel transactionTypeLabel = new JLabel(pastTransaction.getTRANSACTION_TYPE_NAME(pastTransaction.transaction_id));
        transactionTypeLabel.setFont(new Font("Dialog", Font.BOLD, 20));

        JLabel transactionAmountLabel = new JLabel(String.valueOf(pastTransaction.getTRANSACTION_AMOUNT(pastTransaction.transaction_id)));
        transactionAmountLabel.setFont(new Font("Dialog", Font.BOLD, 20));

        JLabel transactionDateLabel = new JLabel(pastTransaction.getTRANSACTION_DATE().toString());
        transactionDateLabel.setFont(new Font("Dialog", Font.BOLD, 20));

        pastTransactionContainer.add(transactionDateLabel, BorderLayout.SOUTH);
        pastTransactionContainer.add(transactionTypeLabel, BorderLayout.WEST);
        pastTransactionContainer.add(transactionAmountLabel, BorderLayout.EAST);

        pastTransactionContainer.setBackground(Color.WHITE);
        pastTransactionContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pastTransactionPanel.add(pastTransactionContainer);
    }

    add(scrollPane);
}

private ArrayList<transaction> fetchTransactionHistory() {

    ArrayList<transaction> transactions = new ArrayList<>();
    try (Connection connection = DriverManager.getConnection(DataAccessLayer.DB_URL, DataAccessLayer.DB_USERNAME, DataAccessLayer.DB_PASSWORD)) {
        transactions = transaction.getByAccountID(connection, selectedAccount.account_id);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return transactions;
}


public void refreshPastTransactions() {
    pastTransactionPanel.removeAll();
    addPastTransactionComponents();
    pastTransactionPanel.revalidate();
    pastTransactionPanel.repaint();
}
    

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand();
        float amountVal = Float.parseFloat(enterAmountField.getText());
        if (buttonPressed.equalsIgnoreCase("Deposit") || 
            buttonPressed.equalsIgnoreCase("Withdraw")) {
            handleTransaction(buttonPressed, amountVal);
        } else if (buttonPressed.equalsIgnoreCase("Transfer")) {
            String transferredUser = enterUserField.getText();
            handleTransfer(transferredUser, amountVal);
        }
    }
}
