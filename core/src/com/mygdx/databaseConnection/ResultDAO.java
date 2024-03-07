package com.mygdx.databaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDAO {
    public Result get() throws SQLException {
        Connection con = Database.getConnection();
        Result result = null;
        String sql = "SELECT *\n" +
                "FROM result\n" +
                "WHERE points = (SELECT MAX(points) FROM Result);\n";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            String playerName = rs.getString("playerName");
            int points = rs.getInt("points");
            int time = rs.getInt("time");

            result = new Result(playerName, points, time);
        }
        return result;
    }

    public List<Result> getAll() throws SQLException {

        Connection con = Database.getConnection();
        String sql = "SELECT * FROM employees";

        List<Result> employees = new ArrayList<>();

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()) {
            String playerName = rs.getString("playerName");
            int points = rs.getInt("points");
            int time = rs.getInt("time");

            Result result = new Result(playerName, points, time);

            employees.add(result);
        }

        return employees;
    }

    public int insert(Result result) throws SQLException {
        Connection con = Database.getConnection();

        String sql = "INSERT INTO Result (playerName, points, time) VALUES (?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, result.getPlayerName());
        ps.setInt(2, result.getPoints());
        ps.setInt(3, result.getTime());

        int res = ps.executeUpdate();

        Database.closePreparedStatement(ps);
        Database.closeConnection(con);

        return res;
    }
}
