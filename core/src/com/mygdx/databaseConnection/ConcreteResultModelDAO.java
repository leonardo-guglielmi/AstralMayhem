package com.mygdx.databaseConnection;

import com.mygdx.utils.Commons;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConcreteResultModelDAO implements ResultModelDAO {

    @Override
    public List<ResultModel> getBest() throws SQLException {
        Connection con = Database.getConnection();
        List<ResultModel> resultModels = new ArrayList<>();

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
            resultModels.add(new ResultModel(player, points, time));
        }
        Database.closePreparedStatement(ps);
        Database.closeConnection(con);

        return resultModels;
    }

    @Override
    public List<ResultModel> getByUSer(String user) throws SQLException {
        Connection con = Database.getConnection();
        List<ResultModel> resultModels = new ArrayList<>();

        if(user.isEmpty())
            user = Commons.DEFAULT_USERNAME;
        String query = "SELECT *\n" +
                "FROM result\n" +
                "WHERE player = '" +user +"'\n" +
                "LIMIT 5";

        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String player = rs.getString("player");
            int points = rs.getInt("points");
            int time = rs.getInt("time");
            resultModels.add(new ResultModel(player, points, time));
        }
        Database.closePreparedStatement(ps);
        Database.closeConnection(con);

        return resultModels;
    }

    @Override
    public void insert(ResultModel resultModel) throws SQLException {
        Connection con = Database.getConnection();

        String sql = "INSERT INTO result (player, points, time) VALUES (?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, resultModel.getPlayer());
        ps.setInt(2, resultModel.getPoints());
        ps.setInt(3, resultModel.getTime());

        ps.executeUpdate();

        Database.closePreparedStatement(ps);
        Database.closeConnection(con);

        System.out.println("Insert executed");
    }
}
