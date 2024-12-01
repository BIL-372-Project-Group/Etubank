package guis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public TakeLoanDialog(JFrame parentFrame) {
        super(parentFrame, "Loan Options", true);
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
        // Implement confirm logic here if needed
        JOptionPane.showMessageDialog(this, "Loan confirmed!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Banking Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setVisible(true);

            TakeLoanDialog loanDialog = new TakeLoanDialog(frame);
            loanDialog.setVisible(true);
        });
    }
}
