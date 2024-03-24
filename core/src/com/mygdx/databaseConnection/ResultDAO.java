package com.mygdx.databaseConnection;

import java.sql.SQLException;
import java.util.List;

public interface ResultDAO {

    List<Result> get() throws SQLException;

    int insert(Result result) throws SQLException;

}
