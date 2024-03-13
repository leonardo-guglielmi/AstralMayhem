package com.mygdx.databaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDAOConcrete implements ResultDAO{

    public Result get() throws SQLException {
        Connection con = Database.getConnection();
        Result result = null;
        String sql = "SELECT *\n" +
                "FROM result\n" +
                "WHERE points = (SELECT MAX(points) FROM Result);\n";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            int points = rs.getInt("points");
            int time = rs.getInt("time");

            result = new Result(points, time);
        }
        return result;
    }

    //QUESTO METODO NON DOVREBBE SERVIRE, LO LASCIO NEL CASO FOSSE UTILE, POI NEL CASO LO TOGLIAMO
    public List<Result> getAll() throws SQLException {

        Connection con = Database.getConnection();
        String sql = "SELECT * FROM result";

        List<Result> results = new ArrayList<>();

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()) {
            int points = rs.getInt("points");
            int time = rs.getInt("time");

            Result result = new Result(points, time);

            results.add(result);
        }

        return results;
    }

    public int insert(Result result) throws SQLException {
        Connection con = Database.getConnection();

        String sql = "INSERT INTO Result (points, time) VALUES (?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, result.getPoints());
        ps.setInt(2, result.getTime());

        int res = ps.executeUpdate();

        Database.closePreparedStatement(ps);
        Database.closeConnection(con);

        return res;
    }
}
