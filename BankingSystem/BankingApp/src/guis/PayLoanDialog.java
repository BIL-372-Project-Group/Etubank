package guis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class PayLoanDialog extends JDialog implements ActionListener {
    private JComboBox<String> loanComboBox;
    private JLabel monthlyPaymentLabel;
    private JButton payButton;
    private JButton cancelButton;

    private Map<String, Loan> loans;

    public PayLoanDialog(Frame parent) {
        super(parent, "Pay Loan", true);
        initializeLoans(); // Initialize with sample data
        setupUI();
    }

    private void initializeLoans() {
        loans = new HashMap<>();
        loans.put("Loan 001", new Loan("Loan 001", 5000.0, 5.0, 12));
        loans.put("Loan 002", new Loan("Loan 002", 10000.0, 4.0, 24));
        loans.put("Loan 003", new Loan("Loan 003", 7500.0, 6.0, 18));
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
        loan.setRemainingBalance(newBalance);

        if (newBalance <= 0) {
            JOptionPane.showMessageDialog(this, "Loan fully paid!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loans.remove(selectedLoan);
            loanComboBox.removeItem(selectedLoan); // Remove fully paid loan from combo box
        } else {
            JOptionPane.showMessageDialog(this, "Payment successful! Remaining balance: " + String.format("$%.2f", newBalance), "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        updateMonthlyPayment(); // Update the displayed monthly payment after payment is made
    }

    // Loan class to encapsulate loan details
    private static class Loan {
        private String id;
        private double remainingBalance;
        private double interestRate; // Annual interest rate in %
        private int installments; // Remaining installments

        public Loan(String id, double remainingBalance, double interestRate, int installments) {
            this.id = id;
            this.remainingBalance = remainingBalance;
            this.interestRate = interestRate;
            this.installments = installments;
        }

        public String getId() {
            return id;
        }

        public double getRemainingBalance() {
            return remainingBalance;
        }

        public void setRemainingBalance(double remainingBalance) {
            this.remainingBalance = remainingBalance;
        }

        public double getInterestRate() {
            return interestRate;
        }

        public int getInstallments() {
            return installments;
        }
    }
}