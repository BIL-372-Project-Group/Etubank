### Steps to Run the Project

1. **Start the SQL Server**  
   Ensure your SQL server is running.

2. **Create a New Schema**  
   Set up a new database schema in your SQL server.

3. **Execute SQL Scripts**  
   Run the following scripts in order:  
   - `createTable.sql`  
   - `sampleTablePopulation.sql`

4. **Configure Database Connection**  
   Update the database configuration in the `src/dataAccess/DataAccessLayer` file:

   ```java
   // Database configuration
   private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/etubank_test";
   private static final String DB_USERNAME = "root";
   private static final String DB_PASSWORD = "password";
   ```

   Replace the placeholder values (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`) with your actual database details.

5. **Ensure MySQL Connector is Available**  
   Verify that the `mysql-connector-j-9.1.0.jar` file in the `lib` directory is properly recognized by your IDE.  
   - Add it to the project's classpath if necessary.

6. **Run the Application**  
   Execute the application to confirm it connects to the database and functions correctly.
