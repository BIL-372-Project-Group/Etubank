import dataAccess.customer;
import guis.BankingAppGui;
import guis.LoginGui;
//import guis.RegisterGui;

import javax.swing.*;
import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            System.out.println("calisiyor");
           new LoginGui().setVisible(true);
           //new RegisterGui().setVisible(true);
           //new BankingAppGui(new User(1,"email","password",new BigDecimal("20.00"))
           //).setVisible(true);
        });
    }
}
