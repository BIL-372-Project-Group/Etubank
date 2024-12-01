package guis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Kredi cekme icin yazilmis class
public class TakeLoanDialog extends JDialog implements ActionListener {
    private final JButton homeLoanJButton;
    private final JButton personalLoanJButton;
    private final JTextField homeLoanPrincipalField;
    private final JTextField personalLoanPrincipalField;
    private final JTextField carLoanPrincipalField;
    private final JButton carLoanJButton;

    private final JComboBox<Integer> homeLoanTermComboBox;
    private final JComboBox<Integer> personalLoanTermComboBox;   
    private final JComboBox<Integer> carLoanTermComboBox;

    private final JLabel homeLoanInterestLabel;
    private final JLabel personalLoanInterestLabel;
    private final JLabel carLoanInterestLabel;

    private final JButton confirmButton;


    private final double homeLoanInterestRate = 3.5; // %3.5
    private final double personalLoanInterestRate = 5.0; // %5.0
    private final double carLoanInterestRate = 4.2; // %4.2


    private final Integer[] termOptions = {6, 12, 24};

    

    public TakeLoanDialog(JFrame parentFrame) {
        super(parentFrame, "Loan Options", true);
        setLayout(new BorderLayout());
        
        JPanel homeLoanPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        homeLoanPanel.setBorder(BorderFactory.createTitledBorder("Home Loan"));
        
        homeLoanJButton = new JButton("Home Loan");
        homeLoanJButton.setActionCommand("Home Loan");
        homeLoanJButton.addActionListener(this);
        homeLoanPrincipalField = new JTextField();
        homeLoanTermComboBox = new JComboBox<>(termOptions);
        homeLoanInterestLabel = new JLabel("Interest Rate: " + homeLoanInterestRate + "%");

        homeLoanPanel.add(homeLoanJButton);
        homeLoanPanel.add(new JLabel());
        homeLoanPanel.add(new JLabel("Principal Amount:"));
        homeLoanPanel.add(homeLoanPrincipalField);
        homeLoanPanel.add(new JLabel("Term (months):"));
        homeLoanPanel.add(homeLoanTermComboBox);
        homeLoanPanel.add(homeLoanInterestLabel);


        // Panel for Personal Loan
        JPanel personalLoanPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        personalLoanPanel.setBorder(BorderFactory.createTitledBorder("Personal Loan"));
        personalLoanJButton = new JButton("Personal Loan");
        personalLoanJButton.setActionCommand("Personal Loan");
        personalLoanPrincipalField = new JTextField();
        personalLoanTermComboBox = new JComboBox<>(termOptions);
        personalLoanInterestLabel = new JLabel("Interest Rate: " + personalLoanInterestRate + "%");

        personalLoanPanel.add(personalLoanJButton);
        personalLoanPanel.add(new JLabel());
        personalLoanPanel.add(new JLabel("Principal Amount:"));
        personalLoanPanel.add(personalLoanPrincipalField);
        personalLoanPanel.add(new JLabel("Term (months):"));
        personalLoanPanel.add(personalLoanTermComboBox);
        personalLoanPanel.add(personalLoanInterestLabel);


        // Panel for Car Loan
        JPanel carLoanPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        carLoanJButton = new JButton("Car Loan");
        carLoanJButton.setActionCommand("Car Loan");
        carLoanPrincipalField = new JTextField("");
        carLoanTermComboBox = new JComboBox<>(termOptions);
        carLoanInterestLabel = new JLabel("Interest Rate: " + carLoanInterestRate + "%");

        carLoanPanel.add(carLoanJButton);
        carLoanPanel.add(new JLabel());
        carLoanPanel.add(new JLabel("Principal Amount:"));
        carLoanPanel.add(carLoanPrincipalField);
        carLoanPanel.add(new JLabel("Term:"));
        carLoanPanel.add(carLoanTermComboBox);
        carLoanPanel.add(carLoanInterestLabel);

        ButtonGroup loanGroup = new ButtonGroup();
        loanGroup.add(homeLoanJButton);
        loanGroup.add(personalLoanJButton);
        loanGroup.add(carLoanJButton);


        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(this);

        JPanel loanSelectionPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        loanSelectionPanel.add(homeLoanPanel);
        loanSelectionPanel.add(personalLoanPanel);
        loanSelectionPanel.add(carLoanPanel);

        add(loanSelectionPanel, BorderLayout.CENTER);
        add(confirmButton, BorderLayout.SOUTH);

        // Dialog settings
        setSize(900, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            System.out.println(command);
            if ("Home Loan".equals(command)) {
                String selectedTerm = e.getActionCommand();
                JOptionPane.showMessageDialog(this, 
                        "Are you sure you want to proceed with Home Loan?\nInterest Rate: " + homeLoanInterestRate + "%\nTerm: " + selectedTerm + " months", 
                        "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } else if ("Personal Loan".equals(command)) {
                int selectedTerm = (int) personalLoanTermComboBox.getSelectedItem();
                JOptionPane.showMessageDialog(this, 
                        "Are you sure you want to proceed with Personal Loan?\nInterest Rate: " + personalLoanInterestRate + "%\nTerm: " + selectedTerm + " months", 
                        "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } else if ("Car Loan".equals(command)) {
                int selectedTerm = (int) carLoanTermComboBox.getSelectedItem();
                JOptionPane.showMessageDialog(this, 
                        "Are you sure you want to proceed with Car Loan?\nInterest Rate: " + carLoanInterestRate + "%\nTerm: " + selectedTerm + " months", 
                        "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,"" + command + " is not a valid loan type.", "Error", JOptionPane.ERROR_MESSAGE); 
        }
    }

    /* 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Banking Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setVisible(true);

            LoanDialog loanDialog = new LoanDialog(frame);
            loanDialog.setVisible(true);
        });
    }

     */
}
