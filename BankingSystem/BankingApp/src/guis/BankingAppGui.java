package guis;

import dataAccess.account;
import dataAccess.customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankingAppGui extends BaseFrame implements ActionListener {
    private account selectedAccount;
    private JTextField currentBalanceField;
    public JTextField getCurrentBalanceField() { return currentBalanceField;}

    public BankingAppGui(customer user, account selectedAccount) {
        super("Banking App", user);
        System.out.println(selectedAccount);
        this.selectedAccount = selectedAccount;
        currentBalanceField.setText("$" + selectedAccount.balance);
        setResizable(true);
    }

    @Override
    protected void addGuiComponents() {
        //create welcome message

        String welcomeString = "<html>" +
                "<body style = 'text-align:center'>" +
                "<b>Hello " + user.first_name + "</b><br>" +
                "What would you like to do today?</body></html>";

        JLabel welcomeMessageLabel =  new JLabel(welcomeString);
        welcomeMessageLabel.setBounds(0,20,getWidth(),40);
        welcomeMessageLabel.setFont(new Font("Dialog", Font.PLAIN,16));
        welcomeMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeMessageLabel);


        //create current balance label
        JLabel currentBalanceLabel =  new JLabel("Current Balance");
        currentBalanceLabel.setBounds(0,80,getWidth() -10,40);
        currentBalanceLabel.setFont(new Font("Dialog", Font.BOLD,16));
        currentBalanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(currentBalanceLabel);

        //create current balance field
        currentBalanceField = new JTextField("$" + 0);
        currentBalanceField.setBounds(15,120,getWidth() -50,40);
        currentBalanceField.setFont(new Font("Dialog", Font.BOLD,16));
        currentBalanceField.setHorizontalAlignment(SwingConstants.RIGHT);
        currentBalanceField.setEditable(false);
        
        add(currentBalanceField);

        //deposit button
        JButton depositButton =  new JButton("Deposit");
        depositButton.setBounds(15,180,getWidth() -50,50);
        depositButton.setFont(new Font("Dialog", Font.BOLD,22));
        depositButton.setHorizontalAlignment(SwingConstants.CENTER);
        depositButton.addActionListener(this);
        add(depositButton);

        //withdraw button
        JButton withdrawButton =  new JButton("Withdraw");
        withdrawButton.setBounds(15,240,getWidth() -50,50);
        withdrawButton.setFont(new Font("Dialog", Font.BOLD,22));
        withdrawButton.setHorizontalAlignment(SwingConstants.CENTER);
        withdrawButton.addActionListener(this);
        add(withdrawButton);

        // past transaction button
        JButton pastTransactionButton =  new JButton("Past Transaction");
        pastTransactionButton.setBounds(15,300,getWidth() -50,50);
        pastTransactionButton.setFont(new Font("Dialog", Font.BOLD,22));
        pastTransactionButton.setHorizontalAlignment(SwingConstants.CENTER);
        pastTransactionButton.addActionListener(this);
        add(pastTransactionButton);

        //transfer button
        JButton transferButton =  new JButton("Transfer");
        transferButton.setBounds(15,360,getWidth() -50,50);
        transferButton.setFont(new Font("Dialog", Font.BOLD,22));
        transferButton.setHorizontalAlignment(SwingConstants.CENTER);
        transferButton.addActionListener(this);
        add(transferButton);

        // Take loan button
        JButton takeLoanButton = new JButton("Take Loan");
        takeLoanButton.setBounds(15, 420, getWidth() - 50, 50);
        takeLoanButton.setFont(new Font("Dialog", Font.BOLD, 22));
        takeLoanButton.setHorizontalAlignment(SwingConstants.CENTER);
        takeLoanButton.addActionListener(this);
        add(takeLoanButton);

        // Pay loan button
        JButton payLoanButton = new JButton("Pay Loan");
        payLoanButton.setBounds(15, 480, getWidth() - 50, 50);
        payLoanButton.setFont(new Font("Dialog", Font.BOLD, 22));
        payLoanButton.setHorizontalAlignment(SwingConstants.CENTER);
        payLoanButton.addActionListener(this);
        add(payLoanButton);

        //Logout button
        JButton logoutButton =  new JButton("Logout");
        logoutButton.setBounds(15,540,getWidth() -50,50);
        logoutButton.setFont(new Font("Dialog", Font.BOLD,22));
        logoutButton.setHorizontalAlignment(SwingConstants.CENTER);
        logoutButton.addActionListener(this);
        add(logoutButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand();


        //pressed logout
        if(buttonPressed.equalsIgnoreCase("Logout")) {
            new LoginGui().setVisible(true);
            this.dispose();
            return;
        }

        //pressed take loan
        if (buttonPressed.equalsIgnoreCase("Take Loan")) {
            TakeLoanDialog takeLoanDialog = new TakeLoanDialog(this, user);
            takeLoanDialog.setVisible(true);
            return;
        }
        //pressed pay loan
        if (buttonPressed.equalsIgnoreCase("Pay Loan")) {
            PayLoanDialog payLoanDialog = new PayLoanDialog(this, user);
            payLoanDialog.setVisible(true);
            return;
        }

        BankingAppDialog bankingAppDialog =  new BankingAppDialog(this, user, selectedAccount);

        bankingAppDialog.setTitle(buttonPressed);


        if(buttonPressed.equalsIgnoreCase("Deposit") || buttonPressed.equalsIgnoreCase("Withdraw")
        || buttonPressed.equalsIgnoreCase("Transfer")) {

            //balance and gui components
            bankingAppDialog.addCurrentBalanceAndAmount();

            //add action button
            bankingAppDialog.addActionButton(buttonPressed);


            //for the transfer it will need more components
            if(buttonPressed.equalsIgnoreCase("Transfer")) {
                bankingAppDialog.addUserField();

            }


        }else if(buttonPressed.equalsIgnoreCase("Past Transaction")) {
            bankingAppDialog.addPastTransactionComponents();
        }
        bankingAppDialog.setVisible(true);
    }
}
