package quiz.rest.authentication;

import quiz.rest.DTO.AccountDTO;
import quiz.rest.DTO.LoginDTO;
import quiz.rest.DTO.RegisterDTO;
import quiz.rest.DbClass;

import java.sql.*;
import java.util.Objects;

public class ChemistryBuddyLogin {

    private DbClass dbClass = new DbClass();
    String dbURL = "jdbc:mysql://127.0.0.1:3306/ChemistryBuddy";
    String dbuser = "root";
    String dbpassword = "Oliwoi19!";


    public boolean register(RegisterDTO registerDTO){
        try{
            Connection con = DriverManager.getConnection(dbURL, dbuser, dbpassword);

            String call = "{call register_user(?,?)}";
            try (CallableStatement stmt = con.prepareCall(call)) {
                stmt.setString(1, registerDTO.getUsername());
                stmt.setString(2, registerDTO.getPassword());
                //ResultSet rs = stmt.executeQuery();
                stmt.execute();
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean login(LoginDTO loginDTO){
        //String procedure = "SELECT User_Email, User_Password FROM [dbo].[User] WHERE User_Email = ? AND User_Password = ?";
        String call = "{call login_user(?,?)}";


        Connection con = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbURL, dbuser, dbpassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (CallableStatement stmt = Objects.requireNonNull(con).prepareCall(call)) {
            stmt.setString(1, loginDTO.getUsername());
            stmt.setString(2, loginDTO.getPassword());
            ResultSet rs = stmt.executeQuery();
            boolean isEmpty = ! rs.first();
            if (!isEmpty) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean doesAccountExist() {
        //TODO
        return false;
    }


}

