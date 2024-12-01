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


    // Simulated loans data (loan ID -> [remaining balance, interest rate, number of installments])
    //loans DB den cekilecek
    private Map<String, Loan> loans;

    public PayLoanDialog(Frame parent) {
        super(parent, "Pay Loan", true);
        initializeLoans(); // Initialize with sample data
        setupUI();
    }

    private void initializeLoans() {
        loans = new HashMap<>();
        loans.put("Loan 001", new Loan("Loan 001", 5000.0, 5.0, 12)); // 5000 balance, 5% interest, 12 installments
        loans.put("Loan 002", new Loan("Loan 002", 10000.0, 4.0, 24)); // 10000 balance, 4% interest, 24 installments
        loans.put("Loan 003", new Loan("Loan 003", 7500.0, 6.0, 18)); // 7500 balance, 6% interest, 18 installments
    }

    private void setupUI() {
        // Create components
        JLabel loanLabel = new JLabel("Select Loan:");
        loanComboBox = new JComboBox<>(loans.keySet().toArray(new String[0]));

        JLabel paymentLabel = new JLabel("Monthly Payment:");
        monthlyPaymentLabel = new JLabel("0.0"); // Initially empty or default value

        payButton = new JButton("Pay");
        cancelButton = new JButton("Cancel");

        payButton.addActionListener(this);
        cancelButton.addActionListener(this);
        loanComboBox.addActionListener(this); // Update payment when loan is selected

        // Layout
        setLayout(new GridLayout(3, 2, 10, 10));

        add(loanLabel);
        add(loanComboBox);
        add(paymentLabel);
        add(monthlyPaymentLabel);
        add(payButton);
        add(cancelButton);

        setSize(200, 300);
        setLocationRelativeTo(null); // Center on screen
        updateMonthlyPayment(); // Initialize the monthly payment display
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == payButton) {
            handlePayment();
        } else if (e.getSource() == cancelButton) {
            dispose(); // Close the dialog
        } else if (e.getSource() == loanComboBox) {
            updateMonthlyPayment(); // Update the monthly payment when a new loan is selected
        }
    }

    private void updateMonthlyPayment() {
        String selectedLoan = (String) loanComboBox.getSelectedItem();
        if (selectedLoan == null) return;

        Loan loan = loans.get(selectedLoan);
        double monthlyPayment = calculateMonthlyPayment(loan.getRemainingBalance(), loan.getInterestRate(), loan.getInstallments());
        monthlyPaymentLabel.setText(String.format("%.2f", monthlyPayment)); // Display formatted monthly payment
    }

    private double calculateMonthlyPayment(double principal, double interestRate, int installments) {
        double monthlyInterestRate = interestRate / 100 / 12; // Convert annual rate to monthly rate
        return (principal * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -installments));
    }

    private void handlePayment() {
        String selectedLoan = (String) loanComboBox.getSelectedItem();
        System.out.println(selectedLoan);
        if (selectedLoan == null) {
            JOptionPane.showMessageDialog(this, "Please select a loan.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Loan loan = loans.get(selectedLoan);
        double monthlyPayment = calculateMonthlyPayment(loan.getRemainingBalance(), loan.getInterestRate(), loan.getInstallments());

        // Deduct one installment's worth of payment
        double newBalance = loan.getRemainingBalance() - monthlyPayment;
        loan.setRemainingBalance(newBalance);

        if (newBalance <= 0) {
            JOptionPane.showMessageDialog(this, "Loan fully paid!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loans.remove(selectedLoan);
            loanComboBox.removeItem(selectedLoan); // Remove fully paid loan
        } else {
            JOptionPane.showMessageDialog(this, "Payment successful! Remaining balance: " + String.format("%.2f", newBalance), "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        dispose(); // Close the dialog
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


    /*
    // Entry point for testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PayLoanDialog dialog = new PayLoanDialog(null);
            dialog.setVisible(true);
        });
    }

     */
}
