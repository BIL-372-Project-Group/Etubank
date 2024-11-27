package guis;

import db_sys.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankGui extends BaseFrame{

    public static String prefferedBank;

    public BankGui() {
        super("Choose Bank");
    }

    @Override
    protected void addGuiComponents() {

        //banking app label
        JLabel bankingAppLabel = new JLabel("Please Choose Your Bank");

        //setting the labels location
        bankingAppLabel.setBounds(-10,20,super.getWidth(),40);

        //font changes
        bankingAppLabel.setFont(new Font("Dialog", Font.BOLD, 32));

        //center text
        bankingAppLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(bankingAppLabel);

        //choosing bank label
        JLabel chooseBank = new JLabel("Choose your bank from below:");

        //creating bank buttons
        JButton firstBank = new JButton("ETU BANK");
        firstBank.setBounds(20,160,getWidth()-50,40);
        firstBank.setFont(new Font("Dialog", Font.PLAIN, 28));
        firstBank.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                prefferedBank = "ETU BANK";
                BankGui.this.dispose();
                new LoginGui().setVisible(true);
            }
        });
        add(firstBank);

        JButton secondBank = new JButton("GARANTI BANK");
        secondBank.setBounds(20,220,getWidth()-50,40);
        secondBank.setFont(new Font("Dialog", Font.PLAIN, 28));
        secondBank.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                prefferedBank = "GARANTI BANK";
                BankGui.this.dispose();
                new LoginGui().setVisible(true);
            }
        });
        add(secondBank);

        JButton thirdBank = new JButton("ZIRAAT BANK");
        thirdBank.setBounds(20,280,getWidth()-50,40);
        thirdBank.setFont(new Font("Dialog", Font.PLAIN, 28));
        thirdBank.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                prefferedBank = "ZIRAAT BANK";
                BankGui.this.dispose();
                new LoginGui().setVisible(true);
            }
        });
        add(thirdBank);

    }
}
