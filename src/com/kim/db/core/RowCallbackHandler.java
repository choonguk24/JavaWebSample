package com.kim.db.core;

import java.sql.ResultSet;
import java.sql.SQLException;

//创建结果集对象
public interface RowCallbackHandler {
    void procesRow(ResultSet resultSet) throws SQLException;
}
