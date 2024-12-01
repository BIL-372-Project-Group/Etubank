package guis;

import javax.swing.*;

import dataAccess.customer;
import dataAccess.loan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import bll.Loan;

public class PayLoanDialog extends JDialog implements ActionListener {
    private JComboBox<String> loanComboBox;
    private JLabel monthlyPaymentLabel;
    private JButton payButton;
    private JButton cancelButton;

    private Map<String, Loan> loans;
    private static customer user;

    public PayLoanDialog(Frame parent, customer u) {
        super(parent, "Pay Loan", true);
        user = u;
        initializeLoans(); // Initialize with sample data
        setupUI();
    }

    private void initializeLoans() {
        loans = new HashMap<String, Loan>();
        int i = 0;
        for(loan a : user.loans){
            loans.put("Loan: " + (i++), new Loan(a));
        }
    }

    private void setupUI() {
        // Set up dialog properties
        setLayout(new BorderLayout(10, 10));
        setSize(400, 300);
        setLocationRelativeTo(null); // Center on screen

        // Header label
        JLabel headerLabel = new JLabel("Pay Loan", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(headerLabel, BorderLayout.NORTH);

        // Main panel for form elements
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        add(formPanel, BorderLayout.CENTER);

        // Loan selection label and combo box
        JLabel loanLabel = new JLabel("Select Loan:");
        formPanel.add(loanLabel);

        loanComboBox = new JComboBox<>(loans.keySet().toArray(new String[0]));
        loanComboBox.addActionListener(this);
        formPanel.add(loanComboBox);

        // Monthly payment label
        JLabel paymentLabel = new JLabel("Monthly Payment:");
        formPanel.add(paymentLabel);

        monthlyPaymentLabel = new JLabel("0.0");
        monthlyPaymentLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        formPanel.add(monthlyPaymentLabel);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        add(buttonPanel, BorderLayout.SOUTH);

        payButton = new JButton("Pay");
        payButton.addActionListener(this);
        buttonPanel.add(payButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        buttonPanel.add(cancelButton);

        // Initialize monthly payment display
        updateMonthlyPayment();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == payButton) {
            handlePayment();
        } else if (e.getSource() == cancelButton) {
            dispose(); // Close the dialog
        } else if (e.getSource() == loanComboBox) {
            updateMonthlyPayment(); // Update monthly payment when a new loan is selected
        }
    }

    private void updateMonthlyPayment() {
        String selectedLoan = (String) loanComboBox.getSelectedItem();
        if (selectedLoan == null) return;

        Loan loan = loans.get(selectedLoan);
        if (loan != null) {
            double monthlyPayment = calculateMonthlyPayment(loan.getRemainingBalance(), loan.getInterestRate(), loan.getInstallments());
            monthlyPaymentLabel.setText(String.format("$%.2f", monthlyPayment)); // Display formatted monthly payment
        }
    }

    private double calculateMonthlyPayment(double principal, double interestRate, int installments) {
        double monthlyInterestRate = interestRate / 100 / 12; // Convert annual rate to monthly rate
        return (principal * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -installments));
    }

    private void handlePayment() {
        String selectedLoan = (String) loanComboBox.getSelectedItem();
        if (selectedLoan == null) {
            JOptionPane.showMessageDialog(this, "Please select a loan.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Loan loan = loans.get(selectedLoan);
        if (loan == null) {
            JOptionPane.showMessageDialog(this, "Selected loan data not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double monthlyPayment = calculateMonthlyPayment(loan.getRemainingBalance(), loan.getInterestRate(), loan.getInstallments());
        double newBalance = loan.getRemainingBalance() - monthlyPayment;
        //loan.setRemainingBalance(newBalance);

        if (newBalance <= 0) {
            JOptionPane.showMessageDialog(this, "Loan fully paid!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loans.remove(selectedLoan);
            loanComboBox.removeItem(selectedLoan); // Remove fully paid loan from combo box
        } else {
            user.accounts.get(0).balance = user.accounts.get(0).balance.subtract(BigDecimal.valueOf(monthlyPayment));
            JOptionPane.showMessageDialog(this, "Payment successful! Remaining balance: " + String.format("$%.2f", newBalance), "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        updateMonthlyPayment(); // Update the displayed monthly payment after payment is made
    }
}