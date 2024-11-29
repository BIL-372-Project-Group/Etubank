package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//interaction with MYSQL database.

public class DataAccessLayer {

    //db configuration
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/etubank_test";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "abc123";


    public static customer createSession(String email, String password) {
        try{
            Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM customer WHERE email = ? AND password = ?"
            );

            //parameter index refers to ?
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            //execute query and store it in result set
            ResultSet resultSet = preparedStatement.executeQuery();

            //next() returns true or false
            //true - query returned data and result set now points to the first row
            //false query returned no data and result set equals to null
            if(resultSet.next()){
                int customerId = resultSet.getInt("customer_id");
                customer temp = customer.getByID(connection, customerId);
                System.out.println(temp);
                return temp;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //not valid user
        return null;
    }

}
