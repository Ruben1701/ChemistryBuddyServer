package quiz.rest.achievement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import quiz.rest.DTO.AchievementDTO;
import quiz.rest.DTO.LinkAchievementDTO;
import quiz.rest.DbClass;

import java.sql.*;


public class ChemistryBuddyAchievement {

    String dbURL = "jdbc:mysql://127.0.0.1:3306/ChemistryBuddy";
    String dbuser = "root";
    String dbpassword = "Oliwoi19!";
    private DbClass dbClass = new DbClass();

    public boolean create(AchievementDTO achievementDTO) {
        try {
            Connection con = DriverManager.getConnection(dbURL, dbuser, dbpassword);

            String call = "{call create_achievement(?,?)}";
            try (CallableStatement stmt = con.prepareCall(call)) {
                stmt.setString(1, achievementDTO.getName());
                stmt.setInt(2, achievementDTO.getPoints());
                //ResultSet rs = stmt.executeQuery();
                stmt.execute();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean setPlayerAchievement(String userId, String achievementId) {
        try {
            Connection con = DriverManager.getConnection(dbURL, dbuser, dbpassword);

            String call = "{call set_player_achievement(?,?)}";
            try (CallableStatement stmt = con.prepareCall(call)) {
                stmt.setInt(1, Integer.valueOf(userId));
                stmt.setInt(2, Integer.valueOf(achievementId));
                //ResultSet rs = stmt.executeQuery();
                stmt.execute();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getAchievement(int achievementId) {
        try {
            Connection con = DriverManager.getConnection(dbURL, dbuser, dbpassword);

            String call = "{call get_achievement(?)}";
            try (CallableStatement stmt = con.prepareCall(call)) {
                stmt.setInt(1, achievementId);

                ResultSet rs = stmt.executeQuery();
                boolean isEmpty = ! rs.first();
                ResultSetMetaData metadata = rs.getMetaData();
                int numColumns = metadata.getColumnCount();
                JSONObject obj = new JSONObject();
                if (!isEmpty) {
                    for (int i = 1; i <= numColumns; ++i)
                    {
                        String column_name = metadata.getColumnName(i);
                        obj.put(column_name, rs.getObject(column_name));
                    }
                    return obj.toJSONString();
                }
            }
        } catch (Exception e) {
        }

        return null;
    }

    public String getAllAchievements() {
        try {
            Connection con = DriverManager.getConnection(dbURL, dbuser, dbpassword);

            JSONArray json = new JSONArray();
            String call = "{call get_all_achievements()}";
            try (CallableStatement stmt = con.prepareCall(call)) {
                ResultSet rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int numColumns = metadata.getColumnCount();
                while (rs.next()) {
                    JSONObject obj = new JSONObject();      //extends HashMap
                    for (int i = 1; i <= numColumns; ++i)           //iterate columns
                    {
                        String column_name = metadata.getColumnName(i);
                        obj.put(column_name, rs.getObject(column_name));
                    }
                    json.add(obj);
                }
                return json.toJSONString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUserAchievement(int userId) {
        try {
            Connection con = DriverManager.getConnection(dbURL, dbuser, dbpassword);

            JSONArray json = new JSONArray();
            String call = "{call get_user_achievements(?)}";
            try (CallableStatement stmt = con.prepareCall(call)) {
                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int numColumns = metadata.getColumnCount();
                while (rs.next()) {
                    JSONObject obj = new JSONObject();      //extends HashMap
                    for (int i = 1; i <= numColumns; ++i)           //iterate columns
                    {
                        String column_name = metadata.getColumnName(i);
                        obj.put(column_name, rs.getObject(column_name));
                    }
                    json.add(obj);
                }
                return json.toJSONString();
            }
        } catch (Exception e) {
        }
        return null;
    }


}
