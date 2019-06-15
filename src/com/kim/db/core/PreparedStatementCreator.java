package com.kim.db.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 创建预处理对象
public interface PreparedStatementCreator {
    PreparedStatement createPreparedStatement(Connection connection) throws SQLException;
}
