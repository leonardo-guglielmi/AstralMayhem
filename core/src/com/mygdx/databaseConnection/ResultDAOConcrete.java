package com.mygdx.databaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDAOConcrete implements ResultDAO{

    public List<Result> get() throws SQLException {
        Connection con = Database.getConnection();
        List<Result> results = null;
        String sql = "SELECT *\n" +
                "FROM resultastralmayhem\n" +
                "ORDER BY points DESC\n" +
                "LIMIT 5";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            String player = rs.getString("player");
            int points = rs.getInt("points");
            int time = rs.getInt("time");

            results.add(new Result(player, points, time));
        }
        return results;
    }

    //QUESTO METODO NON DOVREBBE SERVIRE, LO LASCIO NEL CASO FOSSE UTILE, POI NEL CASO LO TOGLIAMO
    public List<Result> getAll() throws SQLException {

        Connection con = Database.getConnection();
        String sql = "SELECT * FROM result";

        List<Result> results = new ArrayList<>();

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()) {
            String player = rs.getString("player");
            int points = rs.getInt("points");
            int time = rs.getInt("time");

            Result result = new Result(player, points, time);

            results.add(result);
        }

        return results;
    }

    public int insert(Result result) throws SQLException {
        Connection con = Database.getConnection();

        String sql = "INSERT INTO resultastralmayhem (player, points, time) VALUES (?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, result.getPlayer());
        ps.setInt(2, result.getPoints());
        ps.setInt(3, result.getTime());

        int res = ps.executeUpdate();

        Database.closePreparedStatement(ps);
        Database.closeConnection(con);

        return res;
    }
}
