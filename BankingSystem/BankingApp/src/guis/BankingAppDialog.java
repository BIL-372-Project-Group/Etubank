package guis;

import dataAccess.customer;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

public class BankingAppDialog extends JDialog implements ActionListener {
    private customer user;
    private BankingAppGui bankingAppGui;
    private JLabel balanceLabel,enterAmountLabel, enterUserLabel;
    private JTextField enterAmountField, enterUserField;
    private JButton actionButton;
    private JPanel pastTransactionPanel;

    public BankingAppDialog( BankingAppGui bankingAppGui,customer user) {

        setSize(400,400);

        //addingFocus  to the dialog. Cannot interact with anything until the dialog is closed.
        setModal(true);
        //loads in the center of bankingapp gui
        setLocationRelativeTo(bankingAppGui);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setResizable(false);

        setLayout(null);

        this.bankingAppGui = bankingAppGui;

        this.user = user;


    }

    public void addCurrentBalanceAndAmount(){
        balanceLabel =  new JLabel("Balance: $" + 0);
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

    private void handleTransaction(String transactionType,float amountVal) {

    }

    private void resetFieldsAndUpdateCurrentBalance() {
        enterAmountField.setText("");

        if(enterUserField != null) {
            enterUserField.setText("");
        }

        //update current balance on dialog
        balanceLabel.setText("Balance: $" + 0);

        //update current balance on main gui
        bankingAppGui.getCurrentBalanceField().setText("$" + 0);
    }

    private void handleTransfer(customer user, String transferredUser, float amount){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonpressed = e.getActionCommand();

        //get amount val
        float amountVal = Float.parseFloat(enterAmountField.getText());

        //pressed deposit
        if(buttonpressed.equalsIgnoreCase("Deposit")){
            handleTransaction(buttonpressed,amountVal);
        }else{
            //pressed withdraw or transfer

            //validate input by making sure that withdraw or transfer amount is less than current balance
            //if result is -1 it means that the entered amount is more, 0 means they are equal, and 1 means that
            //the entered amount is less
            int result = 1;
            if(result < 0) {
                JOptionPane.showMessageDialog(this, buttonpressed + " Error:Input value is more than current balance.");
            }else {

                if (buttonpressed.equalsIgnoreCase("Withdraw")) {
                    handleTransaction(buttonpressed, amountVal);
                } else {

                    String transferedUser = enterUserField.getText();

                    //handle transfer
                    handleTransfer(user, transferedUser, amountVal);
                }
            }
        }
    }
}
