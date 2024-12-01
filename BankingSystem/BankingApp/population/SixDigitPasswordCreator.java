import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SixDigitPasswordCreator {

    int numberOfCreatedPasswords = 100;
    
    public static void main(String[] args) {
        Set<String> passwords = new HashSet<>();
        Random random = new Random();

        while (passwords.size() < numberOfCreatedPasswords) {
            StringBuilder password = new StringBuilder();

            for (int i = 0; i < 6; i++) {
                int digit = random.nextInt(10); 
                password.append(digit);
            }

            passwords.add(password.toString());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("password.txt"))) {
            for (String password : passwords) {
                writer.write(password);
                writer.newLine(); 
            }
            System.out.println("Sifreler 'password.txt' dosyasina basariyla yazildi!");
        } catch (IOException e) {
            System.err.println("Dosya yazilirken bir hata olustu: " + e.getMessage());
        }
    }
}
