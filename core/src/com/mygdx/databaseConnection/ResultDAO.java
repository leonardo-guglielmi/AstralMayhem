package com.mygdx.databaseConnection;

import java.sql.SQLException;
import java.util.List;

public interface ResultDAO {

    Result get() throws SQLException;

    List<Result> getAll() throws SQLException;

    int insert(Result result) throws SQLException;

}
