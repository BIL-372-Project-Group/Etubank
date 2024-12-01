package guis;

import javax.swing.*;

import dataAccess.customer;
import dataAccess.loan;
import dataAccess.loan_payment_schedule;
import dataAccess.loan_status;
import dataAccess.loan_type;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

public class TakeLoanDialog extends JDialog implements ActionListener {
    private final JButton homeLoanButton;
    private final JButton personalLoanButton;
    private final JButton carLoanButton;
    private final JButton confirmButton;

    private final JPanel loanDetailsPanel;
    private final JPanel homeLoanPanel;
    private final JPanel personalLoanPanel;
    private final JPanel carLoanPanel;

    private final double homeLoanInterestRate = 3.5; // %3.5
    private final double personalLoanInterestRate = 5.0; // %5.0
    private final double carLoanInterestRate = 4.2; // %4.2

    private final Integer[] termOptions = {6, 12, 24};
    private static customer user;

    public TakeLoanDialog(JFrame parentFrame, customer u) {
        super(parentFrame, "Loan Options", true);
        this.user = u;
        setLayout(new BorderLayout());

        // Initialize loan selection buttons
        homeLoanButton = new JButton("Home Loan");
        personalLoanButton = new JButton("Personal Loan");
        carLoanButton = new JButton("Car Loan");
        homeLoanButton.addActionListener(this);
        personalLoanButton.addActionListener(this);
        carLoanButton.addActionListener(this);

        // Set initial color of buttons
        homeLoanButton.setBackground(Color.CYAN); // Start with Home Loan selected
        personalLoanButton.setBackground(Color.LIGHT_GRAY);
        carLoanButton.setBackground(Color.LIGHT_GRAY);

        // Panel for selecting loan types
        JPanel loanSelectionPanel = new JPanel();
        loanSelectionPanel.add(homeLoanButton);
        loanSelectionPanel.add(personalLoanButton);
        loanSelectionPanel.add(carLoanButton);

        // Initialize the loan details panels
        homeLoanPanel = createLoanPanel("Home Loan", homeLoanInterestRate);
        personalLoanPanel = createLoanPanel("Personal Loan", personalLoanInterestRate);
        carLoanPanel = createLoanPanel("Car Loan", carLoanInterestRate);

        // Add the panels to the main panel and set the default to hide
        loanDetailsPanel = new JPanel(new CardLayout());
        loanDetailsPanel.add(homeLoanPanel, "Home Loan");
        loanDetailsPanel.add(personalLoanPanel, "Personal Loan");
        loanDetailsPanel.add(carLoanPanel, "Car Loan");

        // Confirm button
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(this);

        add(loanSelectionPanel, BorderLayout.NORTH);
        add(loanDetailsPanel, BorderLayout.CENTER);
        add(confirmButton, BorderLayout.SOUTH);

        // Dialog settings
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private JPanel createLoanPanel(String loanType, double interestRate) {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        JLabel principalLabel = new JLabel("Principal Amount:");
        JTextField principalField = new JTextField();
        JLabel termLabel = new JLabel("Term (months):");
        JComboBox<Integer> termComboBox = new JComboBox<>(termOptions);
        JLabel interestRateLabel = new JLabel("Interest Rate: " + String.format("%.2f%%", interestRate));

        panel.add(principalLabel);
        panel.add(principalField);
        panel.add(termLabel);
        panel.add(termComboBox);
        panel.add(new JLabel("Interest Rate:"));
        panel.add(interestRateLabel);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        CardLayout cardLayout = (CardLayout) loanDetailsPanel.getLayout();

        // Reset all button colors to the default
        homeLoanButton.setBackground(Color.LIGHT_GRAY);
        personalLoanButton.setBackground(Color.LIGHT_GRAY);
        carLoanButton.setBackground(Color.LIGHT_GRAY);

        switch (command) {
            case "Home Loan":
                cardLayout.show(loanDetailsPanel, "Home Loan");
                homeLoanButton.setBackground(Color.CYAN); // Change color of selected button
                break;
            case "Personal Loan":
                cardLayout.show(loanDetailsPanel, "Personal Loan");
                personalLoanButton.setBackground(Color.CYAN); // Change color of selected button
                break;
            case "Car Loan":
                cardLayout.show(loanDetailsPanel, "Car Loan");
                carLoanButton.setBackground(Color.CYAN); // Change color of selected button
                break;
            case "Confirm":
                handleConfirm();
                break;
        }
    }

    private void handleConfirm() {
        CardLayout cardLayout = (CardLayout) loanDetailsPanel.getLayout();
        Component visiblePanel = null;
    
        for (Component comp : loanDetailsPanel.getComponents()) {
            if (comp.isVisible()) {
                visiblePanel = comp;
                break;
            }
        }
    
        if (visiblePanel == null) {
            JOptionPane.showMessageDialog(this, "No loan type selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        JPanel selectedPanel = (JPanel) visiblePanel;
        JTextField principalField = (JTextField) selectedPanel.getComponent(1); // Principal field
        JComboBox<Integer> termComboBox = (JComboBox<Integer>) selectedPanel.getComponent(3); // Term dropdown
    
        try {
            double principal = Double.parseDouble(principalField.getText().trim());
            int term = (Integer) termComboBox.getSelectedItem();
            double interestRate = 0.0;
    
            if (selectedPanel == homeLoanPanel) {
                interestRate = homeLoanInterestRate;
            } else if (selectedPanel == personalLoanPanel) {
                interestRate = personalLoanInterestRate;
            } else if (selectedPanel == carLoanPanel) {
                interestRate = carLoanInterestRate;
            }
    
            Date startDate = new Date(System.currentTimeMillis());
            Date endDate = Date.valueOf(startDate.toLocalDate().plusMonths(term));
    
            loan temp = new loan(
                1, // Replace with loan ID if auto-generated by DB
                1, // Replace with bank ID or branch ID
                user.customer_id,
                (int)principal,
                interestRate,
                0, // Loan status (0 = active, for example)
                startDate,
                endDate,
                loan_type.getByID(customer.storedConnection, 1), // Payment made (initially 0)
                loan_status.getByID(customer.storedConnection, 1), // Remaining amount (initially same as principal)
                new ArrayList<loan_payment_schedule>()
        );

        temp.installments = term;

            user.loans.add(temp);
            user.accounts.get(0).balance = user.accounts.get(0).balance.add(BigDecimal.valueOf(principal));
    
            JOptionPane.showMessageDialog(this, "Loan confirmed!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid principal amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Banking Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setVisible(true);

            TakeLoanDialog loanDialog = new TakeLoanDialog(frame, user);
            loanDialog.setVisible(true);
        });
    }
}
