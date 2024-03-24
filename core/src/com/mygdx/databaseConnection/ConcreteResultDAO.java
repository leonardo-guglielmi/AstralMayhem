package com.mygdx.databaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConcreteResultDAO implements ResultDAO{

    public List<Result> get() throws SQLException {
        Connection con = Database.getConnection();
        List<Result> results = new ArrayList<>();

        String query = "SELECT *\n" +
                "FROM result\n" +
                "ORDER BY points DESC\n" +
                "LIMIT 5";

        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String player = rs.getString("player");
            int points = rs.getInt("points");
            int time = rs.getInt("time");
            results.add(new Result(player, points, time));
        }
        Database.closePreparedStatement(ps);
        Database.closeConnection(con);

        return results;
    }

    public int insert(Result result) throws SQLException {
        Connection con = Database.getConnection();

        String sql = "INSERT INTO result (player, points, time) VALUES (?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, result.getPlayer());
        ps.setInt(2, result.getPoints());
        ps.setInt(3, result.getTime());

        int res = ps.executeUpdate();

        Database.closePreparedStatement(ps);
        Database.closeConnection(con);

        System.out.println("Insert executed");
        return res;
    }
}
