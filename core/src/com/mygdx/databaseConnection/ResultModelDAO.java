package com.mygdx.databaseConnection;

import java.sql.SQLException;
import java.util.List;

public interface ResultModelDAO {

    List<ResultModel> getBest() throws SQLException;
    List<ResultModel> getByUSer(String user) throws SQLException;

    void insert(ResultModel resultModel) throws SQLException;

}
