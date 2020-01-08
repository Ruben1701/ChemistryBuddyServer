package quiz.rest.service.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {


    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/ChemistryBuddy";
        String url2 = "jdbc:mysql://127.0.0.1:3306/ChemistryBuddy";


        String username = "root";
        String password = "Oliwoi19!";
        System.out.println("Connecting database...");

        try (
                Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
        } catch (
                SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }


}
